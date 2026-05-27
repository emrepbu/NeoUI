package com.pushforcestudio.neoui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

enum class SnackbarPosition { TOP, BOTTOM }

@Composable
fun NeoSnackbarHost(
    state: NeoSnackbarState,
    modifier: Modifier = Modifier,
    position: SnackbarPosition = SnackbarPosition.BOTTOM,
) {
    var currentData by remember { mutableStateOf<NeoSnackbarState.SnackbarData?>(null) }
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        state.snackbarFlow.collect { data ->
            visible = false
            currentData = data
            visible = true
        }
    }

    LaunchedEffect(currentData, visible) {
        if (visible && currentData != null) {
            delay(3000)
            visible = false
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = when (position) {
            SnackbarPosition.TOP -> Alignment.TopCenter
            SnackbarPosition.BOTTOM -> Alignment.BottomCenter
        },
    ) {
        val slideIn = when (position) {
            SnackbarPosition.TOP -> slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
                initialOffsetY = { -it },
            )
            SnackbarPosition.BOTTOM -> slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
                initialOffsetY = { it },
            )
        }

        val slideOut = when (position) {
            SnackbarPosition.TOP -> slideOutVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh,
                ),
                targetOffsetY = { -it },
            )
            SnackbarPosition.BOTTOM -> slideOutVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh,
                ),
                targetOffsetY = { it },
            )
        }

        AnimatedVisibility(
            visible = visible && currentData != null,
            enter = slideIn,
            exit = slideOut,
        ) {
            currentData?.let { data ->
                NeoBanner(
                    message = data.message,
                    type = data.type,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onDismiss = { visible = false },
                )
            }
        }
    }
}
