package com.example.diaryapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diaryapp.data.model.DiaryEntry

@Dao
interface DiaryEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: DiaryEntry)

    @Query("SELECT * FROM diary_entries ORDER BY date DESC")
    suspend fun getAllEntries(): List<DiaryEntry>

//    @Query("SELECT * FROM diary_entries WHERE LOWER(mood) LIKE LOWER('%' || :mood || '%') OR content LIKE '%' || :keyword || '%'")
    @Query("""
        SELECT * FROM diary_entries 
        WHERE (:mood IS NULL OR LOWER(mood) LIKE LOWER(:mood)) 
          AND (:keyword IS NULL OR content LIKE '%' || :keyword || '%')
    """)
    suspend fun searchEntries(mood: String?, keyword: String?): List<DiaryEntry>

    @Delete
    suspend fun deleteEntry(entry: DiaryEntry)
}