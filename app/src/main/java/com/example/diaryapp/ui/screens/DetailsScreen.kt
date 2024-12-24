package com.example.diaryapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.diaryapp.data.model.DiaryEntry

@Composable
fun DetailsScreen(entry: DiaryEntry?) {
    if (entry != null) {
        Column {
            Text(text = "Data: ${entry.date}")
            Text(text = "Humor: ${entry.mood}")
            Text(text = "Conteúdo: ${entry.content}")
        }
    } else {
        Text("Entrada não encontrada")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val mockEntry = DiaryEntry(
        id = 1,
        date = "18/12/2024",
        content = "Hoje foi um dia incrível!",
        mood = "😀"
    )
    DetailsScreen(entry = mockEntry)
}
