package com.tc.citizenskmp.android.android

data class Transaction(
    val id: String,
    val title: String,
    val amount: String,
    val date: String,
    val isNegative: Boolean
)
