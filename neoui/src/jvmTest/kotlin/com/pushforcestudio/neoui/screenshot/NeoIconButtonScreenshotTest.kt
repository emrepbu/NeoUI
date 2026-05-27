package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoIconButton
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoIconButtonScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoIconButton_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Default", modifier = Modifier.padding(bottom = 4.dp))
                    Row {
                        NeoIconButton(
                            onClick = {},
                            painter = ColorPainter(Color.Black),
                            contentDescription = null,
                        )
                        Spacer(Modifier.width(16.dp))
                        IconButtonPressed()
                        Spacer(Modifier.width(16.dp))
                        NeoIconButton(
                            onClick = {},
                            painter = ColorPainter(Color.Black),
                            contentDescription = null,
                            enabled = false,
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Tint", modifier = Modifier.padding(bottom = 4.dp))
                    Row {
                        NeoIconButton(
                            onClick = {},
                            painter = ColorPainter(Color.Red),
                            contentDescription = null,
                            tint = Color.Red,
                        )
                        Spacer(Modifier.width(16.dp))
                        NeoIconButton(
                            onClick = {},
                            painter = ColorPainter(Color.Blue),
                            contentDescription = null,
                            tint = Color.Blue,
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Size (64dp button, 32dp icon)", modifier = Modifier.padding(bottom = 4.dp))
                    NeoIconButton(
                        onClick = {},
                        painter = ColorPainter(Color.Black),
                        contentDescription = null,
                        size = 64.dp,
                        iconSize = 32.dp,
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }

    companion object {
        @Composable
        fun IconButtonPressed() {
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.emit(
                    PressInteraction.Press(androidx.compose.ui.geometry.Offset.Zero)
                )
            }
            NeoIconButton(
                onClick = {},
                painter = ColorPainter(Color.Black),
                contentDescription = null,
                interactionSource = interactionSource,
            )
        }
    }
}
