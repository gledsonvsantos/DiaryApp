package com.example.diaryapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.data.model.DiaryEntry
import com.example.diaryapp.ui.components.EntryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    entries: List<DiaryEntry>,
    onAddEntry: () -> Unit,
    onSearch: () -> Unit,
    onEntryClick: (DiaryEntry) -> Unit // Passa o evento de clique no item
) {
    val forceRerender = remember { mutableStateOf(0) } // Estado auxiliar

    LaunchedEffect(entries) {
        Log.d("HomeScreen", "Entries updated: $entries")
        forceRerender.value++ // Incrementa para forçar recomposição
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddEntry) {
                Icon(Icons.Default.Add, contentDescription = "Add Entry")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Diário Pessoal") }, // Pode ser algo dinâmico como "Olá, Gledson!"
                actions = {
                    IconButton(onClick = onSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (entries.isEmpty()) {
            // Placeholder para lista vazia
            Text(
                text = "Nenhuma entrada disponível. Adicione seu primeiro registro!",
                modifier = Modifier.padding(paddingValues)
                    .padding(16.dp),
                color = Color.Gray
            )
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(
                    items = entries,
                    key = { it.id } // Chave única baseada no ID
                ) { entry ->
                    Log.d("LazyColumn", "Rendering entry: $entry")
                    EntryCard(
                        entry = entry,
                        onClick = { onEntryClick(entry) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewWithEntries() {
    val mockEntries = listOf(
        DiaryEntry(
            id = 1,
            date = "18/12/2024",
            content = "Hoje foi um dia incrível!",
            mood = "😀"
        ),
        DiaryEntry(
            id = 2,
            date = "17/12/2024",
            content = "Dia produtivo e tranquilo.",
            mood = "😎"
        ),
        DiaryEntry(
            id = 3,
            date = "16/12/2024",
            content = "Dia chuvoso e relaxante, assisti alguns filmes.",
            mood = "😀"
        )
    )

    HomeScreen(
        entries = mockEntries,
        onAddEntry = {},
        onSearch = {},
        onEntryClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewEmpty() {
    HomeScreen(
        entries = emptyList(),
        onAddEntry = {},
        onSearch = {},
        onEntryClick = {}
    )
}