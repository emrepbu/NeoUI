package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.layout.Box
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
import com.pushforcestudio.neoui.components.NeoDropdown
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoDropdownScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoDropdown_collapsed() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Empty - shows placeholder", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDropdown(
                        items = emptyList(),
                        selectedIndex = 0,
                        onItemSelected = {},
                        placeholder = "Choose an option...",
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("With Selection", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDropdown(
                        items = listOf("Option A", "Option B", "Option C"),
                        selectedIndex = 1,
                        onItemSelected = {},
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoDropdown_expanded() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Dropdown States (expanded simulated)", modifier = Modifier.padding(bottom = 4.dp))

                    Text("Selected Index = 0", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDropdown(
                        items = listOf("First Item", "Second Item", "Third Item"),
                        selectedIndex = 0,
                        onItemSelected = {},
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Long Items", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDropdown(
                        items = listOf(
                            "A very long item name that might wrap",
                            "Short",
                            "Another long item name for testing",
                        ),
                        selectedIndex = 2,
                        onItemSelected = {},
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Many Items", modifier = Modifier.padding(bottom = 4.dp))
                    NeoDropdown(
                        items = (1..15).map { "Item $it" },
                        selectedIndex = 4,
                        onItemSelected = {},
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
