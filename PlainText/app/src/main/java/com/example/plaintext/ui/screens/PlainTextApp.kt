package com.example.plaintext.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.editList.EditList
import com.example.plaintext.ui.screens.hello.Hello_screen
import com.example.plaintext.ui.screens.list.ListView
import com.example.plaintext.ui.screens.login.LoginScreen
import com.example.plaintext.ui.screens.preferences.SettingsScreen
import com.example.plaintext.ui.viewmodel.EditListViewModel
import com.example.plaintext.ui.viewmodel.LoginViewModel
import com.example.plaintext.ui.viewmodel.PreferencesViewModel
import com.example.plaintext.utils.parcelableType
import kotlin.reflect.typeOf

@Composable
fun PlainTextApp(
    appState: JetcasterAppState = rememberJetcasterAppState()
) {
    val preferencesViewModel: PreferencesViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()

    NavHost(
        navController = appState.navController,
        startDestination = Screen.Login
    )
    {
        composable<Screen.Hello>{
            var args = it.toRoute<Screen.Hello>()
            Hello_screen(args)
        }
        composable<Screen.Login>{
            LoginScreen(
                navigateToSettings = { appState.navigateToSettings() },
                navigateToList = { appState.navigateToList("dummyName") }, // 'dummyName' é um placeholder
                loginViewModel = loginViewModel,
                preferencesViewModel = preferencesViewModel
            )
        }
        // Construtor de rotas para a tela de configurações
        // Screen.Preferences: Objeto serializável que associa uma rota a um Composable.
        composable<Screen.Preferences> {
            SettingsScreen(
                navigateToLogin = { appState.navigateToLogin() },
                viewModel = preferencesViewModel
            )
        }
        // Construtor de rotas para a tela de Lista de senhas
        // Screen.List: Objeto serializável que associa uma rota a um Composable.
        composable<Screen.List> {
            ListView()
        }
        composable<Screen.EditList>(
            typeMap = mapOf(typeOf<PasswordInfo>() to parcelableType<PasswordInfo>())
        ) {
            val args = it.toRoute<Screen.EditList>()
            EditList(
                args,
                navigateBack = {},
                viewModel = hiltViewModel()
            )
        }

    }
}