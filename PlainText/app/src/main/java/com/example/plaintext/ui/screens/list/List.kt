package com.example.plaintext.ui.screens.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plaintext.R
import com.example.plaintext.ui.viewmodel.ListViewModel
import com.example.plaintext.ui.viewmodel.ListViewState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.util.ListItem

@Composable
fun ListView(
    navigateToEdit: (password: PasswordInfo) -> Unit,
    listViewModel: ListViewModel = hiltViewModel()
) {
    val listViewState = listViewModel.listViewState

    Scaffold (
        floatingActionButton = {
            AddButton(onClick = {
                navigateToEdit(
                    PasswordInfo(0, "", "", "", "")
                )
            })
        },
        containerColor = colorResource(id = R.color.background_container),
        contentColor = colorResource(id = R.color.font_screen)
    ) {
        ListItemContent(
            modifier = Modifier.padding(it),
            listState = listViewState,
            navigateToEdit = navigateToEdit
        )
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = colorResource(id = R.color.login_button),
        contentColor = colorResource(id = R.color.font_screen),
        shape = RoundedCornerShape(50),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Small floating action button.",
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItemContent(
    modifier: Modifier,
    listState: ListViewState,
    navigateToEdit: (password: PasswordInfo) -> Unit
) {
    // usado para exibir uma mensagem de carregamento enquanto os dados são carregados
    when {
        !listState.isCollected -> {
            LoadingScreen()
        }
        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
            ) {
                items(listState.passwordList.size) {
                    ListItem(
                        password = listState.passwordList[it],
                        navigateToEdit = navigateToEdit
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Carregando")
    }
}