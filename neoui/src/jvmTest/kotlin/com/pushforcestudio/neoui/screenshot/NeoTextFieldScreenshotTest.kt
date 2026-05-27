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
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoTextField
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoTextFieldScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoTextField_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Enabled — Idle", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTextField(
                        value = "",
                        onValueChange = {},
                        hint = "Enter text...",
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    NeoTextField(
                        value = "Hello NeoUI",
                        onValueChange = {},
                        hint = "Type something",
                        modifier = Modifier.padding(bottom = 8.dp),
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Enabled — Focused (simulated)", modifier = Modifier.padding(bottom = 4.dp))
                    TextFieldFocused()

                    Spacer(Modifier.height(16.dp))
                    Text("Enabled — Error", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTextField(
                        value = "bad value",
                        onValueChange = {},
                        isError = true,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    NeoTextField(
                        value = "",
                        onValueChange = {},
                        hint = "Required field",
                        isError = true,
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Disabled", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTextField(
                        value = "Disabled text",
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    NeoTextField(
                        value = "",
                        onValueChange = {},
                        hint = "Disabled hint",
                        enabled = false,
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Multiline", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTextField(
                        value = "Line one\nLine two\nLine three",
                        onValueChange = {},
                        singleLine = false,
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }

    companion object {
        @Composable
        fun TextFieldFocused() {
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.emit(
                    PressInteraction.Press(androidx.compose.ui.geometry.Offset.Zero)
                )
            }
            NeoTextField(
                value = "Focused state",
                onValueChange = {},
                hint = "Enter text...",
                interactionSource = interactionSource,
            )
        }
    }
}
