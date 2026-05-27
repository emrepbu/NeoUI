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
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.NeoTopAppBar
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoTopAppBarScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoTopAppBar_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("Title Only", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTopAppBar(title = { Text("Title Only") })
                    Spacer(Modifier.height(4.dp))

                    Text("With Back Icon", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTopAppBar(
                        title = { Text("With Back") },
                        navigationIcon = { Text("<-") },
                    )
                    Spacer(Modifier.height(4.dp))

                    Text("With Actions", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTopAppBar(
                        title = { Text("With Actions") },
                        actions = {
                            Text("A1")
                            Spacer(Modifier.width(8.dp))
                            Text("A2")
                        },
                    )
                    Spacer(Modifier.height(4.dp))

                    Text("Full Bar", modifier = Modifier.padding(bottom = 4.dp))
                    NeoTopAppBar(
                        title = { Text("Full Navigation") },
                        navigationIcon = { Text("<-") },
                        actions = {
                            Text("Search")
                            Spacer(Modifier.width(8.dp))
                            Text("More")
                        },
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
