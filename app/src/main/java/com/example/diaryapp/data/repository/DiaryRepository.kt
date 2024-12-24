package com.example.diaryapp.data.repository

import android.util.Log
import com.example.diaryapp.data.dao.DiaryEntryDao
import com.example.diaryapp.data.model.DiaryEntry

class DiaryRepository(private val dao: DiaryEntryDao)  {
    suspend fun insertEntry(entry: DiaryEntry) {
        Log.d("DiaryRepository", "Inserting entry: $entry")
        dao.insertEntry(entry)
        Log.d("DiaryRepository", "Entry inserted successfully")
    }
    suspend fun getAllEntries(): List<DiaryEntry> {
        Log.d("DiaryRepository", "Fetching all entries")
        return dao.getAllEntries().also {
            Log.d("DiaryRepository", "Fetched entries: $it")
        }
    }
    suspend fun searchEntries(mood: String?, keyword: String?) = dao.searchEntries(mood, keyword)
    suspend fun deleteEntry(entry: DiaryEntry) = dao.deleteEntry(entry)
}