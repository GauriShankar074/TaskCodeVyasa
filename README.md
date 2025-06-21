# KList DSL – Modifier-style Lazy List for Jetpack Compose

KList is a custom, composable, immutable **DSL for building sectioned lists** in Jetpack Compose, inspired by the `Modifier` chaining pattern.

Easily create complex, styled lists with support for:
- Section headers
- Dividers
- Animations
- Click handlers
- Dynamic add/remove
- Dialog with input to add items

---

##  Features

- ✅ Fluent DSL style (like `Modifier`)
- ✅ Section support with headers
- ✅ Clickable items
- ✅ Divider lines between items
- ✅ Entry/exit animation
- ✅ Dialog to add new items with `TextField`
- ✅ Type-safe and fully generic (`KList<T>`)

---


### Define your data class

```kotlin
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val price: Double,
    val changePercent24h: Double
)
