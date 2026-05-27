package com.pushforcestudio.neoui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NeoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NeoButtonColors = NeoButtonDefaults.filledColors(),
    shadows: NeoButtonShadows = NeoButtonDefaults.filledShadows(),
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = NeoButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    NeoButtonCore(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shadows = shadows,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun NeoOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NeoButtonColors = NeoButtonDefaults.outlinedColors(),
    shadows: NeoButtonShadows = NeoButtonDefaults.filledShadows(),
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = NeoButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    NeoButtonCore(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shadows = shadows,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun NeoSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NeoButtonColors = NeoButtonDefaults.secondaryColors(),
    shadows: NeoButtonShadows = NeoButtonDefaults.filledShadows(),
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = NeoButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    NeoButtonCore(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shadows = shadows,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun NeoTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NeoButtonColors = NeoButtonDefaults.textColors(),
    shadows: NeoButtonShadows = NeoButtonDefaults.textShadows(),
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    content: @Composable RowScope.() -> Unit,
) {
    NeoButtonCore(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shadows = shadows,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
        content = content,
    )
}
