package com.example.diaryapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.data.model.DiaryEntry

@Composable
fun EntryCard(entry: DiaryEntry, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation =  4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.mood, fontSize = 24.sp) // Emoji do humor
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = entry.content, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = entry.date,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryCardPreview() {
    val mockEntry = DiaryEntry(
        id = 1,
        date = "18/12/2024",
        content = "Hoje foi um dia incrível!",
        mood = "😀"
    )

    EntryCard(entry = mockEntry, onClick = {})
}