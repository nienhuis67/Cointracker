
package com.example.coin500.network

import com.example.coin500.model.Coin
import retrofit2.http.GET

interface ApiService {
    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=500&page=1")
    suspend fun getTopCoins(): List<Coin>
}
