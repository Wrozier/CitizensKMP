package com.tc.citizenskmp.db

import app.cash.sqldelight.db.SqlDriver
import com.citizens.db.CitizensDatabase
import com.tc.citizenskmp.db.DatabaseDriverFactory

fun createDatabase(
    driverFactory: DatabaseDriverFactory,
    passphrase: String
): CitizensDatabase {
    require(passphrase.isNotBlank() && passphrase.length >= 8) {
        "Passphrase must be at least 8 characters"
    }

    val driver = driverFactory.createDriver(passphrase)

    return CitizensDatabase(driver).also {
        it.transaction {

        }
    }
}