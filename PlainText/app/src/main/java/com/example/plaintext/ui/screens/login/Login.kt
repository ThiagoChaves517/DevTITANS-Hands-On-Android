package com.example.plaintext.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.plaintext.ui.theme.PlainTextTheme
import com.example.plaintext.ui.viewmodel.LoginUiState
import com.example.plaintext.ui.viewmodel.LoginViewModel
import com.example.plaintext.ui.viewmodel.PreferencesState
import com.example.plaintext.ui.viewmodel.PreferencesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.Composable
import com.example.plaintext.R
import com.example.plaintext.ui.screens.util.AboutDialog
import com.example.plaintext.ui.screens.util.CheckableRow
import com.example.plaintext.ui.screens.util.CustomButton
import com.example.plaintext.ui.screens.util.CustomImageTextRow
import com.example.plaintext.ui.screens.util.LoginInput
import com.example.plaintext.ui.screens.util.TopBarComponent

@Composable
fun LoginScreen(
    navigateToSettings: () -> Unit,
    navigateToList: () -> Unit,
    loginViewModel: LoginViewModel,
    preferencesViewModel: PreferencesViewModel,
) {
    // Variáveis de estado para controlar os diálogos
    var showAboutDialog by rememberSaveable { mutableStateOf(false) }

    // Coleta os estados dos ViewModels
    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val preferencesState = preferencesViewModel.preferencesState

    // Estado local para a tela
    var isLoginFailed by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (!loginUiState.isChecked) {
            loginViewModel.updateLogin("")
            loginViewModel.updatePassword("")
        }

        if (preferencesState.preencher) {
            loginViewModel.updateLogin(preferencesState.login)
        }
    }

    AboutDialog(
        isShowing = showAboutDialog,
        onDismissRequest = { showAboutDialog = false }
    )

    Scaffold(
        topBar = {
            TopBarComponent(
                navigateToSettings = navigateToSettings,
                showAboutDialog = { showAboutDialog = true },
                isOnPreferencesScreen = false
            )
        },
        containerColor = colorResource(id = R.color.background_container),
        contentColor = colorResource(id = R.color.font_screen)
    ) {
        LoginContainer(
            modifier = Modifier.padding(it),
            loginUiState = loginUiState,
            onUpdateLogin = loginViewModel::updateLogin,
            onUpdatePassword = loginViewModel::updatePassword,
            onUpdateIsChecked = loginViewModel::updateIsChecked,
            onLoginClick = {
                // A LÓGICA DE VERIFICAÇÃO DE LOGIN E SENHA
                if (preferencesViewModel.checkCredentials(
                        loginUiState.login,
                        loginUiState.password
                    )
                ) {
                    navigateToList()
                    isLoginFailed = false // Reseta o estado em caso de sucesso
                } else {
                    isLoginFailed = true // Atualiza o estado da tela em caso de falha
                }
            },
            isLoginFailed = isLoginFailed, // Passa o estado do erro para o componente filho
            onDismissLoginFailedDialog = { isLoginFailed = false } // Passa o callback para o filho
        )
    }
}

@Composable
// Tela de login
fun LoginContainer(
    modifier: Modifier,
    loginUiState: LoginUiState,
    onUpdateLogin: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onUpdateIsChecked: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
    isLoginFailed: Boolean, // Recebe o estado do erro do pai
    onDismissLoginFailedDialog: () -> Unit // Recebe o callback para fechar o diálogo
){

    val scrollState = rememberScrollState()

    Column(
        // Recebe as configurações de espaçamento para os elementos filhos da coluna.
        // Neste caso informações de preenchimento (padding) da barra superior e inferior
        // caso a barra de navegação esteja presente.
        // fillMaxSize é usado para preencher o espaço disponível na tela.
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ){
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

        LoginInput(
            value = loginUiState.login,
            onValueChange = onUpdateLogin,
            label = stringResource(id = R.string.login_login),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Default.AccountBox
        )

        LoginInput(
            value = loginUiState.password,
            onValueChange = onUpdatePassword,
            label = stringResource(id = R.string.login_senha),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.VpnKey,
            isPasswordInput = true
        )

        CheckableRow(
            checked = loginUiState.isChecked,
            onCheckedChange = onUpdateIsChecked,
            text = stringResource(id = R.string.login_isCheck),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            spacerWidth = 0.dp
        )

        if (isLoginFailed) {
            AlertDialog(
                onDismissRequest = onDismissLoginFailedDialog,
                title = { Text("Falha no Login") },
                text = { Text("Login ou senha incorretos. Por favor, tente novamente.") },
                confirmButton = {
                    TextButton(onClick = onDismissLoginFailedDialog) {
                        Text("OK")
                    }
                }
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement  = Arrangement.Center
        ){
            CustomButton(
                modifier = Modifier.fillMaxWidth(0.90f),
                onClick = onLoginClick,
                text = stringResource(id = R.string.login_button),
                leadingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIcon = Icons.AutoMirrored.Filled.Login,
                trailingIconRotation = 180f
            )
        }
    }
}

class FakeLoginViewModel() : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState(login = "devtitans", password = "senha123", isChecked = true))
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()
    fun updateLogin(login: String) {}
    fun updatePassword(password: String) {}
    fun updateIsChecked(isChecked: Boolean) {}
}

class FakePreferencesViewModel() : PreferencesViewModel(
    SavedStateHandle()
) {
    var fakePreferencesState by mutableStateOf(
        PreferencesState(
            login = "previsão",
            password = "senha123",
            preencher = true
        )
    )
}

// Composable que representa a pré-visualização da tela de login
@SuppressLint("ViewModelConstructorInComposable")
@Preview(name = "Modo Retrato", widthDp = 320)
@Preview(name = "Modo Paisagem", widthDp = 640)
@Composable
fun PreviewUILogin() {
    PlainTextTheme {
        PlainTextTheme {
            Scaffold (
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
                LoginContainer(
                    modifier = Modifier.padding(it),
                    loginUiState = LoginUiState(
                        login = "devtitans",
                        password = "senha123",
                        isChecked = true
                    ),
                    onUpdateLogin = {},
                    onUpdatePassword = {},
                    onUpdateIsChecked = {},
                    onLoginClick = {},
                    isLoginFailed = false,
                    onDismissLoginFailedDialog = {}
                )
            }
        }
    }
}