package com.example.salesnotes.data

sealed class LoginResult {
    data class Success(val idSales: String?, val name: String?, val token: String?, val status: String?) : LoginResult()
    data class Error(val errorMessage: String) : LoginResult()
}