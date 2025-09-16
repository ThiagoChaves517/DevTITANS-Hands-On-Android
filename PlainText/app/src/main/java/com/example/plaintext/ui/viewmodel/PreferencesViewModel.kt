package com.example.plaintext.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Classe de estado para a tela de configurações.
// Responsável por armazenar os dados da tela de configurações.
//
// Atributos:
//  - preferencesState: PreferencesState - Armazena o estado da tela de configurações.
//
// Métodos:
//  - updateLogin(login: String): Atualiza o login de usuário.
//  - updatePassword(password: String): Atualiza a senha do usuário.
//  - updatePreencher(preencher: Boolean): Atualiza o estado que habilita o
//     salvamento das credencias de login.
//  - checkCredentials(login: String, password: String): Verifica se as credenciais são válidas.
//
// Hilt: Usado para injetar as dependências necessárias no App.
@HiltViewModel
open class PreferencesViewModel @Inject constructor(
    handle: SavedStateHandle
) : ViewModel() {
    var preferencesState by mutableStateOf(PreferencesState(login = "devtitans", password = "123", preencher = true))
        private set

    fun updateLogin(login: String) {
        preferencesState = preferencesState.copy(login = login)
    }

    fun updatePassword(password: String) {
        preferencesState = preferencesState.copy(password = password)
    }

    fun updatePreencher(preencher: Boolean) {
        preferencesState = preferencesState.copy(preencher = preencher)
    }

    fun checkCredentials(login: String, password: String): Boolean{
        return login == preferencesState.login && password == preferencesState.password
    }
}