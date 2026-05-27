package com.pushforcestudio.neoui.components

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NeoSnackbarState {
    data class SnackbarData(
        val message: String,
        val type: BannerType = BannerType.INFO,
    )

    private val channel = Channel<SnackbarData>(Channel.CONFLATED)

    val snackbarFlow = channel.receiveAsFlow()

    suspend fun showSnackbar(message: String, type: BannerType = BannerType.INFO) {
        channel.send(SnackbarData(message, type))
    }
}
