package com.example.plaintext.ui.viewmodel

// Classe de estado para a tela de configurações.
// Responsável por armazenar os dados da tela de configurações.
//
// Lógica do funcionamento da criação dos dados da tela de configurações::
//  Os dados da tela de configurações são criados sem valores padrão.
//
// Os dados armazenados são:
//  - login: String - Nome de usuário
//  - password: String - Senha do usuário
//  - preencher: Boolean - Indica se o usuário habilitou o salvamento das credencias de login
data class PreferencesState(
    val login: String,
    val password: String,
    val preencher: Boolean
)


