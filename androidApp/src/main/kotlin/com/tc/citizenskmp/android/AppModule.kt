package com.tc.citizenskmp.android


import android.app.Application
import com.tc.citizenskmp.di.databaseModule
import com.tc.citizenskmp.di.platformModule // Import the platformModule from shared
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CitizensApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CitizensApplication)
            modules(
                databaseModule,
                platformModule,
                // networkModule,
                // viewModelModule
            )
        }
    }
}