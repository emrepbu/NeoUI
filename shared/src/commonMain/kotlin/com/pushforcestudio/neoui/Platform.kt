package com.pushforcestudio.neoui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform