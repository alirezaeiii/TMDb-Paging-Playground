package com.sample.android.tmdb.ui.start

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.sample.android.tmdb.util.await
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.max

class InAppUpdateHandler @Inject constructor(
    private val activity: Activity
) {

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(activity.baseContext) }

    suspend fun ensureUpdated(): Boolean {
        Timber.d("Ensuring app is up to date.")
        val appUpdateInfo = try {
            withTimeout(TIMEOUT_MILLIS) {
                appUpdateManager.appUpdateInfo.await()
            }
        } catch (timeout: TimeoutCancellationException) {
            Timber.w("Timed out when getting AppUpdateInfo.")
            return true
        } catch (throwable: Throwable) {
            Timber.w(throwable, "Failed to get AppUpdateInfo.")
            return true
        }
        when (appUpdateInfo.updateAvailability()) {
            UpdateAvailability.UPDATE_AVAILABLE -> Unit
            UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> Unit
            else -> {
                Timber.i("No update available.")
                return true
            }
        }
        val immediate = isImmediateUpdate()
        val updateType = if (immediate) {
            Timber.i("Immediate update available.")
            AppUpdateType.IMMEDIATE
        } else {
            Timber.i("Flexible update available.")
            AppUpdateType.FLEXIBLE
        }
        val started = appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            updateType,
            activity,
            if (immediate) REQUEST_CODE_IMMEDIATE else REQUEST_CODE_FLEXIBLE,
        )
        if (!started) {
            Timber.w("Failed to start update flow for result")
            return true
        }
        return false
    }

    suspend fun completeUpdate(requestCode: Int, resultCode: Int): CompletionResult {
        val immediate = when (requestCode) {
            REQUEST_CODE_FLEXIBLE -> false
            REQUEST_CODE_IMMEDIATE -> true
            else -> return CompletionResult.INCORRECT_REQUEST_CODE
        }
        return if (resultCode == AppCompatActivity.RESULT_OK) {
            if (immediate) {
                CompletionResult.OK
            } else {
                try {
                    appUpdateManager.completeUpdate().await()
                } catch (throwable: Throwable) {
                    Timber.w(throwable, "Failed to install update.")
                }
                CompletionResult.OK
            }
        } else {
            if (immediate) {
                Timber.w("Immediate update not completed. App is blocked.")
                CompletionResult.BLOCKED
            } else {
                CompletionResult.OK
            }
        }
    }

    private fun isImmediateUpdate(): Boolean {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val maximumVersionForImmediateUpdate =
            remoteConfig.getString("maximum_version_for_immediate_update")
        if (maximumVersionForImmediateUpdate.isNotEmpty()) {
            val currentVersion =
                (activity.packageManager.getPackageInfo(activity.packageName, 0).versionName)
            Timber.i("current version name: $currentVersion")
            Timber.i("maximum version for immediate update: $maximumVersionForImmediateUpdate")
            return compareSemanticVersions(currentVersion, maximumVersionForImmediateUpdate) <= 0
        } else
            Timber.w("something's wrong with getting \"maximum_version_for_immediate_update\" from remote config")
        return false
    }

    enum class CompletionResult {
        INCORRECT_REQUEST_CODE,
        OK,
        BLOCKED,
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5000L
        private const val REQUEST_CODE_FLEXIBLE = 100
        private const val REQUEST_CODE_IMMEDIATE = 200

        /**
         * Compares two semantic versions.
         * Returns -1 if the first version is less than the second.
         * Returns 0 if the versions are identical.
         * Returns 1 if the first version is greater than the second.
         */
        private fun compareSemanticVersions(a: String, b: String): Int {
            val aList = a.split('.')
            val bList = b.split('.')
            for (i in 0 until max(aList.size, bList.size)) {
                if (aList.size < i + 1) {
                    return -1
                }
                if (bList.size < i + 1) {
                    return 1
                }
                val aInt = aList[i].toInt()
                val bInt = bList[i].toInt()
                if (aInt < bInt) {
                    return -1
                }
                if (aInt > bInt) {
                    return 1
                }
            }
            return 0
        }
    }
}