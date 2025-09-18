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
<<<<<<< HEAD
=======
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
>>>>>>> 36d9457 (New Solutions)
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
<<<<<<< HEAD
=======
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
>>>>>>> 36d9457 (New Solutions)
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
<<<<<<< HEAD
=======
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
>>>>>>> 36d9457 (New Solutions)
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plaintext.R
import com.example.plaintext.data.model.Password
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.data.repository.PasswordDBStore
import com.example.plaintext.ui.screens.Screen
<<<<<<< HEAD
import com.example.plaintext.ui.screens.login.ButtonWithIcons
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.theme.PlainTextTheme
import com.example.plaintext.ui.viewmodel.EditListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
=======
import com.example.plaintext.ui.screens.login.LoginInput
import com.example.plaintext.ui.theme.PlainTextTheme
>>>>>>> 36d9457 (New Solutions)

@Composable
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    viewModel: EditListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()

    // *** BUG FIX: This loads the password data into the ViewModel when the screen is shown ***
    LaunchedEffect(key1 = args.password) {
        viewModel.setPassword(args.password)
    }

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
<<<<<<< HEAD
=======
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    savePassword: (password: PasswordInfo) -> Unit
) {

}

@Composable
fun EditInput(
    textInputLabel: String,
    textInputState: MutableState<String> = mutableStateOf(""),
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
            onValueChange = { textState = it },
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
    val scrollState = rememberScrollState()

    EditList(
        Screen.EditList(PasswordInfo(1, "Nome", "Usuário", "Senha", "Notas")),
        navigateBack = {},
        savePassword = {}
    )

    Column (
        modifier = modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        CustomImageTextRow(
            contentDescription = "cabeça de robô Android",
            textResId = R.string.login_image_text
        )

        Text(
            text = stringResource(id = R.string.editlist_editing_password).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 5.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            EditInput("Nome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            EditInput("Usuário",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            EditInput("Senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            EditInput("Notas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement  = Arrangement.Center
        ){
            SaveButton(
                modifier = Modifier.fillMaxWidth(0.90f),
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.save_button)
            )
        }
    }
}

@Composable
fun AddNewPasswordScreen(
    modifier: Modifier,
) {
    val scrollState = rememberScrollState()

    Column (
        modifier = modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        CustomImageTextRow(
            contentDescription = "cabeça de robô Android",
            textResId = R.string.login_image_text
        )

        Text(
            text = stringResource(id = R.string.editlist_adding_new_password).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 5.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            EditInput("Nome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            EditInput("Usuário",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            EditInput("Senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            EditInput("Notas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement  = Arrangement.Center
        ){
            SaveButton(
                modifier = Modifier.fillMaxWidth(0.90f),
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.save_button)
            )
        }
    }
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun EditPasswordPreview() {
    PlainTextTheme {
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
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun AddNewPasswordPreview() {
    PlainTextTheme {
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
>>>>>>> 36d9457 (New Solutions)
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

<<<<<<< HEAD
// --- PREVIEW FIXES ---

/**
 * A fake implementation of the PasswordDBStore for previewing purposes.
 */
private class FakePasswordDBStore : PasswordDBStore {
    override fun getList(): Flow<List<Password>> = flowOf(emptyList())
    override suspend fun add(password: Password): Long = 0
    override suspend fun update(password: Password) {}
    override fun get(id: Int): Password? = null
    override suspend fun save(passwordInfo: PasswordInfo) {}
    override suspend fun isEmpty(): Flow<Boolean> = flowOf(true)
}

@Composable
private fun EditPasswordScreen(
    modifier: Modifier,
    viewModel: EditListViewModel
) {
    EditList(
        Screen.EditList(PasswordInfo(1, "Nome", "Usuário", "Senha", "Notas")),
        navigateBack = {},
        viewModel = viewModel
    )
}

@Composable
private fun AddNewPasswordScreen(
    modifier: Modifier,
    viewModel: EditListViewModel
) {
    EditList(
        Screen.EditList(PasswordInfo(0, "", "", "", "")),
        navigateBack = {},
        viewModel = viewModel
    )
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
private fun EditPasswordPreview() {
    val fakeViewModel = EditListViewModel(FakePasswordDBStore())
    PlainTextTheme {
        Scaffold (
            topBar = {
                TopBarComponent()
            },
            containerColor = colorResource(id = R.color.black),
            contentColor = colorResource(id = R.color.white)
        ) {
            EditPasswordScreen(
                modifier = Modifier.padding(it),
                viewModel = fakeViewModel
            )
        }
    }
}

@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
private fun AddNewPasswordPreview() {
    val fakeViewModel = EditListViewModel(FakePasswordDBStore())
    PlainTextTheme {
        Scaffold (
            topBar = {
                TopBarComponent()
            },
            containerColor = colorResource(id = R.color.black),
            contentColor = colorResource(id = R.color.white)
        ) {
            AddNewPasswordScreen(
                modifier = Modifier.padding(it),
                viewModel = fakeViewModel
=======
@Composable
// Botão com ícones à esquerda e à direita
fun SaveButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = colorResource(id =  R.color.login_button)
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
>>>>>>> 36d9457 (New Solutions)
            )
        }
    }
}