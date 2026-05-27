package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoCard
import com.pushforcestudio.neoui.components.NeoOutlinedCard
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoCardScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoCard_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Filled Card", modifier = Modifier.padding(bottom = 4.dp))
                    NeoCard(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Card Title", modifier = Modifier.padding(8.dp))
                        Text("Card body content goes here", modifier = Modifier.padding(8.dp))
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Outlined Card", modifier = Modifier.padding(bottom = 4.dp))
                    NeoOutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Outline Card", modifier = Modifier.padding(8.dp))
                        Text("Transparent background", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
