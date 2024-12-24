package com.example.diaryapp.di
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): EncryptedSharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    @Singleton
    @Provides
    fun provideDatabasePassphrase(prefs: EncryptedSharedPreferences): ByteArray {
        val passphraseKey = "db_passphrase"
        val storedPassphrase = prefs.getString(passphraseKey, null)

        return if (storedPassphrase == null) {
            val newPassphrase = "default_passphrase_${System.currentTimeMillis()}"
            prefs.edit().putString(passphraseKey, newPassphrase).apply()
//            Log.d("DatabasePassphrase", "Generated Passphrase: $newPassphrase")
            newPassphrase.toByteArray()
        } else {
//            Log.d("DatabasePassphrase", "Retrieved Passphrase: $storedPassphrase")
            storedPassphrase.toByteArray()
        }
    }
}