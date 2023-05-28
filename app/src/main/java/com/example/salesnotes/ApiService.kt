package com.example.salesnotes

import com.example.salesnotes.data.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

//interface ApiService {
//    @GET("/users")
//    suspend fun getUsers(): Response<List<User>>
//}
class ApiService {

    private lateinit var retrofit: Retrofit

    fun changeBaseUrl(newBaseUrl: String) {
        retrofit = Retrofit.Builder()
            .baseUrl(newBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Metode lain dalam service
}

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


interface CancelService {
    @POST("'/cancel_order/<id>'")
    suspend fun createOrder(@Body request: CheckoutRequest): Response<OrderResponse>
}
interface TransactionService {
    @GET("/transaction")
    suspend fun getAllTransactions(@Header("token") token: String): List<Transaction>

    @GET("cancel_order/{id}")
    suspend fun cancelOrder(@Path("id") transactionId: String) : Status
}