package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoButton
import com.pushforcestudio.neoui.components.NeoOutlinedButton
import com.pushforcestudio.neoui.components.NeoSecondaryButton
import com.pushforcestudio.neoui.components.NeoTextButton
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoButtonScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoButton_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                ButtonStatesGrid()
            }
        }
        onRoot().captureRoboImage()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoButton_variants() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Outlined", modifier = Modifier.padding(bottom = 8.dp))
                    Row(Modifier.fillMaxWidth()) {
                        NeoOutlinedButton(
                            onClick = {},
                            modifier = Modifier.testTag("outlined_default").padding(end = 8.dp),
                        ) { Text("Default") }
                        NeoOutlinedButton(
                            onClick = {},
                            modifier = Modifier.testTag("outlined_disabled").padding(end = 8.dp),
                            enabled = false,
                        ) { Text("Disabled") }
                        OutlinedButtonPressed()
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Secondary", modifier = Modifier.padding(bottom = 8.dp))
                    Row(Modifier.fillMaxWidth()) {
                        NeoSecondaryButton(
                            onClick = {},
                            modifier = Modifier.testTag("secondary_default").padding(end = 8.dp),
                        ) { Text("Default") }
                        NeoSecondaryButton(
                            onClick = {},
                            modifier = Modifier.testTag("secondary_disabled").padding(end = 8.dp),
                            enabled = false,
                        ) { Text("Disabled") }
                        SecondaryButtonPressed()
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Text", modifier = Modifier.padding(bottom = 8.dp))
                    Row(Modifier.fillMaxWidth()) {
                        NeoTextButton(
                            onClick = {},
                            modifier = Modifier.testTag("text_default").padding(end = 8.dp),
                        ) { Text("Default") }
                        NeoTextButton(
                            onClick = {},
                            modifier = Modifier.testTag("text_disabled").padding(end = 8.dp),
                            enabled = false,
                        ) { Text("Disabled") }
                        TextButtonPressed()
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }

    companion object {
        @Composable
        fun ButtonStatesGrid() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                StatefulButton(
                    label = "Default",
                    tag = "btn_default",
                    onClick = {},
                )
                Spacer(Modifier.height(16.dp))
                StatefulButton(
                    label = "Pressed",
                    tag = "btn_pressed",
                    onClick = {},
                    pressed = true,
                )
                Spacer(Modifier.height(16.dp))
                StatefulButton(
                    label = "Disabled",
                    tag = "btn_disabled",
                    onClick = {},
                    enabled = false,
                )
            }
        }

        @Composable
        fun StatefulButton(
            label: String,
            tag: String,
            onClick: () -> Unit,
            enabled: Boolean = true,
            pressed: Boolean = false,
        ) {
            val interactionSource = remember { MutableInteractionSource() }

            if (pressed) {
                LaunchedEffect(interactionSource) {
                    interactionSource.emit(
                        PressInteraction.Press(
                            androidx.compose.ui.geometry.Offset.Zero,
                        )
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = label, modifier = Modifier.padding(bottom = 4.dp))
                NeoButton(
                    onClick = onClick,
                    modifier = Modifier.testTag(tag),
                    enabled = enabled,
                    interactionSource = interactionSource,
                ) {
                    Text("Button")
                }
            }
        }

        @Composable
        fun OutlinedButtonPressed() {
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.emit(PressInteraction.Press(androidx.compose.ui.geometry.Offset.Zero))
            }
            NeoOutlinedButton(
                onClick = {},
                interactionSource = interactionSource,
            ) { Text("Pressed") }
        }

        @Composable
        fun SecondaryButtonPressed() {
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.emit(PressInteraction.Press(androidx.compose.ui.geometry.Offset.Zero))
            }
            NeoSecondaryButton(
                onClick = {},
                interactionSource = interactionSource,
            ) { Text("Pressed") }
        }

        @Composable
        fun TextButtonPressed() {
            val interactionSource = remember { MutableInteractionSource() }
            LaunchedEffect(interactionSource) {
                interactionSource.emit(PressInteraction.Press(androidx.compose.ui.geometry.Offset.Zero))
            }
            NeoTextButton(
                onClick = {},
                interactionSource = interactionSource,
            ) { Text("Pressed") }
        }
    }
}
