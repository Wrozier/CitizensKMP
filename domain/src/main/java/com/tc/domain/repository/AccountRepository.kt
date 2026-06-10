package com.tc.domain.repository

import com.tc.domain.model.Account
import com.tc.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAccounts(): Flow<List<Account>>
    fun getTransactions(accountId: String): Flow<List<Transaction>>
    suspend fun refreshAccounts()
}
