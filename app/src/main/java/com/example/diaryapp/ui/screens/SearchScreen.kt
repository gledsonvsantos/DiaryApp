package com.example.diaryapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.ui.components.EmojiPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onSearch: (String, String) -> Unit, onBack: () -> Unit) {
    var keyword by remember { mutableStateOf("") }
    var selectedEmoji by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Entradas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Filtrar por Humor:")
            EmojiPicker(
                selectedEmoji = selectedEmoji,
                onEmojiSelected = { emoji -> selectedEmoji = emoji }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                label = { Text("Palavra-chave") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val moodQuery = if (selectedEmoji.isBlank()) "" else selectedEmoji
                    val keywordQuery = if (keyword.isBlank()) "" else keyword
                    onSearch(moodQuery, keywordQuery)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Buscar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(onSearch = { _, _ -> }, onBack = {})
}