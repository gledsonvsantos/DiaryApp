package com.example.diaryapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_entries")
data class DiaryEntry (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val content: String,
    val mood: String // Emoji como String
)