package com.example.salesnotes

import com.example.salesnotes.data.Customer
import com.example.salesnotes.data.LoginRequest
import com.example.salesnotes.data.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

//interface ApiService {
//    @GET("/users")
//    suspend fun getUsers(): Response<List<User>>
//}

interface AuthService {
    @POST("/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
interface ApiService {
    @GET("/customer")
    fun getAllCustomers(@Header("token") token: String): Call<List<Customer>>
}