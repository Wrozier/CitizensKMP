package com.tc.citizenskmp.di

import org.koin.core.context.startKoin

class KoinHelper {
    fun start() {
        startKoin {
            modules(databaseModule, platformModule)
        }
    }
}
