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
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoBadge
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoBadgeScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoBadge_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Default", modifier = Modifier.padding(bottom = 4.dp))
                    NeoBadge(text = "NEW")

                    Spacer(Modifier.height(16.dp))
                    Text("Long Text", modifier = Modifier.padding(bottom = 4.dp))
                    NeoBadge(text = "99+")

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Colors", modifier = Modifier.padding(bottom = 4.dp))
                    Row {
                        NeoBadge(
                            text = "Custom",
                            backgroundColor = Color(0xFFFF1493),
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoBadge(
                            text = "Dark",
                            backgroundColor = Color.Black,
                            textColor = Color.White,
                        )
                        Spacer(Modifier.width(8.dp))
                        NeoBadge(
                            text = "Blue",
                            backgroundColor = Color(0xFF2196F3),
                            textColor = Color.White,
                        )
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
