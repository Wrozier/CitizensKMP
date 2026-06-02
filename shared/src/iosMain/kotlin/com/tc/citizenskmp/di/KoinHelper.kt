package com.tc.citizenskmp.di

import org.koin.core.context.startKoin

class KoinHelper {
    fun initKoin() {
        startKoin {
            modules(databaseModule, platformModule)
        }
    }
}
