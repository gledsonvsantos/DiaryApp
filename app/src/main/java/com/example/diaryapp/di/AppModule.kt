package com.example.diaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.diaryapp.data.dao.DiaryEntryDao
import com.example.diaryapp.data.database.AppDatabase
import com.example.diaryapp.data.repository.DiaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        passphrase: ByteArray
    ): AppDatabase {
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "diary_database"
        )
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.diaryEntryDao()

    @Singleton
    @Provides
    fun provideRepository(dao: DiaryEntryDao) = DiaryRepository(dao)
}