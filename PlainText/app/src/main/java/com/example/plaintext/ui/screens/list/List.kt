package com.example.plaintext.ui.screens.list

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plaintext.R
import com.example.plaintext.ui.viewmodel.ListViewModel
import com.example.plaintext.ui.viewmodel.ListViewState
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Android
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.util.AndroidIconWithRoundedImageBackground
import com.example.plaintext.ui.screens.util.ListItem

//Lista fake para teste
val fakePasswordList = listOf(
    PasswordInfo(
        id = 1,
        name = "Netflix",
        login = "joao.silva@email.com",
        password = "senha123",
        notes = "Assinatura mensal"
    ),
    PasswordInfo(
        id = 2,
        name = "Google",
        login = "joao.silva@gmail.com",
        password = "senha-google",
        notes = "Autenticação em dois fatores ativada"
    ),
    PasswordInfo(
        id = 3,
        name = "Github",
        login = "joao.dev",
        password = "senha-github",
        notes = "Usar com cuidado"
    )
)

@Composable
fun ListView() {
    Scaffold (
        floatingActionButton = {
            AddButton(onClick = { })
        },
        containerColor = colorResource(id = R.color.background_container),
        contentColor = colorResource(id = R.color.font_screen)
    ) {
        ListItemContent(
            modifier = Modifier.padding(it),
            listState = ListViewState(fakePasswordList, true),
            navigateToEdit = { }
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
                        listState.passwordList[it],
                        navigateToEdit
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