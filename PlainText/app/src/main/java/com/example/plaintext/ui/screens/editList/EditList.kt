package com.example.plaintext.ui.screens.editList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plaintext.R
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.Screen
import com.example.plaintext.ui.screens.login.CustomButton
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.screens.login.CustomImageTextRow
import com.example.plaintext.ui.theme.PlainTextTheme

@Composable
fun EditInput(
    textInputLabel: String,
    textInputState: MutableState<String> = mutableStateOf(""),
    textInputHeight: Int = 60,
    contentColor: Color = Color.White,
) {
    val padding: Int = 30

    var textState by rememberSaveable { textInputState }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(textInputHeight.dp),
            //.padding(horizontal = padding.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(textInputLabel) },
            modifier = Modifier
                .height(textInputHeight.dp)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = contentColor,
                unfocusedTextColor = contentColor,
                focusedLeadingIconColor = contentColor,
                unfocusedLeadingIconColor = contentColor,
                focusedLabelColor = contentColor,
                unfocusedLabelColor = contentColor,
                focusedBorderColor = contentColor,
                unfocusedBorderColor = contentColor
            )
        )

    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun AddNewPasswordContainer(
    modifier: Modifier,
    onSaveClick: () -> Unit,
){
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        CustomImageTextRow(
            contentDescription = "cabeça de robô Android",
            textResId = R.string.login_image_text
        )

        Text(
            text = stringResource(R.string.editlist_adding_new_password).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 5.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            EditInput(
                "Nome",
            )
            EditInput(
                "Usuário",
            )
            EditInput(
                "Senha",
            )
            EditInput(
                "Notas",
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButton(
                modifier = Modifier.fillMaxWidth(0.90f),
                onClick = onSaveClick,
                text = stringResource(id = R.string.save_button),
                leadingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIconRotation = 180f
            )
        }
    }
}

@Composable
fun EditPasswordContainer(
    modifier: Modifier,
    onSaveClick: () -> Unit,
){
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        CustomImageTextRow(
            contentDescription = "cabeça de robô Android",
            textResId = R.string.login_image_text
        )

        Text(
            text = stringResource(R.string.editlist_editing_password).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 5.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            EditInput(
                "Nome",
            )
            EditInput(
                "Usuário",
            )
            EditInput(
                "Senha",
            )
            EditInput(
                "Notas",
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButton(
                modifier = Modifier.fillMaxWidth(0.90f),
                onClick = onSaveClick,
                text = stringResource(id = R.string.save_button),
                leadingIcon = null,
                trailingIcon = null,
                trailingIconRotation = 180f
            )
        }
    }
}

@Composable
fun EditPasswordScreen(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                navigateToSettings = navigateToSettings,
                showAboutDialog = {},
                isOnPreferencesScreen = false
            )
        },
        containerColor = colorResource(id = R.color.background_container),
        contentColor = colorResource(id = R.color.font_screen)
    ) {
        EditPasswordContainer(
            modifier = modifier.padding(it),
            onSaveClick = {}
        )
    }
}

@Composable
fun AddNewPasswordScreen(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                navigateToSettings = navigateToSettings,
                showAboutDialog = {},
                isOnPreferencesScreen = false
            )
        },
        containerColor = colorResource(id = R.color.background_container),
        contentColor = colorResource(id = R.color.font_screen)
    ) {
        AddNewPasswordContainer(
            modifier = modifier.padding(it),
            onSaveClick = {}
        )
    }
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun EditPasswordPreview() {
    PlainTextTheme {
        PlainTextTheme {
            Scaffold(
                topBar = {
                },
                containerColor = colorResource(id = R.color.background_container),
                contentColor = colorResource(id = R.color.font_screen)
            ) {
                EditPasswordScreen(
                    modifier = Modifier.padding(it),
                    navigateToSettings = {}
                )
            }
        }
    }
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun AddNewPasswordPreview() {
    PlainTextTheme {
        PlainTextTheme {
            Scaffold(
                topBar = {
                },
                containerColor = colorResource(id = R.color.background_container),
                contentColor = colorResource(id = R.color.font_screen)
            ) {
                AddNewPasswordScreen(
                    modifier = Modifier.padding(it),
                    navigateToSettings = {}
                )
            }
        }
    }
}

@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },

            title = { Text(text = "Sobre") },
            text = { Text(text = "PlainText Password Manager v1.0") },
            confirmButton = {
                Button(
                    onClick = { shouldShowDialog.value = false }
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }
}