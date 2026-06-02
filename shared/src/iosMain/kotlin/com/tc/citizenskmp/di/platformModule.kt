package com.tc.citizenskmp.di

import com.tc.citizenskmp.db.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseDriverFactory() }
}

actual fun getSecurePassphrase(): String {
    // In a real app, this should be retrieved from a secure storage like Keychain
    return "secure_passphrase_ios"
}
