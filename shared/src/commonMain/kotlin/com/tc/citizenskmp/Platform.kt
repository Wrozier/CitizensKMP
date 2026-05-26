package com.tc.citizenskmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform