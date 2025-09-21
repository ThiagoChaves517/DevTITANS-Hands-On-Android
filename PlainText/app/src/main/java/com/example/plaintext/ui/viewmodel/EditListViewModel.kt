package com.example.plaintext.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.data.model.toPasswordInfo
import com.example.plaintext.data.repository.PasswordDBStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditListViewModel @Inject constructor(
    private val passwordDBStore: PasswordDBStore,
    // SavedStateHandle é usado para recuperar os argumentos de navegação
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getPasswordInfo(id: Int): Flow<PasswordInfo?> {
        return flow {
            // Busque o item do banco de dados usando o ID
            val password = passwordDBStore.get(id)
            emit(password?.toPasswordInfo()) // Converta para PasswordInfo
        }
    }

    // Função para salvar ou atualizar a senha na base de dados
    fun savePassword(passwordInfo: PasswordInfo) {
        viewModelScope.launch {
            passwordDBStore.save(passwordInfo)
        }
    }
}