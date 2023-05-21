package com.example.salesnotes

import com.example.salesnotes.data.*
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
interface ItemService {
    @GET("/item")
    suspend fun getAllItems(): List<Item>
}
interface ApiService {
    @GET("/customer")
    fun getAllCustomers(@Header("token") token: String): Call<List<Customer>>
}
interface StockApiService {
    @GET("/stock")
    fun getAllStocks(token: String): Call<List<Stock>>
}