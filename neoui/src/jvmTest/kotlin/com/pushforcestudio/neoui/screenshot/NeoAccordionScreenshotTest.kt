package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoAccordion
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoAccordionScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoAccordion_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Collapsed", modifier = Modifier.padding(bottom = 4.dp))
                    NeoAccordion(title = "Collapsed Section") {
                        Text("Hidden content")
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Expanded", modifier = Modifier.padding(bottom = 4.dp))
                    NeoAccordion(
                        title = "Expanded Section",
                        initiallyExpanded = true,
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            Text("Visible content line 1")
                            Text("Visible content line 2")
                        }
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
