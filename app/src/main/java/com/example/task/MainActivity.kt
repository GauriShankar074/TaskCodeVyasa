package com.example.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appcomponents.appcomponents.core.KList
import com.example.appcomponents.appcomponents.core.KList.items
import com.example.appcomponents.appcomponents.models.Coin
import com.example.appcomponents.ui.component.KListItem
import com.example.task.ui.theme.TaskTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTheme {
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(title = {
                        Text(text = "Top Coins")
                    })
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(innerPadding)
                }
            }
        }
    }
}


@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    val topGainers = listOf(
        Coin("btc", "Bitcoin", "BTC", 68000.0, 5.6),
        Coin("eth", "Ethereum", "ETH", 3400.0, 4.2),
        Coin("sol", "Solana", "SOL", 130.5, 7.9)
    )
    Column(modifier = Modifier.padding(paddingValues)) {
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskTheme {
        HomeScreen(PaddingValues(0.dp))
    }
}