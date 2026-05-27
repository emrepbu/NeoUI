package com.pushforcestudio.neoui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle

@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) {
    Column(
        modifier = modifier
            .neoBrutalistStyle()
            .padding(16.dp),
        content = content,
    )
}
