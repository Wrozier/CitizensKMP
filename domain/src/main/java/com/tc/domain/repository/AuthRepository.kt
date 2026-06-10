package com.tc.domain.repository

interface AuthRepository {
    suspend fun login(pin: String): Result<Unit>
    suspend fun logout()
    fun isLoggedIn(): Boolean
}
