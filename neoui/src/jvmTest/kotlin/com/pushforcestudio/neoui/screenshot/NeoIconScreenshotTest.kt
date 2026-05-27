package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoIcon
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoIconScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoIcon_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Default (24dp, theme text color)", modifier = Modifier.padding(bottom = 4.dp))
                    NeoIcon(
                        painter = ColorPainter(Color.Black),
                        contentDescription = null,
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Tint", modifier = Modifier.padding(bottom = 4.dp))
                    Row {
                        NeoIcon(
                            painter = ColorPainter(Color.Red),
                            contentDescription = null,
                            tint = Color.Red,
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoIcon(
                            painter = ColorPainter(Color.Blue),
                            contentDescription = null,
                            tint = Color.Blue,
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoIcon(
                            painter = ColorPainter(Color.Green),
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Sizes", modifier = Modifier.padding(bottom = 4.dp))
                    Row(verticalAlignment = androidx.compose.ui.Alignment.Bottom) {
                        NeoIcon(
                            painter = ColorPainter(Color.Black),
                            contentDescription = null,
                            size = 16.dp,
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoIcon(
                            painter = ColorPainter(Color.Black),
                            contentDescription = null,
                            size = 24.dp,
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoIcon(
                            painter = ColorPainter(Color.Black),
                            contentDescription = null,
                            size = 32.dp,
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoIcon(
                            painter = ColorPainter(Color.Black),
                            contentDescription = null,
                            size = 48.dp,
                        )
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
