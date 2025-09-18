package com.example.plaintext.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.viewmodel.ListViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListView(
    listViewModel: ListViewModel = hiltViewModel(),
    onAddPassword: () -> Unit // Adicione essa função para a navegação
) {
    Scaffold(
        topBar = { TopBarComponent(title = "PlainText") },
        floatingActionButton = {
            AddButton(
                onAddClick = onAddPassword
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            val list = listOf(
                PasswordInfo("Twitter", "dev"),
                PasswordInfo("Facebook", "devtitans"),
                PasswordInfo("Moodle", "dev.com")
            )
            items(list) { passwordInfo ->
                ListItem(passwordInfo)
            }
        }
    }
}

@Composable
fun ListItem(passwordInfo: PasswordInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = passwordInfo.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = passwordInfo.login,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun AddButton(onAddClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onAddClick() }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Adicionar senha")
    }
}