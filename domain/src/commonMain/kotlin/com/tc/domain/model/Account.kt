package com.tc.domain.model

data class Account(
    val id: String,
    val userID: String,
    val balance: Double,
    val accountType: String,
    val lastUpdated: String
)
