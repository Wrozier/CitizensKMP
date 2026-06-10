package com.tc.feature_transactions.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Transaction(
    val id: String,
    val title: String,
    val amount: String,
    val date: String,
    val isNegative: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    onBack: () -> Unit
) {
    val transactions = listOf(
        Transaction("1", "Starbucks", "-$5.50", "Today, 08:30 AM", true),
        Transaction("2", "Salary Deposit", "+$5,000.00", "Yesterday, 06:00 PM", false),
        Transaction("3", "Amazon.com", "-$120.40", "Oct 24, 2023", true),
        Transaction("4", "Uber Trip", "-$15.00", "Oct 23, 2023", true),
        Transaction("5", "Rent Payment", "-$2,200.00", "Oct 01, 2023", true),
        Transaction("6", "Grocery Store", "-$85.20", "Sep 30, 2023", true),
        Transaction("7", "Netflix Subscription", "-$15.99", "Sep 28, 2023", true),
        Transaction("8", "Dividend Payment", "+$12.50", "Sep 25, 2023", false),
        Transaction("9", "Gas Station", "-$45.00", "Sep 24, 2023", true),
        Transaction("10", "Utility Bill", "-$120.00", "Sep 20, 2023", true)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transactions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = transaction.amount,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isNegative) MaterialTheme.errorColor() else Color(0xFF4CAF50)
            )
        }
    }
}

@Composable
fun MaterialTheme.errorColor() = MaterialTheme.colorScheme.error
