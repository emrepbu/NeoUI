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
import com.pushforcestudio.neoui.components.NeoProgressBar
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoProgressBarScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoProgressBar_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Progress Levels", modifier = Modifier.padding(bottom = 4.dp))
                    NeoProgressBar(progress = 0f, modifier = Modifier.padding(bottom = 8.dp))
                    Text("0%", modifier = Modifier.padding(bottom = 2.dp))

                    Spacer(Modifier.height(4.dp))
                    NeoProgressBar(progress = 0.25f, modifier = Modifier.padding(bottom = 8.dp))
                    Text("25%", modifier = Modifier.padding(bottom = 2.dp))

                    Spacer(Modifier.height(4.dp))
                    NeoProgressBar(progress = 0.5f, modifier = Modifier.padding(bottom = 8.dp))
                    Text("50%", modifier = Modifier.padding(bottom = 2.dp))

                    Spacer(Modifier.height(4.dp))
                    NeoProgressBar(progress = 0.75f, modifier = Modifier.padding(bottom = 8.dp))
                    Text("75%", modifier = Modifier.padding(bottom = 2.dp))

                    Spacer(Modifier.height(4.dp))
                    NeoProgressBar(progress = 1f, modifier = Modifier.padding(bottom = 8.dp))
                    Text("100%", modifier = Modifier.padding(bottom = 2.dp))

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Colors", modifier = Modifier.padding(bottom = 4.dp))
                    NeoProgressBar(
                        progress = 0.6f,
                        fillColor = Color(0xFFFF1493),
                        modifier = Modifier.padding(bottom = 8.dp),
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Custom Height (12dp)", modifier = Modifier.padding(bottom = 4.dp))
                    NeoProgressBar(progress = 0.5f, height = 12.dp)
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
