package com.example.plaintext.ui.screens.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.plaintext.R

@Composable
fun AboutDialog(
    isShowing: Boolean,
    onDismissRequest: () -> Unit
) {
    if (isShowing) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(text = stringResource(R.string.about_title))
            },
            text = {
                Text(text = stringResource(R.string.about_message))
            },
            confirmButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(R.string.about_confirm))
                }
            }
        )
    }
}