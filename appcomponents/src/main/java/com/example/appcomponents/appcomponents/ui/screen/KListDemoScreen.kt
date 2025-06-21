package com.example.appcomponents.appcomponents.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.appcomponents.appcomponents.core.KList
import com.example.appcomponents.appcomponents.core.KList.items
import com.example.appcomponents.appcomponents.models.Coin
import com.example.appcomponents.ui.component.KListItem

@Composable
fun KListDemoScreen() {
    val topGainers = listOf(
        Coin("btc", "Bitcoin", "BTC", 68000.0, 5.6),
        Coin("eth", "Ethereum", "ETH", 3400.0, 4.2),
        Coin("sol", "Solana", "SOL", 130.5, 7.9)
    )
    Column {
        KList
            .padding<Coin>(10.dp)
            .header("Top Gainers")
            .dividers()
            .clickable { println("Clicked ${it.name}") }
            .animate()
            .items(topGainers) { coin ->
                KListItem(coin)
            }

    }
}