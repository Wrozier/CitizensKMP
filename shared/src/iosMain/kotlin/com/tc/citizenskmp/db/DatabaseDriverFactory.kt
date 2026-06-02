package com.tc.citizenskmp.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.citizens.db.CitizensDatabase

actual class DatabaseDriverFactory {

    actual fun createDriver(passphrase: String): SqlDriver {
        val dbName = "citizens.db"

        return NativeSqliteDriver(
            schema = CitizensDatabase.Schema,
            name = dbName,
            onConfiguration = { config ->
                config.copy(
                    encryptionConfig = DatabaseConfiguration.Encryption(
                        key = passphrase
                    )
                )
            }
        )
    }
}