package com.example.salesnotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var BASE_URL = "http://192.168.231.40:5000/"

    private var retrofit: Retrofit = createRetrofit(BASE_URL)

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun changeBaseUrl(newBaseUrl: String) {
        retrofit = createRetrofit(newBaseUrl)
    }


    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
    val customerService: CustomerService by lazy {
        retrofit.create(CustomerService::class.java)
    }

    val orderService: OrderService by lazy {
        retrofit.create(OrderService::class.java)
    }

    val itemService: ItemService = retrofit.create(ItemService::class.java)
    val stockService: StockService = retrofit.create(StockService::class.java)
    val transactionService: TransactionService = retrofit.create(TransactionService::class.java)
}