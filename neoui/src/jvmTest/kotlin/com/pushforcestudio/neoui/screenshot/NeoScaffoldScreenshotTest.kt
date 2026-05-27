package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.pushforcestudio.neoui.components.NeoScaffold
import com.pushforcestudio.neoui.components.NeoTopAppBar
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoScaffoldScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoScaffold_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("With TopBar + Content", modifier = Modifier.padding(bottom = 4.dp))
                    NeoScaffold(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        topBar = {
                            NeoTopAppBar(title = { Text("Scaffold") })
                        },
                    ) {
                        Column(Modifier.padding(it).padding(16.dp)) {
                            Text("Content Area")
                            Text("Second line of content")
                        }
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoScaffold_bottomBar() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("With BottomBar", modifier = Modifier.padding(bottom = 4.dp))
                    NeoScaffold(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        bottomBar = {
                            NeoTopAppBar(title = { Text("Bottom") })
                        },
                    ) {
                        Column(Modifier.padding(it).padding(16.dp)) {
                            Text("Content above bottom bar")
                        }
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoScaffold_full() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("Full Scaffold", modifier = Modifier.padding(bottom = 4.dp))
                    NeoScaffold(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        topBar = { NeoTopAppBar(title = { Text("Top") }) },
                        bottomBar = { NeoTopAppBar(title = { Text("Bottom") }) },
                    ) {
                        Column(Modifier.padding(it).padding(16.dp)) {
                            Text("Middle Content")
                            Text("Scrollable area")
                        }
                    }
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
