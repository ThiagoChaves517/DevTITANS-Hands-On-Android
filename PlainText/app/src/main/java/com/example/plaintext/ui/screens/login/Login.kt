package com.example.plaintext.ui.screens.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plaintext.R
import com.example.plaintext.ui.theme.PlainTextTheme
import com.example.plaintext.ui.viewmodel.LoginViewModel
import com.example.plaintext.ui.viewmodel.PreferencesViewModel

data class LoginState(
    val preencher: Boolean,
    val login: String,
    val navigateToSettings: () -> Unit,
    val navigateToList: (name: String) -> Unit,
    val checkCredentials: (login: String, password: String) -> Boolean,
)

@Composable
fun Login_screen(
    navigateToSettings: () -> Unit,
    navigateToList: () -> Unit,
    viewModel: PreferencesViewModel = hiltViewModel()
) {

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
    navigateToSettings: (() -> Unit?)? = null,
//    navigateToSensores: (() -> Unit?)? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog)
    }

    TopAppBar(
        title = { Text("PlainText", color = colorResource(id = R.color.white)) },
        actions = {
//            if (navigateToSettings != null && navigateToSensores != null) {
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

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlainTextTheme {
                Scaffold(
                    topBar = {
                        TopBarComponent()
                    },
                    containerColor = colorResource(id = R.color.black),
                    contentColor = colorResource(id = R.color.white)
                ) {
                    // Modifier.padding(it) é usado para adicionar padding ao conteúdo da tela
                    // para os casos em que a barra de navegação é exibida no Scaffold.
                    // Isso é útil para evitar que o conteúdo seja sobreposto pela barra de navegação.
                    LoginScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

// Composable que representa a pré-visualização da tela de login
@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun PreviewUILogin() {
    PlainTextTheme {
        PlainTextTheme {
            Scaffold (
                topBar = {
                    TopBarComponent()
                },
                containerColor = colorResource(id = R.color.black),
                contentColor = colorResource(id = R.color.white)
            ) {
                LoginScreen(
                    modifier = Modifier.padding(it),
                    viewModel = LoginViewModel())
            }
        }
    }
}

@Composable
// Tela de login
private fun LoginScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel() // Usando o Hilt
){
    // Recupera o estado da tela de login a partir do ViewModel.
    // Isso permiti que IU possa se recompor automaticamente quando o estado do ViewModel muda.
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        // Recebe as configurações de espaçamento para os elementos filhos da coluna.
        // Neste caso informações de preenchimento (padding) da barra superior e inferior
        // caso a barra de navegação esteja presente.
        // fillMaxSize é usado para preencher o espaço disponível na tela.
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ){
        /*
          Lógica descartada:
          Código foi implentado para aplicar a criação e gerenciamento de estados da
          tela de login (Composable) de forma local (dentro de função).
          Eram estados criados com remeber que poderiam ser perdidos quando
          a tela era rotacionada.
        */
//        var login by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//        var isChecked by remember { mutableStateOf(false) }

        CustomImageTextRow(
            contentDescription = "cabeça de robô Android",
            textResId = R.string.login_image_text
        )

        Text(
            text = stringResource(id = R.string.login_title).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 5.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            LoginInput(
                // Configura com o valor de login do estado da tela
                value = loginUiState.login,
                // Utiliza a chamada da função do ViewModel para atualizar o estado
                onValueChange = viewModel::updateLogin,
                label = stringResource(id = R.string.login_login),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Default.AccountBox
            )

            LoginInput(
                // Configura com o valor de senha do estado da tela
                value = loginUiState.password,
                // Utiliza a chamada da função do ViewModel para atualizar o estado
                onValueChange = viewModel::updatePassword,
                label = stringResource(id = R.string.login_senha),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                leadingIcon = Icons.Default.VpnKey,
                isPasswordInput = true
            )
        }

        CheckableRow(
            // Configura com o valor de checked do estado da tela
            checked = loginUiState.isChecked,
            onCheckedChange = viewModel::updateIsChecked,
            text = stringResource(id = R.string.login_isCheck),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            spacerWidth = 0.dp
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement  = Arrangement.Center
        ){
            ButtonWithIcons(
                modifier = Modifier.fillMaxWidth(0.90f),
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.login_button),
                leadingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIconRotation = 180f
            )
        }
    }
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
// Entradas de texto para o login e password
fun LoginInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector = Icons.Default.AccountBox,
    contentColor: Color = Color.White,
    isPasswordInput: Boolean = false
){
    val visualTransformation = if (isPasswordInput) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "Icone de Entradada de Texto"
            )},
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

@Composable
// Caixa de seleção de opção com texto
fun CheckableRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    spacerWidth: Dp = 5.dp,
    contentColor: Color = Color.White
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = contentColor,
                uncheckedColor = contentColor
            )
        )
        Spacer(Modifier.width(spacerWidth))
        Text(text = text)
    }
}

@Composable
// Botão com ícones à esquerda e à direita
fun ButtonWithIcons(
    onClick: () -> Unit,
    text: String,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?,
    modifier: Modifier = Modifier,
    leadingIconRotation: Float = 0f,
    trailingIconRotation: Float = 0f,
    spacerWidth: Dp = 8.dp,
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
            // Ícone da esquerda
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Ícone à esquerda do texto",
                    modifier = Modifier.rotate(leadingIconRotation)
                )
            }

            // Espaçamento e texto
            if (leadingIcon != null && trailingIcon != null) {
                Spacer(modifier = Modifier.width(spacerWidth))
            }

            Text(
                text = text.uppercase(),
                fontWeight = FontWeight.Bold
            )

            // Espaçamento e ícone da direita
            if (leadingIcon != null && trailingIcon != null) {
                Spacer(modifier = Modifier.width(spacerWidth))
            }
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "Ícone à direita do texto",
                    modifier = Modifier.rotate(trailingIconRotation)
                )
            }
        }
    }
}