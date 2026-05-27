package com.pushforcestudio.neoui.components

import androidx.compose.runtime.Stable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Stable
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
