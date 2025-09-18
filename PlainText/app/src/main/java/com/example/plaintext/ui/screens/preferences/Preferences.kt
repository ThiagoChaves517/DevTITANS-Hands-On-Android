package com.example.plaintext.ui.screens.preferences

import android.annotation.SuppressLint
import com.example.plaintext.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.screens.util.PreferenceInput
import com.example.plaintext.ui.screens.util.PreferenceItem
import com.example.plaintext.ui.viewmodel.PreferencesState
import com.example.plaintext.ui.viewmodel.PreferencesViewModel

@Composable
fun SettingsScreen(
//    navController: NavHostController?,
    navigateToLogin: () -> Unit,
    viewModel: PreferencesViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopBarComponent(
//                navigateToSettings = { navController?.navigateUp() },
                navigateToSettings = navigateToLogin,
                showAboutDialog = {},
                isOnPreferencesScreen = true
            )
        },
        containerColor = colorResource(id = R.color.background_container),
        contentColor = colorResource(id = R.color.font_screen)
    ){ padding ->
        SettingsContent(
            modifier = Modifier.padding(padding),
            viewModel
        )
    }
}

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    viewModel: PreferencesViewModel
) {
    // Acessa o estado diretamente do ViewModel
    val preferencesState = viewModel.preferencesState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())){

        PreferenceInput(
            title = stringResource(id = R.string.preferences_input_login_title),
            label = stringResource(id = R.string.preferences_input_login_label),
            fieldValue = preferencesState.login,
            summary = stringResource(id = R.string.preferences_input_login_summary)
        ){
            // Chama a função do ViewModel para atualizar o login
            viewModel.updateLogin(it)
        }

        PreferenceInput(
            title = stringResource(id = R.string.preferences_input_password_title),
            label = stringResource(id = R.string.preferences_input_password_label),
            fieldValue = preferencesState.password,
            summary = stringResource(id = R.string.preferences_input_password_summary)
        ){
            // Chama a função do ViewModel para atualizar a senha
            viewModel.updatePassword(it)
        }

        PreferenceItem(
            title = stringResource(id = R.string.preferences_item_title),
            summary = stringResource(id = R.string.preferences_item_summary),
            onClick = {
                // chama a função do ViewModel para atualizar o estado do switch para
                // o valor de habilitado ou deshabilitado
                viewModel.updatePreencher(!preferencesState.preencher)
            },
            control = {
                Switch(
                    // configura o estado do switch com o valor do estado atual do ViewModel
                    checked = preferencesState.preencher,
                    onCheckedChange = {
                        // chama a função do ViewModel para atualizar o estado do switch
                        viewModel.updatePreencher(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = colorResource(id = R.color.login_button),
                        checkedThumbColor = colorResource(id = R.color.font_screen),
                        uncheckedTrackColor = colorResource(id = R.color.login_button),
                        uncheckedThumbColor = colorResource(id = R.color.background_container),
                        uncheckedBorderColor = colorResource(id = R.color.font_screen),
                    )
                )
            }
        )
    }
}

// Usada no preview da tela de configurações porque é utilizado @HiltViewModel
// que não consegue fazer a injeção de dependência no preview.
// Então, é fornecido uma ViewModel fake para o preview. (mock)
class FakePreferencesViewModel() : PreferencesViewModel(
    SavedStateHandle()
){
    var fakePreferencesState by mutableStateOf(
        PreferencesState(
            login = "previsão",
            password = "senha123",
            preencher = true)
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        navigateToLogin = {},
        viewModel = FakePreferencesViewModel()
    )
}