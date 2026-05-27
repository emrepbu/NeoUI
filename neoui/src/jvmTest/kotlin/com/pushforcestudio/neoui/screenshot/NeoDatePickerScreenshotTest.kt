package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoDate
import com.pushforcestudio.neoui.components.NeoDatePicker
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoDatePickerScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoDatePicker_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("No Selection", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDatePicker(
                        selectedDate = null,
                        onDateSelected = {},
                        today = NeoDate(2026, 5, 27),
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Date Selected", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDatePicker(
                        selectedDate = NeoDate(2026, 5, 15),
                        onDateSelected = {},
                        today = NeoDate(2026, 5, 27),
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Today is Selected", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDatePicker(
                        selectedDate = NeoDate(2026, 5, 27),
                        onDateSelected = {},
                        today = NeoDate(2026, 5, 27),
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
