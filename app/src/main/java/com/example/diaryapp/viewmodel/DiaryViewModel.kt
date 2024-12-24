package com.example.diaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.model.DiaryEntry
import com.example.diaryapp.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {

    private val _entries = MutableStateFlow<List<DiaryEntry>>(emptyList())
    val entries: StateFlow<List<DiaryEntry>> get() = _entries

    init {
        loadEntries()
    }

    private fun loadEntries() {
        viewModelScope.launch {
            Log.d("DiaryViewModel", "Loading entries...")
            _entries.value = repository.getAllEntries()
            Log.d("DiaryViewModel", "Loaded entries: ${_entries.value}")

        }
    }

    fun addEntry(entry: DiaryEntry) {
        viewModelScope.launch {
            Log.d("DiaryViewModel", "Adding entry: $entry")
            repository.insertEntry(entry)

            // Atualiza o estado com os dados mais recentes
            _entries.value = repository.getAllEntries()
            Log.d("DiaryViewModel", "Updated entries: ${_entries.value}")
        }
    }

    fun getEntryById(id: Int?): DiaryEntry? {
        return _entries.value.find { it.id == id }
    }

    fun searchEntries(mood: String, keyword: String) {
        viewModelScope.launch {
            val moodQuery = if (mood.isBlank()) null else "%$mood%"
            val keywordQuery = if (keyword.isBlank()) null else "%$keyword%"

            Log.d("DiaryViewModel", "Searching entries with mood: $moodQuery and keyword: $keywordQuery")
            val searchResults = repository.searchEntries(moodQuery, keywordQuery)
            Log.d("DiaryViewModel", "Search results: $searchResults")
            _entries.value = searchResults // Atualiza o StateFlow com os resultados
        }
    }
}