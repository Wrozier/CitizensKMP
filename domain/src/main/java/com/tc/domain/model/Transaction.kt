package com.tc.domain.model

data class Transaction(
    val id: String,
    val accountId: String,
    val amount: Double,
    val type: String,
    val description: String,
    val timestamp: String
)
