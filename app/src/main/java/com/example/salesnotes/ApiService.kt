package com.example.salesnotes

import com.example.salesnotes.data.*
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
interface CustomerService {
    @GET("/customer")
    suspend fun getAllCustomers(@Header("token") token: String): List<Customer>
}
interface StockService {
    @GET("/stock")
    suspend fun getAllStocks(): List<Stock>
}

interface OrderService {
    @POST("/checkout")
    suspend fun createOrder(@Body request: CheckoutRequest): Response<OrderResponse>
}