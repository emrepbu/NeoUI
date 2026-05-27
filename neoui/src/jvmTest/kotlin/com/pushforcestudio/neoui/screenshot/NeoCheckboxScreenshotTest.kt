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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoCheckbox
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoCheckboxScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoCheckbox_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Enabled", modifier = Modifier.padding(bottom = 4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoCheckbox(
                            checked = false,
                            onCheckedChange = {},
                            modifier = Modifier.padding(end = 4.dp),
                        )
                        Text("Unchecked")
                        Spacer(Modifier.width(24.dp))
                        NeoCheckbox(
                            checked = true,
                            onCheckedChange = {},
                            modifier = Modifier.padding(end = 4.dp),
                        )
                        Text("Checked")
                        Spacer(Modifier.width(24.dp))
                        CheckboxPressed()
                        Text("Pressed")
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Disabled", modifier = Modifier.padding(bottom = 4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoCheckbox(
                            checked = false,
                            onCheckedChange = {},
                            enabled = false,
                            modifier = Modifier.padding(end = 4.dp),
                        )
                        Text("Unchecked")
                        Spacer(Modifier.width(24.dp))
                        NeoCheckbox(
                            checked = true,
                            onCheckedChange = {},
                            enabled = false,
                            modifier = Modifier.padding(end = 4.dp),
                        )
                        Text("Checked")
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }

    companion object {
        @Composable
        fun CheckboxPressed() {
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.emit(
                    PressInteraction.Press(androidx.compose.ui.geometry.Offset.Zero)
                )
            }
            NeoCheckbox(
                checked = true,
                onCheckedChange = {},
                interactionSource = interactionSource,
            )
        }
    }
}
