package com.example.plaintext.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.data.model.toPassword
import com.example.plaintext.data.repository.PasswordDBStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditListViewModel @Inject constructor(
    private val passwordDBStore: PasswordDBStore,
    // SavedStateHandle é usado para recuperar os argumentos de navegação
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Função para salvar ou atualizar a senha na base de dados
    fun savePassword(passwordInfo: PasswordInfo) {
        viewModelScope.launch {
            // A sua entidade do Room é 'Password', então convertemos 'PasswordInfo'
            if (passwordInfo.id == 0) {
                // Se o ID for 0, é uma nova senha, então inserimos
                passwordDBStore.add(passwordInfo.toPassword())
            } else {
                // Caso contrário, atualizamos a existente
                passwordDBStore.update(passwordInfo.toPassword())
            }
        }
    }
}