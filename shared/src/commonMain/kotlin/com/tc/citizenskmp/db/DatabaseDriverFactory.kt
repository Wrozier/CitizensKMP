package com.tc.citizenskmp.db

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(passphrase: String): SqlDriver
}