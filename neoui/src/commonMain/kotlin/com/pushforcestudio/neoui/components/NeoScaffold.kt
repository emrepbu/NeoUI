package com.pushforcestudio.neoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.pushforcestudio.neoui.theme.NeoTheme

@Composable
fun NeoScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    backgroundColor: Color = Color.Unspecified,
    content: @Composable (PaddingValues) -> Unit,
) {
    val bg = if (backgroundColor == Color.Unspecified) NeoTheme.colors.background else backgroundColor

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bg),
    ) {
        topBar()

        Box(modifier = Modifier.weight(1f)) {
            content(PaddingValues(0.dp))
        }

        bottomBar()
    }
}
