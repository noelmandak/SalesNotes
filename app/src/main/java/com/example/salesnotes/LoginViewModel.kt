package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.data.LoginRequest
import com.example.salesnotes.data.LoginResult
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username:String, password: String){
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(username, password)
                val response = RetrofitInstance.authService.login(loginRequest)
                response
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse
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
                e
                _loginResult.value = LoginResult.Error("Terjadi kesalahan jaringan") // Atur pesan kesalahan yang sesuai
            }
        }
    }
    private fun saveToken(token: String?) {
        // Simpan token di penyimpanan yang sesuai (misalnya SharedPreferences)
    }

}