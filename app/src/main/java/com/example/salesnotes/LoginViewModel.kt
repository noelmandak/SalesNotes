package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.data.LoginRequest
import com.example.salesnotes.data.LoginResult
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username:String, password: String){
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(username, password)
                val response = RetrofitInstance.authService.login(loginRequest)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val idSales = loginResponse?.id
                    val name = loginResponse?.nama
                    val token = loginResponse?.encoded
                    val status = loginResponse?.status

                    _loginResult.value = LoginResult.Success(idSales, name, token, status)

                }
                else {
                    _loginResult.value = LoginResult.Error("Gagal login") // Atur pesan kesalahan yang sesuai
                }
            } catch (e : Exception) {
                _loginResult.value = LoginResult.Error("Terjadi kesalahan jaringan") // Atur pesan kesalahan yang sesuai

                if (isIPAddress(username)){
                    RetrofitInstance.changeBaseUrl("http://${username}:5000/")
                }
            }
        }
    }
    fun isIPAddress(input: String): Boolean {
        val ipPattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
        return ipPattern.matcher(input).matches()
    }
}