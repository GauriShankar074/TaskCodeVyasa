package com.example.appcomponents.appcomponents.models

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val price: Double,
    val changePercent24h: Double
)