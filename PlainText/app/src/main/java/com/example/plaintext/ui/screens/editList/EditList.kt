package com.example.plaintext.ui.screens.editList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plaintext.R
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.Screen
import com.example.plaintext.ui.screens.login.ButtonWithIcons
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.theme.PlainTextTheme
import com.example.plaintext.ui.viewmodel.EditListViewModel

@Composable
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    viewModel: EditListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()

    LaunchedEffect(uiState.isPasswordSaved) {
        if (uiState.isPasswordSaved) {
            navigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopBarComponent()
        },
        containerColor = colorResource(id = R.color.black),
        contentColor = colorResource(id = R.color.white)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(8.dp)
        ) {
            CustomImageTextRow(
                contentDescription = "cabeça de robô Android",
                textResId = R.string.login_image_text
            )

            Text(
                text = stringResource(
                    id = if (uiState.isNewPassword) R.string.editlist_adding_new_password
                    else R.string.editlist_editing_password
                ).uppercase(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 5.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )

            EditInput(
                modifier = Modifier.fillMaxWidth(),
                textInputLabel = "Nome",
                value = uiState.name,
                onValueChange = { viewModel.updateName(it) }
            )
            EditInput(
                modifier = Modifier.fillMaxWidth(),
                textInputLabel = "Usuário",
                value = uiState.login,
                onValueChange = { viewModel.updateLogin(it) }
            )
            EditInput(
                modifier = Modifier.fillMaxWidth(),
                textInputLabel = "Senha",
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) }
            )
            EditInput(
                modifier = Modifier.fillMaxWidth(),
                textInputLabel = "Notas",
                value = uiState.notes,
                onValueChange = { viewModel.updateNotes(it) }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonWithIcons(
                    modifier = Modifier.fillMaxWidth(0.90f),
                    onClick = { viewModel.savePassword() },
                    text = stringResource(id = R.string.save_button),
                    leadingIcon = Icons.AutoMirrored.Filled.Login,
                    trailingIcon = Icons.AutoMirrored.Filled.Login,
                    trailingIconRotation = 180f
                )
            }
        }
    }
}

@Composable
fun EditInput(
    modifier: Modifier,
    textInputLabel: String,
    value: String,
    onValueChange: (String) -> Unit,
    textInputHeight: Int = 60,
    contentColor: Color = Color.White,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(textInputHeight.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
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
fun CustomImageTextRow(
    backgroundColor: Color = colorResource(id = R.color.login_row_background),
    imageResId: Int = R.drawable.ic_launcher_foreground,
    contentDescription: String,
    textResId: Int,
    imageStartPadding: Dp = 25.dp,
    textImageSpacerWidth: Dp = 8.dp,
    textEndPadding: Dp = 25.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.padding(start = imageStartPadding)
        )

        Spacer(Modifier.width(textImageSpacerWidth))

        Text(
            text = stringResource(id = textResId),
            modifier = Modifier.padding(end = textEndPadding)
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun EditPasswordScreen(
    modifier: Modifier
) {
    EditList(
        Screen.EditList(PasswordInfo(1, "Nome", "Usuário", "Senha", "Notas")),
        navigateBack = {}
    )
}

@Composable
fun AddNewPasswordScreen(
    modifier: Modifier
) {
    EditList(
        Screen.EditList(PasswordInfo(0, "", "", "", "")),
        navigateBack = {}
    )
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun EditPasswordPreview() {
    PlainTextTheme {
        Scaffold (
            topBar = {
                TopBarComponent()
            },
            containerColor = colorResource(id = R.color.black),
            contentColor = colorResource(id = R.color.white)
        ) {
            EditPasswordScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun AddNewPasswordPreview() {
    PlainTextTheme {
        Scaffold (
            topBar = {
                TopBarComponent()
            },
            containerColor = colorResource(id = R.color.black),
            contentColor = colorResource(id = R.color.white)
        ) {
            AddNewPasswordScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}