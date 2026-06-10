package com.tc.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.citizens.db.CitizensDatabase
import com.tc.domain.model.Account
import com.tc.domain.model.Transaction
import com.tc.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepositoryImpl(
    private val database: CitizensDatabase
) : AccountRepository {

    override fun getAccounts(): Flow<List<Account>> {
        return database.citizensDatabaseQueries
            .selectAllAccounts()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { accounts ->
                accounts.map { it.toDomain() }
            }
    }

    override fun getTransactions(accountId: String): Flow<List<Transaction>> {
        return database.citizensDatabaseQueries
            .selectAllTransactions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { transactions ->
                transactions
                    .filter { it.accountId == accountId }
                    .map { it.toDomain() }
            }
    }

    override suspend fun refreshAccounts() { }

    private fun com.citizens.db.Account.toDomain(): Account {
        return Account(
            id = id,
            userID = userID ?: "",
            balance = balance ?: 0.0,
            accountType = accountType ?: "",
            lastUpdated = lastUpdated ?: ""
        )
    }

    private fun com.citizens.db.AccountTransaction.toDomain(): Transaction {
        return Transaction(
            id = id,
            accountId = accountId ?: "",
            amount = amount ?: 0.0,
            type = type ?: "",
            description = description ?: "",
            timestamp = timestamp ?: ""
        )
    }
}
