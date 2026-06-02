package com.tc.citizenskmp.di

import com.tc.citizenskmp.db.createDatabase
import org.koin.dsl.module

val databaseModule = module {


    single {
        createDatabase(
            driverFactory = get(),
            passphrase = getSecurePassphrase()
        )
    }

}

expect val platformModule: org.koin.core.module.Module
expect fun getSecurePassphrase(): String
