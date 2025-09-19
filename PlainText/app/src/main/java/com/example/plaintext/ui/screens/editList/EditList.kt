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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun EditList(
    args: Screen.EditList, // Argumento recebido pela navegação
    navigateBack: () -> Unit,
    savePassword: (password: PasswordInfo) -> Unit
) {
    // Verificamos se o ID é 0. Se for, consideramos que é uma nova senha.
    // Esta é uma convenção comum.
    val isNewPassword = args.password.id == 0

    if (isNewPassword) {
        // Se for uma nova senha, exibe a tela de adição de senha.
        AddNewPasswordScreen(
            onSaveClick = { passwordInfo ->
                savePassword(passwordInfo)
            },
            navigateBack = navigateBack,
            navigateToSettings = {}
        )
    } else {
        // Se a senha já existir, mostramos a tela de edição, passando os dados.
        EditPasswordScreen(
            passwordInfo = args.password,
            onSaveClick = { passwordInfo ->
                savePassword(passwordInfo)
            },
            navigateBack = navigateBack,
            navigateToSettings = {}
        )
    }
}

@Composable
fun EditInput(
    textInputLabel: String,
    value: String, // Recebe o valor atual do estado
    onValueChange: (String) -> Unit, // Recebe a função para atualizar o estado
    //textInputState: MutableState<String> = mutableStateOf(""),
    textInputHeight: Int = 60,
    contentColor: Color = Color.White,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(textInputHeight.dp),
            //.padding(horizontal = padding.dp),
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
fun EditPasswordContainer(
    modifier: Modifier,
    passwordInfo: PasswordInfo,
    onSaveClick: (PasswordInfo) -> Unit,
    name: String,
    login: String,
    password: String,
    notes: String
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
                "Nome", value = name, onValueChange = { name }
            )
            EditInput(
                "Usuário", value = login, onValueChange = { login }
            )
            EditInput(
                "Senha", value = password, onValueChange = { password }
            )
            EditInput(
                "Notas", value = notes, onValueChange = { notes }
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
                onClick = {
                    val updatedPassword = passwordInfo.copy(
                        name = name,
                        login = login,
                        password = password,
                        notes = notes
                    )
                    onSaveClick(updatedPassword)
                },
                text = stringResource(id = R.string.save_button),
                leadingIcon = null,
                trailingIcon = null,
                trailingIconRotation = 180f
            )
        }
    }
}

@Composable
fun AddNewPasswordContainer(
    modifier: Modifier,
    onSaveClick: (PasswordInfo) -> Unit,
    name: String,
    login: String,
    password: String,
    notes: String
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
                "Nome", value = name, onValueChange = { name }
            )
            EditInput(
                "Usuário", value = login, onValueChange = { login }
            )
            EditInput(
                "Senha", value = password, onValueChange = { password }
            )
            EditInput(
                "Notas", value = notes, onValueChange = { notes }
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
                onClick = {
                    val updatedPassword = PasswordInfo(
                        id = 3, // Enquanto não houver um contador, IDs serão igual 3
                        name = name,
                        login = login,
                        password = password,
                        notes = notes
                    )
                    onSaveClick(updatedPassword)
                },
                text = stringResource(id = R.string.save_button),
                leadingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIconRotation = 180f
            )
        }
    }
}

@Composable
fun EditPasswordScreen(
    modifier: Modifier = Modifier,
    passwordInfo: PasswordInfo,
    onSaveClick: (PasswordInfo) -> Unit,
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit,
) {
    // Um estado para cada campo, inicializado com os valores da senha
    var name by rememberSaveable { mutableStateOf(passwordInfo.name) }
    var login by rememberSaveable { mutableStateOf(passwordInfo.login) }
    var password by rememberSaveable { mutableStateOf(passwordInfo.password) }
    var notes by rememberSaveable { mutableStateOf(passwordInfo.notes) }

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
            passwordInfo = passwordInfo,
            onSaveClick = onSaveClick,
            name = name,
            login = login,
            password = password,
            notes = notes
        )
    }
}

@Composable
fun AddNewPasswordScreen(
    modifier: Modifier = Modifier,
    onSaveClick: (PasswordInfo) -> Unit,
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit,
) {
    // Um estado para cada campo, inicializado com os valores da senha
    var name by rememberSaveable { mutableStateOf("") }
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

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
            onSaveClick = onSaveClick,
            name = name,
            login = login,
            password = password,
            notes = notes
        )
    }
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun EditPasswordPreview() {
    PlainTextTheme {
        PlainTextTheme {
            var name by rememberSaveable { mutableStateOf("Thiago") }
            var login by rememberSaveable { mutableStateOf("ShadowX25") }
            var password by rememberSaveable { mutableStateOf("1234") }
            var notes by rememberSaveable { mutableStateOf("Senha para testes") }

            Scaffold(
                topBar = {
                    TopBarComponent(
                        navigateToSettings = {},
                        showAboutDialog = {},
                        isOnPreferencesScreen = false
                    )
                },
                containerColor = colorResource(id = R.color.background_container),
                contentColor = colorResource(id = R.color.font_screen)
            ) {
                // Fake instance of PasswordInfo, so we can pass it to the EditPasswordContainer
                val passwordInfo = PasswordInfo(0, name, login, password, notes)

                EditPasswordContainer(
                    modifier = Modifier.padding(it),
                    passwordInfo = passwordInfo,
                    onSaveClick = {},
                    name = name,
                    login = login,
                    password = password,
                    notes = notes
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
            var name by rememberSaveable { mutableStateOf("") }
            var login by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var notes by rememberSaveable { mutableStateOf("") }

            Scaffold(
                topBar = {
                    TopBarComponent(
                        navigateToSettings = {},
                        showAboutDialog = {},
                        isOnPreferencesScreen = false
                    )
                },
                containerColor = colorResource(id = R.color.background_container),
                contentColor = colorResource(id = R.color.font_screen)
            ) {
                AddNewPasswordContainer(
                    modifier = Modifier.padding(it),
                    onSaveClick = {},
                    name = name,
                    login = login,
                    password = password,
                    notes = notes
                )
            }
        }
    }
}