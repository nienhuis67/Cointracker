
package com.example.coin500

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coin500.network.ApiService
import com.example.coin500.model.Coin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoScreen()
        }
    }
}

@Composable
fun CryptoScreen() {
    var coins by remember { mutableStateOf(listOf<Coin>()) }

    LaunchedEffect(Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        coins = api.getTopCoins().take(500)
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(coins) { coin ->
            Text("${coin.name}: $${coin.current_price}", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
