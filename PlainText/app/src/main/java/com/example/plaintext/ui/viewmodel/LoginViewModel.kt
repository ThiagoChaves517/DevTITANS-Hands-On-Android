package com.example.plaintext.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// Classe de estado para a tela de login.
// Responsável gerenciar os estados da IU de login (View).
//
// Atributos:
//  - _loginUiState: MutableStateFlow<LoginUiState> - Armazena o estado da tela de login.
//  - loginUiState: StateFlow<LoginUiState> - Estado da tela de login para leitura.
//     A View pode ler e observar as mudanças no estado, mas não pode altera-los diretamente.
//
// Métodos:
//  - updateLogin(login: String): Atualiza o login de usuário.
//  - updatePassword(password: String): Atualiza a senha do usuário.
//  - updateIsChecked(isChecked: Boolean): Atualiza o estado da caixa de seleção
//     que habilita o salvamento das credencias de login.
//
// Hilt: Usado para injetar as dependências necessárias no App.
// @HiltViewModel:
//  Transforma a classe ViewModel em uma classe que o Hilt
//  pode injetar e gerenciar, respeitando o ciclo de vida do Tela.
// @Inject:
//  Informa o Hilt como criar uma instância da classe.
@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

//    init {
//        if (!loginUiState.value.isChecked) {
//
//            this.updateLogin(login = "")
//            this.updatePassword(password = "")
//        }
//    }
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    // Método para atualizar o login de usuário
    fun updateLogin(login: String) {
        /*
        Implementação Não Atômica
        NÃO RECOMENDADA por ser bloqueante
        pondendo causar uma condição de corrida (race condition).
        Isso resulta em dados inconsistentes e não atualizados (bugs difíceis de depurar).
        No entanto, é aceitável para estados simples.

        _loginUiState.value = _loginUiState.value.copy(login = login)
        */

        // Implementação Atômica
        // Executa operações atomicamente, garantindo que apenas uma thread
        // possa acessar o objeto ao mesmo tempo.
        _loginUiState.update { currentState ->
            currentState.copy(login = login)
        }
    }

    // Método para atualizar a senha do usuário
    fun updatePassword(password: String) {
        _loginUiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    // Método para atualizar o estado de aceitação que
    // habilita o salvamento das credencias de login
    fun updateIsChecked(isChecked: Boolean) {
        _loginUiState.update { currentState ->
            currentState.copy(isChecked = isChecked)
        }
    }
}