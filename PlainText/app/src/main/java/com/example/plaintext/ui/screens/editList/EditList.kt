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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.plaintext.R
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.Screen
import com.example.plaintext.ui.theme.PlainTextTheme


@Composable
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    savePassword: (password: PasswordInfo) -> Unit
) {
    val isNewPassword = args.password.id == 0
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf(args.password.name) }
    var login by remember { mutableStateOf(args.password.login) }
    var password by remember { mutableStateOf(args.password.password) }
    var notes by remember { mutableStateOf(args.password.notes) }

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
                    id = if (isNewPassword) R.string.editlist_adding_new_password
                    else R.string.editlist_editing_password
                ).uppercase(),
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
                    textInputState = remember { mutableStateOf(name) },
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                EditInput(
                    "Usuário",
                    textInputState = remember { mutableStateOf(login) },
                    onValueChange = { login = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                EditInput(
                    "Senha",
                    textInputState = remember { mutableStateOf(password) },
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                EditInput(
                    "Notas",
                    textInputState = remember { mutableStateOf(notes) },
                    onValueChange = { notes = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                SaveButton(
                    modifier = Modifier.fillMaxWidth(0.90f),
                    onClick = {
                        val updatedPassword = args.password.copy(
                            name = name,
                            login = login,
                            password = password,
                            notes = notes
                        )
                        savePassword(updatedPassword)
                        navigateBack()
                    },
                    text = stringResource(id = R.string.save_button)
                )
            }
        }
    }
}

@Composable
fun EditInput(
    textInputLabel: String,
    textInputState: MutableState<String> = mutableStateOf(""),
    onValueChange: (String) -> Unit,
    textInputHeight: Int = 60,
    modifier: Modifier,
    contentColor: Color = Color.White
) {
    val padding: Int = 30

    var textState by rememberSaveable { textInputState }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(textInputHeight.dp)
            .padding(horizontal = padding.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
                onValueChange(it)
            },
            label = { Text(textInputLabel) },
            modifier = modifier,
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
fun EditPasswordScreen(
    modifier: Modifier,
) {
    EditList(
        Screen.EditList(PasswordInfo(1, "Nome", "Usuário", "Senha", "Notas")),
        navigateBack = {},
        savePassword = {}
    )
}

@Composable
fun AddNewPasswordScreen(
    modifier: Modifier,
) {
    EditList(
        Screen.EditList(PasswordInfo(0, "", "", "", "")),
        navigateBack = {},
        savePassword = {}
    )
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun EditPasswordPreview() {
    PlainTextTheme {
        PlainTextTheme {
            Scaffold(
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
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun AddNewPasswordPreview() {
    PlainTextTheme {
        PlainTextTheme {
            Scaffold(
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarComponent(
    navigateToSettings: (() -> Unit?)? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog)
    }

    TopAppBar(
        title = { Text("PlainText", color = colorResource(id = R.color.white)) },
        actions = {

            if (navigateToSettings != null) {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = colorResource(id = R.color.white)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(colorResource(id = R.color.black))
                ) {
                    DropdownMenuItem(
                        text = { Text("Configurações") },
                        onClick = {
                            navigateToSettings();
                            expanded = false;
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Sobre");
                        },
                        onClick = {
                            shouldShowDialog.value = true;
                            expanded = false;
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.black)
        )
    )
}

@Composable
// Composable que representa uma linha com uma imagem e um texto
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
// Botão com ícones à esquerda e à direita
fun SaveButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = colorResource(id = R.color.login_button)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.uppercase(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}