package com.sample.android.tmdb.ui.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.sample.android.tmdb.BuildConfig
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.common.Dimens
import com.sample.android.tmdb.ui.common.TmdbGreen

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val aboutSettings = listOf(
        Settings.IntentAction(
            iconResourceId = R.drawable.ic_github,
            titleResourceId = R.string.source_code_github,
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(TMDB_REPO_URL))
        ),
        Settings.IntentAction(
            iconResourceId = R.drawable.ic_shield,
            titleResourceId = R.string.privacy_policy,
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(TMDB_POLICY_URL))
        ),
        Settings.Info(
            iconResourceId = R.drawable.ic_info,
            titleResourceId = R.string.version,
            value = BuildConfig.VERSION_NAME
        )
    )
    Box {
        SettingsGroupItem(
            settings = aboutSettings, modifier = modifier
                .padding(Dimens.PaddingNormal)
        )
    }
}

@Composable
private fun SettingsGroupItem(
    settings: List<Settings>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = DIVIDER_ALPHA)
        )
    ) {
        Column {
            settings.forEachIndexed { index, settingsItem ->
                SettingsItem(settings = settingsItem)

                if (index < settings.lastIndex) {
                    Divider()
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
private fun SettingsItem(
    settings: Settings,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    Row(
        modifier = modifier
            .then(
                when (settings) {
                    is Settings.Action -> Modifier.clickable(onClick = settings.onClick)
                    is Settings.IntentAction -> Modifier.clickable { context.startActivity(settings.intent) }
                    is Settings.Info -> Modifier
                }
            )
            .padding(Dimens.PaddingNormal)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
        ) {
            IconBox(
                iconResourceId = settings.iconResourceId,
                contentDescription = stringResource(id = settings.titleResourceId)
            )
            TitleText(titleResourceId = settings.titleResourceId)
        }
        when (settings) {
            is Settings.Info -> TitleText(title = settings.value)
            is Settings.Action, is Settings.IntentAction -> ForwardButton()
        }
    }
}

@Composable
private fun IconBox(
    @DrawableRes iconResourceId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(iconShapeSize)
            .background(color = MaterialTheme.colors.background, shape = CircleShape),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = contentDescription,
            modifier = modifier,
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun TitleText(
    modifier: Modifier = Modifier,
    @StringRes titleResourceId: Int,
    style: TextStyle = MaterialTheme.typography.subtitle1,
    color: Color = MaterialTheme.colors.onSurface,
) {
    Text(
        modifier = modifier,
        text = stringResource(id = titleResourceId),
        style = style,
        color = color
    )
}

@Composable
private fun TitleText(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = MaterialTheme.typography.subtitle1,
    color: Color = MaterialTheme.colors.onSurface,
) {
    Text(
        modifier = modifier,
        text = title,
        style = style,
        color = color
    )
}

@Composable
private fun ForwardButton(
    modifier: Modifier = Modifier,
    color: Color = TmdbGreen
) {
    Icon(
        modifier = modifier.size(iconShapeSize),
        painter = painterResource(id = R.drawable.ic_arrow_forward),
        contentDescription = stringResource(id = R.string.forward),
        tint = color
    )
}

sealed interface Settings {
    @get:DrawableRes
    val iconResourceId: Int

    @get:StringRes
    val titleResourceId: Int

    data class Info(
        @DrawableRes override val iconResourceId: Int,
        @StringRes override val titleResourceId: Int,
        val value: String
    ) : Settings

    data class Action(
        @DrawableRes override val iconResourceId: Int,
        @StringRes override val titleResourceId: Int,
        val onClick: () -> Unit
    ) : Settings

    data class IntentAction(
        @DrawableRes override val iconResourceId: Int,
        @StringRes override val titleResourceId: Int,
        val intent: Intent
    ) : Settings
}


private const val TMDB_REPO_URL = "https://github.com/alirezaeiii/TMDb-Paging-Playground"
private const val TMDB_POLICY_URL =
    "https://docs.google.com/document/d/10tQW2au7MCCYI8D1CZKU5jkpdbsUvsB6wCQ-7ysoT04/edit"
private const val DIVIDER_ALPHA = 0.12f
private val iconShapeSize = 32.dp