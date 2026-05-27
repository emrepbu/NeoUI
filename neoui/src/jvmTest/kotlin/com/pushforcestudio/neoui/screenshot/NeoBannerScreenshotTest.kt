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
import com.pushforcestudio.neoui.components.BannerType
import com.pushforcestudio.neoui.components.NeoBanner
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test

class NeoBannerScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoBanner_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text("INFO", modifier = Modifier.padding(bottom = 4.dp))
                    NeoBanner(message = "Information message", type = BannerType.INFO)
                    Spacer(Modifier.height(8.dp))
                    NeoBanner(
                        message = "Dismissible info",
                        type = BannerType.INFO,
                        onDismiss = {},
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("SUCCESS", modifier = Modifier.padding(bottom = 4.dp))
                    NeoBanner(message = "Operation completed", type = BannerType.SUCCESS)
                    Spacer(Modifier.height(8.dp))
                    NeoBanner(
                        message = "Dismissible success",
                        type = BannerType.SUCCESS,
                        onDismiss = {},
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("ERROR", modifier = Modifier.padding(bottom = 4.dp))
                    NeoBanner(message = "Something went wrong", type = BannerType.ERROR)
                    Spacer(Modifier.height(8.dp))
                    NeoBanner(
                        message = "Dismissible error",
                        type = BannerType.ERROR,
                        onDismiss = {},
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }
}
