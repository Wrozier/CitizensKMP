package com.tc.citizenskmp.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.citizens.db.CitizensDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(passphrase: String): SqlDriver {
        return AndroidSqliteDriver(
            schema = CitizensDatabase.Schema,
            context = context,
            name = "citizens.db"
        )
    }
}