package com.example.appcomponents.appcomponents.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class KListScope<T>(
    val padding: Dp = 0.dp,
    val header: String? = null,
    val sections: List<Pair<String?, List<T>>> = emptyList(),
    val onItemClick: ((T) -> Unit)? = null,
    val showDividers: Boolean = false,
    val enableAnimation: Boolean = false
) {

    fun padding(dp: Dp) = copy(padding = dp)

    fun header(title: String) = copy(header = title)

    fun section(title: String? = null, list: List<T>) =
        copy(sections = sections + (title to list))

    fun clickable(onClick: (T) -> Unit) = copy(onItemClick = onClick)

    fun dividers() = copy(showDividers = true)

    fun animate() = copy(enableAnimation = true)

    fun addItem(item: T): KListScope<T> {
        val updatedSections = if (sections.isEmpty()) {
            listOf(null to listOf(item))
        } else {
            val last = sections.last()
            val newLast = last.first to (last.second + item)
            sections.dropLast(1) + newLast
        }
        return copy(sections = updatedSections)
    }

    fun removeItem(predicate: (T) -> Boolean): KListScope<T> {
        val updatedSections = sections.map { (title, list) ->
            title to list.filterNot(predicate)
        }.filter { it.second.isNotEmpty() }
        return copy(sections = updatedSections)
    }

    @Composable
    fun render(itemContent: @Composable (T) -> Unit) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            header?.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )
                }
            }

            sections.forEach { (sectionTitle, list) ->
                if (!sectionTitle.isNullOrBlank()) {
                    item {
                        Text(
                            text = sectionTitle,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                itemsIndexed(list) { index, item ->
                    val content = @Composable {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(enabled = onItemClick != null) {
                                    onItemClick?.invoke(item)
                                }
                        ) {
                            itemContent(item)
                            if (showDividers && index < list.size - 1) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(Color.LightGray)
                                )
                            }
                        }
                    }

                    if (enableAnimation) {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + expandVertically(),
                            exit = shrinkVertically() + fadeOut()
                        ) {
                            content()
                        }
                    } else {
                        content()
                    }
                }
            }
        }
    }
}