package com.tc.feature_accounts.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(onBack: () -> Unit) {
    BackHandler(onBack = onBack)
}
