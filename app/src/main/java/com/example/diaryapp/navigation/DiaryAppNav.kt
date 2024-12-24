package com.example.diaryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.data.model.DiaryEntry
import com.example.diaryapp.ui.screens.DetailsScreen
import com.example.diaryapp.ui.screens.EntryScreen
import com.example.diaryapp.ui.screens.HomeScreen
import com.example.diaryapp.ui.screens.SearchScreen
import com.example.diaryapp.viewmodel.DiaryViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DiaryAppNav() {
    val navController = rememberNavController()
    val viewModel: DiaryViewModel = hiltViewModel()

    val entries by viewModel.entries.collectAsState() // Observa as entradas

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                entries = entries,
                onAddEntry = { navController.navigate("entry") },
                onSearch = { navController.navigate("search") },
                onEntryClick = { entry ->
                    navController.navigate("details/${entry.id}")
                }
            )
        }
        composable("entry") {
            EntryScreen(
                onSave = { mood, content ->
                    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

                    // Salva a entrada no banco via ViewModel
                    viewModel.addEntry(
                        DiaryEntry(
                            date = currentDate,
                            content = content,
                            mood = mood
                        )
                    )
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("search") {
            SearchScreen(
                onSearch = { mood, keyword ->
                    // Buscar entradas no ViewModel
                    viewModel.searchEntries(mood, keyword)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("details/{entryId}") { backStackEntry ->
            val entryId = backStackEntry.arguments?.getString("entryId")?.toIntOrNull()
            val entry = viewModel.getEntryById(entryId)
            DetailsScreen(entry = entry)
        }
    }
}