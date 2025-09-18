package com.example.plaintext.ui.screens.editList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.Screen
import com.example.plaintext.ui.screens.login.TopBarComponent

data class EditListState(
    val nomeState: MutableState<String>,
    val usuarioState: MutableState<String>,
    val senhaState: MutableState<String>,
    val notasState: MutableState<String>,
)

fun isPasswordEmpty(password: PasswordInfo): Boolean {
    return password.name.isEmpty() && password.login.isEmpty() && password.password.isEmpty() && password.notes.isEmpty()
}

@Composable
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    savePassword: (password: PasswordInfo) -> Unit
) {
    val state = rememberSaveable {
        EditListState(
            nomeState = mutableStateOf(args.passwordInfo?.name ?: ""),
            usuarioState = mutableStateOf(args.passwordInfo?.login ?: ""),
            senhaState = mutableStateOf(args.passwordInfo?.password ?: ""),
            notasState = mutableStateOf(args.passwordInfo?.notes ?: "")
        )
    }

    Scaffold(
        topBar = { TopBarComponent(text = "Editar Senha", onClick = { navigateBack() }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EditInput(textInputLabel = "Nome", textInputState = state.nomeState)
            EditInput(textInputLabel = "Usuário", textInputState = state.usuarioState)
            EditInput(textInputLabel = "Senha", textInputState = state.senhaState)
            EditInput(textInputLabel = "Notas", textInputState = state.notasState, textInputHeight = 150)

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { navigateBack() }) {
                    Text("Cancelar")
                }
                Spacer(modifier = Modifier.width(30.dp))
                Button(
                    onClick = {
                        val newPasswordInfo = PasswordInfo(
                            id = args.passwordInfo?.id ?: 0,
                            name = state.nomeState.value,
                            login = state.usuarioState.value,
                            password = state.senhaState.value,
                            notes = state.notasState.value
                        )
                        savePassword(newPasswordInfo)
                    }
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}


@Composable
fun EditInput(
    textInputLabel: String,
    textInputState: MutableState<String> = mutableStateOf(""),
    textInputHeight: Int = 60
) {
    val padding: Int = 30

    var textState by rememberSaveable { textInputState }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(textInputHeight.dp)
            .padding(horizontal = padding.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(textInputLabel) },
            modifier = Modifier
                .height(textInputHeight.dp)
                .fillMaxWidth()
        )

    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true)
@Composable
fun EditListPreview() {
    EditList(
        Screen.EditList(PasswordInfo(1, "Nome", "Usuário", "Senha", "Notas")),
        navigateBack = {},
        savePassword = {}
    )
}

@Preview(showBackground = true)
@Composable
fun EditInputPreview() {
    EditInput("Nome")
}