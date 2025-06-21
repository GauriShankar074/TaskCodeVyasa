package com.example.appcomponents.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appcomponents.appcomponents.models.Coin

@Composable
fun KListItem(coin: Coin) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(text = "${coin.name} (${coin.symbol})", style = MaterialTheme.typography.titleMedium)
        Text(text = "₹${coin.price}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "24h: ${coin.changePercent24h}%",
            color = if (coin.changePercent24h >= 0) Color.Green else Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}