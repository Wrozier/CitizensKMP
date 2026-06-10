package com.tc.feature_p2p_transfer.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(onBack: () -> Unit) {
    BackHandler(onBack = onBack)
}
