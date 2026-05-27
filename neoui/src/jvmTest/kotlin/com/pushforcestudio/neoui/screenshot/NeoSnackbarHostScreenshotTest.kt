package com.pushforcestudio.neoui.screenshot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.components.BannerType
import com.pushforcestudio.neoui.components.NeoSnackbarHost
import com.pushforcestudio.neoui.components.NeoSnackbarState
import com.pushforcestudio.neoui.components.SnackbarPosition
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import io.github.takahirom.roborazzi.captureRoboImage
import kotlinx.coroutines.flow.collectLatest
import org.junit.Test

class NeoSnackbarHostScreenshotTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun neoSnackbarHost_states() = runComposeUiTest {
        setContent {
            NeoTheme(themeMode = NeoThemeMode.LIGHT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    SnackbarDemo(
                        label = "INFO / BOTTOM",
                        message = "Information snackbar message",
                        type = BannerType.INFO,
                        position = SnackbarPosition.BOTTOM,
                    )

                    Spacer(Modifier.height(16.dp))
                    SnackbarDemo(
                        label = "SUCCESS / BOTTOM",
                        message = "Operation completed successfully!",
                        type = BannerType.SUCCESS,
                        position = SnackbarPosition.BOTTOM,
                    )

                    Spacer(Modifier.height(16.dp))
                    SnackbarDemo(
                        label = "ERROR / TOP",
                        message = "An error occurred. Please try again.",
                        type = BannerType.ERROR,
                        position = SnackbarPosition.TOP,
                    )

                    Spacer(Modifier.height(16.dp))
                    SnackbarDemo(
                        label = "INFO / TOP",
                        message = "This is a top-positioned snackbar",
                        type = BannerType.INFO,
                        position = SnackbarPosition.TOP,
                    )
                }
            }
        }
        onRoot().captureRoboImage()
    }

    companion object {
        @Composable
        fun SnackbarDemo(
            label: String,
            message: String,
            type: BannerType,
            position: SnackbarPosition,
        ) {
            val state = remember { NeoSnackbarState() }

            LaunchedEffect(Unit) {
                state.showSnackbar(message, type)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
            ) {
                NeoSnackbarHost(
                    state = state,
                    position = position,
                )
            }
        }
    }
}
