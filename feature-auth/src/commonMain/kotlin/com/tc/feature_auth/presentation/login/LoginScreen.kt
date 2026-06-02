package com.tc.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onBiometricLogin: () -> Unit = {},
    onCreateAccount: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showAuthOptions by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Citizens Bank",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (!showAuthOptions) {
            // Landing UI: Sign In and Create Account
            Button(
                onClick = { showAuthOptions = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                shape = CircleShape
            ) {
                Text("Sign In", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onCreateAccount,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                shape = CircleShape
            ) {
                Text("Create Account", style = MaterialTheme.typography.titleMedium)
            }
        } else {
            // Authentication UI: Show PIN Pad and Biometrics
            Text(
                text = "Enter your PIN",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            // PIN Indicator (Dots)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(4) { index ->
                    val isFilled = index < pin.length
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(
                                if (isFilled) MaterialTheme.colorScheme.primary 
                                else MaterialTheme.colorScheme.outlineVariant
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Numeric PIN Pad
            PinPad(
                onNumberClick = { num ->
                    if (pin.length < 4) {
                        pin += num
                        errorMessage = null
                        if (pin.length == 4) {
                            if (pin == "1234") { // Mock PIN validation
                                onLoginSuccess()
                            } else {
                                errorMessage = "Invalid PIN"
                                pin = ""
                            }
                        }
                    }
                },
                onDeleteClick = {
                    if (pin.isNotEmpty()) {
                        pin = pin.dropLast(1)
                        errorMessage = null
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = onBiometricLogin) {
                Text("Use Face ID / Fingerprint", style = MaterialTheme.typography.bodyLarge)
            }

            TextButton(onClick = { 
                showAuthOptions = false
                pin = ""
                errorMessage = null
            }) {
                Text("Back")
            }
        }
    }
}

@Composable
fun PinPad(
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit
) {
    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "DEL")
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        numbers.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { item ->
                    when (item) {
                        "" -> Spacer(modifier = Modifier.size(72.dp))
                        "DEL" -> {
                            TextButton(
                                onClick = onDeleteClick,
                                modifier = Modifier.size(72.dp)
                            ) {
                                Text("DEL", style = MaterialTheme.typography.titleMedium)
                            }
                        }
                        else -> {
                            PinButton(
                                number = item,
                                onClick = { onNumberClick(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PinButton(
    number: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = CircleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = number,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}
