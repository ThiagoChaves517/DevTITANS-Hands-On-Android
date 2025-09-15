package com.example.plaintext.ui.viewmodel

// Classe de estado para a tela de login.
// Responsável por armazenar os dados da tela de login.
//
// Lógica do funcionamento da criação dos dados da tela de login::
//  Os dados da tela de login são criados como valores padrão vazios, o que significa que
//  quando a tela é carregada, os valores padrão são usados.
//  Quando o usuário digita algo na tela, os valores padrão são atualizados.
//  @ Obs.:
//    Além disso, tais valores são imutaveis e não podem ser alterados diretamente.
//    Para alterar tais valores, é necessário criar uma nova instância da classe com
//    os novos valores.
//
// Os dados armazenados são:
//  - login: String - Nome de usuário
//  - password: String - Senha do usuário
//  - isChecked: Boolean - Indica se o usuário marcou a caixa de seleção
data class LoginUiState (
    val login: String = "",
    val password: String = "",
    val isChecked: Boolean = false,
)