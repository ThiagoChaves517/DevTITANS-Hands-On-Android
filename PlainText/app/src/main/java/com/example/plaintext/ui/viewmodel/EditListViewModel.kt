package com.example.plaintext.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.data.model.toPassword
import com.example.plaintext.data.repository.PasswordDBStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EditListUiState(
    val id: Int = 0,
    val name: String = "",
    val login: String = "",
    val password: String = "",
    val notes: String = "",
    val isPasswordSaved: Boolean = false,
    val isNewPassword: Boolean = true
)

@HiltViewModel
class EditListViewModel @Inject constructor(
    private val passwordDBStore: PasswordDBStore
) : ViewModel() {

    private val _uiState = mutableStateOf(EditListUiState())
    val uiState: State<EditListUiState> = _uiState

    fun setPassword(password: PasswordInfo) {
        _uiState.value = _uiState.value.copy(
            id = password.id,
            name = password.name,
            login = password.login,
            password = password.password,
            notes = password.notes,
            isNewPassword = password.name.isEmpty() && password.login.isEmpty()
        )
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateLogin(login: String) {
        _uiState.value = _uiState.value.copy(login = login)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun updateNotes(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun savePassword() {
        viewModelScope.launch {
            val passwordInfo = PasswordInfo(
                id = uiState.value.id,
                name = uiState.value.name,
                login = uiState.value.login,
                password = uiState.value.password,
                notes = uiState.value.notes
            )
            if (uiState.value.isNewPassword) {
                passwordDBStore.add(passwordInfo.toPassword())
            } else {
                passwordDBStore.update(passwordInfo.toPassword())
            }
            _uiState.value = _uiState.value.copy(isPasswordSaved = true)
        }
    }
}