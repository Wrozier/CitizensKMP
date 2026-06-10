package com.tc.citizenskmp.android

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tc.feature_transactions.presentation.Transaction
import com.tc.feature_transactions.presentation.TransactionItem


@Composable
fun DashboardScreen(
    onLogout: () -> Unit,
    onAccountClick: () -> Unit,
    onDepositClick: () -> Unit,
    onP2PClick: () -> Unit,
    onSeeAllTransactionsClick: () -> Unit
) {

    BackHandler {
        onLogout()
    }

    val transactions = listOf(
        Transaction("1", "Starbucks", "-$5.50", "Today, 08:30 AM", true),
        Transaction("2", "Salary Deposit", "+$5,000.00", "Yesterday, 06:00 PM", false),
        Transaction("3", "Amazon.com", "-$120.40", "Oct 24, 2023", true),
        Transaction("4", "Uber Trip", "-$15.00", "Oct 23, 2023", true),
        Transaction("5", "Rent Payment", "-$2,200.00", "Oct 01, 2023", true)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Citizens Bank",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = onLogout) {
                            Text("Logout")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Good morning, Will 👋",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(32.dp))


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onAccountClick),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(
                                text = "Total Balance",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$12,000,000.00",
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "View All Accounts >",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = onDepositClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Deposit Check")
                        }
                        Button(
                            onClick = onP2PClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("P2P Transfer")
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Recent Transactions",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = onSeeAllTransactionsClick) {
                            Text("See All")
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}
