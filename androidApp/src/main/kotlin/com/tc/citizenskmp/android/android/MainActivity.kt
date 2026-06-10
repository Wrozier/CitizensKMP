package com.tc.citizenskmp.android.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.tc.citizenskmp.android.DashboardScreen
import com.tc.citizenskmp.android.ui.theme.CitizensKMPTheme
import com.tc.feature_accounts.presentation.AccountsScreen
import com.tc.feature_auth.presentation.create.CreateAccountScreen
import com.tc.feature_auth.presentation.login.LoginScreen
import com.tc.feature_check_deposit.presentation.CheckDepositScreen
import com.tc.feature_p2p_transfer.presentation.P2PTransferScreen
import com.tc.feature_transactions.presentation.TransactionsScreen

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CitizensKMPTheme {

                var currentScreen by remember { mutableStateOf("login") }

                when (currentScreen) {
                    "login" -> {
                        LoginScreen(
                            onLoginSuccess = {
                                currentScreen = "dashboard"
                            },
                            onBiometricLogin = {
                                checkAndShowBiometricPrompt {
                                    currentScreen = "dashboard"
                                }
                            },
                            onCreateAccount = {
                                currentScreen = "create_account"
                            }
                        )
                    }
                    "create_account" -> {
                        CreateAccountScreen(
                            onAccountCreated = {
                                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                                currentScreen = "login"
                            },
                            onBack = {
                                currentScreen = "login"
                            }
                        )
                    }
                    "dashboard" -> {
                        DashboardScreen(
                            onLogout = {
                                currentScreen = "login"
                            },
                            onAccountClick = {
                                currentScreen = "accounts"
                            },
                            onDepositClick = {
                                currentScreen = "check_deposit"
                            },
                            onP2PClick = {
                                currentScreen = "p2p_transfer"
                            },
                            onSeeAllTransactionsClick = {
                                currentScreen = "transactions"
                            }
                        )
                    }
                    "accounts" -> {
                        AccountsScreen(
                            onBack = {
                                currentScreen = "dashboard"
                            }
                        )
                    }
                    "check_deposit" -> {
                        CheckDepositScreen(
                            onBack = {
                                currentScreen = "dashboard"
                            },
                            onDepositSuccess = {
                                Toast.makeText(this, "Check Deposited Successfully!", Toast.LENGTH_SHORT).show()
                                currentScreen = "dashboard"
                            }
                        )
                    }
                    "p2p_transfer" -> {
                        P2PTransferScreen(
                            onBack = {
                                currentScreen = "dashboard"
                            }
                        )
                    }
                    "transactions" -> {
                        TransactionsScreen(
                            onBack = {
                                currentScreen = "dashboard"
                            }
                        )
                    }
                }
            }
        }
    }

    private fun checkAndShowBiometricPrompt(onSuccess: () -> Unit) {
        val biometricManager = BiometricManager.from(this)
        val authenticators = BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        
        when (biometricManager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                showBiometricPrompt(onSuccess)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                showToast("No biometric hardware detected on this device.")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                showToast("Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                showToast("No biometrics enrolled. Please set up Face ID or Fingerprint in device settings.")
            else ->
                showToast("Biometric authentication is not available.")
        }
    }

    private fun showBiometricPrompt(onSuccess: () -> Unit) {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON && errorCode != BiometricPrompt.ERROR_USER_CANCELED) {
                        showToast("Authentication error: $errString")
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast("Authentication failed. Please try again.")
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Citizens Bank Secure Login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use PIN")
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
