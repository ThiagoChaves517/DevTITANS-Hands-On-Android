package com.example.plaintext.ui.screens.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.plaintext.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarComponent(
    navigateToSettings: () -> Unit,
    showAboutDialog: () -> Unit,
    isOnPreferencesScreen: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = colorResource(id = R.color.font_screen)
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = colorResource(id = R.color.font_screen)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(colorResource(id = R.color.font_screen))
                    .padding(8.dp)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text =
                                if (isOnPreferencesScreen)
                                    stringResource(id = R.string.DropdownMenuItem_settings_back)
                                else
                                    stringResource(id = R.string.DropdownMenuItem_settings),
                            color = colorResource(id = R.color.font_screen)
                        )
                    },
                    onClick = {
                        navigateToSettings();
                        expanded = false;
                    },
                    modifier = Modifier
                        .padding(2.dp)
                        .background(colorResource(id = R.color.login_button))
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = R.string.DropdownMenuItem_about),
                            color = colorResource(id = R.color.font_screen)
                        )
                    },
                    onClick = {
                        showAboutDialog()
                        expanded = false;
                    },
                    modifier = Modifier
                        .padding(2.dp)
                        .background(colorResource(id = R.color.login_button))
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.background_container),
        ),
    )
}