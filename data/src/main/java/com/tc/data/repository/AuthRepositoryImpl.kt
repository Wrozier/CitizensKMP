package com.tc.data.repository

import com.tc.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private var loggedIn = false

    override suspend fun login(pin: String): Result<Unit> {
        return if (pin == "0000") {
            loggedIn = true
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid PIN"))
        }
    }

    override suspend fun logout() {
        loggedIn = false
    }

    override fun isLoggedIn(): Boolean {
        return loggedIn
    }
}
