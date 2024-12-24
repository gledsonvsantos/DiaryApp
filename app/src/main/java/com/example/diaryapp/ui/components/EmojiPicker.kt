package com.example.diaryapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmojiPicker(
    selectedEmoji: String,
    onEmojiSelected: (String) -> Unit
) {
    val emojis = listOf("😀", "😢", "😡", "😍", "😎")
    LazyRow {
        items(emojis) { emoji ->
            Text(
                text = emoji,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onEmojiSelected(emoji) }
                    .background(
                        if (emoji == selectedEmoji) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent
                    )
                    .border(
                        width = if (emoji == selectedEmoji) 2.dp else 0.dp,
                        color = if (emoji == selectedEmoji) MaterialTheme.colorScheme.primary else Color.Transparent
                    )
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmojiPickerPreview() {
    EmojiPicker(selectedEmoji = "😀", onEmojiSelected = {})
}