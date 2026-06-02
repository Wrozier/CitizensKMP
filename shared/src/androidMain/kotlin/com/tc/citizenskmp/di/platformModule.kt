package com.tc.citizenskmp.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.tc.citizenskmp.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.dsl.module
import java.util.UUID

actual val platformModule = module {
    single { DatabaseDriverFactory(androidContext()) }
}

/**
 * Android implementation for retrieving or generating a secure database passphrase.
 * Uses [EncryptedSharedPreferences] to store the passphrase securely in the Android Keystore.
 */
actual fun getSecurePassphrase(): String {
    // Access the Android Context from Koin's GlobalContext
    val context = GlobalContext.get().get<Context>()
    
    // Create or retrieve the Master Key for encryption using the Android Keystore
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    // Initialize EncryptedSharedPreferences for secure storage
    val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_database_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Retrieve the existing passphrase or generate a new unique one if it doesn't exist
    var passphrase = sharedPreferences.getString("db_passphrase", null)
    if (passphrase == null) {
        passphrase = UUID.randomUUID().toString()
        sharedPreferences.edit().putString("db_passphrase", passphrase).apply()
    }

    return passphrase!!
}
