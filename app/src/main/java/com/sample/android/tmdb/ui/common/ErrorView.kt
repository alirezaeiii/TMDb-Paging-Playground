package com.sample.android.tmdb.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.android.tmdb.R

@Composable
fun ErrorView(message: String, refresh: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = refresh,
            colors = ButtonDefaults.buttonColors(TmdbGreen)
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}