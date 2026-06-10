package com.tc.feature_p2p_transfer.presentation

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(onBack: () -> Unit) {
    // No-op for iOS as it typically uses gesture-based navigation or top bar buttons
}
