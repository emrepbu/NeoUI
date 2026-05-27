# NeoUI

A Neo-Brutalist UI component library for **Compose Multiplatform** — bold borders, hard shadows, vibrant colors. Targets **Android**, **iOS**, and **Desktop (JVM)**.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## Installation

```kotlin
// gradle/libs.versions.toml
[versions]
neoui = "0.4.0"

[libraries]
neoui-core = { module = "com.pushforcestudio.neoui:core", version.ref = "neoui" }
```

```kotlin
// build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.neoui.core)
        }
    }
}
```

---

## Quick Start

```kotlin
import com.pushforcestudio.neoui.theme.NeoTheme
import com.pushforcestudio.neoui.theme.NeoThemeMode
import com.pushforcestudio.neoui.theme.NeoColorScheme
import com.pushforcestudio.neoui.components.NeoButton
import com.pushforcestudio.neoui.components.NeoScaffold
import com.pushforcestudio.neoui.components.NeoTopAppBar

@Composable
fun App() {
    NeoTheme(
        colorScheme = NeoColorScheme.Blue,
        themeMode = NeoThemeMode.SYSTEM,
    ) {
        NeoScaffold(
            topBar = { NeoTopAppBar(title = { Text("NeoUI") }) },
        ) { padding ->
            NeoButton(onClick = { /* handle click */ }) {
                Text("Click Me")
            }
        }
    }
}
```

---

## Components

| Component | Type | Description |
|---|---|---|
| **NeoAccordion** | Container | Expandable/collapsible section with animated content |
| **NeoBadge** | Display | Small label with customizable background and text color |
| **NeoBanner** | Display | INFO / SUCCESS / ERROR notification bar, optionally dismissible |
| **NeoButton** | Action | Filled button with shadow press animation |
| **NeoOutlinedButton** | Action | Transparent button with border and shadow |
| **NeoSecondaryButton** | Action | Surface-colored button with shadow |
| **NeoTextButton** | Action | Text-only button without container |
| **NeoCard** | Container | Filled or outlined card with neo-brutalist border and shadow |
| **NeoCheckbox** | Input | Checkbox with X-mark indicator and shadow press effect |
| **NeoDatePicker** | Input | Calendar grid with month navigation and selection |
| **NeoDialog** | Overlay | Modal dialog with title, body, and action buttons |
| **NeoDropdown** | Input | Dropdown select with popup list and scroll |
| **NeoIcon** | Display | Icon rendering with configurable tint and size |
| **NeoIconButton** | Action | Square icon button with shadow press animation |
| **NeoProgressBar** | Display | Horizontal progress bar with track and fill |
| **NeoScaffold** | Layout | Full-screen layout with top bar, content, and bottom bar |
| **NeoSnackbarHost** | Overlay | Animated snackbar with INFO / SUCCESS / ERROR types |
| **NeoSwitch** | Input | On/off toggle with animated thumb and track |
| **NeoTextField** | Input | Text field with focus / error / disabled states |
| **NeoTopAppBar** | Layout | Navigation bar with optional back icon and action buttons |

---

## Theme System

### Colors

```kotlin
NeoTheme(
    colors = NeoColors(
        background = Color.White,
        surface = Color.White,
        text = Color.Black,
        primary = Color(0xFFFF1493),
        secondary = Color(0xFF00E5FF),
        border = Color.Black,
        shadow = Color.Black,
        error = Color(0xFFFF0000),
    ),
) { content() }
```

### Color Schemes (OKLCH)

17 predefined `NeoColorScheme` entries with light and dark variants:

Red · Orange · Amber · Yellow · Lime · Green · Emerald · Teal · Cyan · Sky · Blue · Indigo · Violet · Purple · Fuchsia · Pink · Rose

```kotlin
NeoTheme(colorScheme = NeoColorScheme.Emerald) { content() }
```

### Theme Mode

```kotlin
NeoThemeMode.LIGHT   — force light colors
NeoThemeMode.DARK    — force dark colors
NeoThemeMode.SYSTEM  — follow device setting (default)
```

### Typography

```kotlin
NeoTheme(
    typography = NeoTypography(
        heading = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
        body = TextStyle(fontSize = 14.sp),
        caption = TextStyle(fontSize = 12.sp),
    ),
) { content() }
```

### Dimensions

```kotlin
NeoTheme(
    dimens = NeoDimens(
        borderWidth = 4.dp,
        cornerRadius = 0.dp,
        horizontalShadowOffset = 4.dp,
        verticalShadowOffset = 4.dp,
    ),
) { content() }
```

---

## Screenshot Testing

Visual regression tests use [Roborazzi](https://github.com/takahirom/roborazzi) on the JVM — no emulator required.

### Commands

```bash
# Record baseline screenshots
./gradlew :core:recordRoborazziJvm

# Verify against baselines (CI)
./gradlew :core:verifyRoborazziJvm
```

### Test Results

**20 tests across 16 components — all passing**

| Test Class | Tests | Status |
|---|---|---|
| NeoAccordionScreenshotTest | 1 | Passed |
| NeoBadgeScreenshotTest | 1 | Passed |
| NeoBannerScreenshotTest | 1 | Passed |
| NeoButtonScreenshotTest | 2 | Passed |
| NeoCardScreenshotTest | 1 | Passed |
| NeoCheckboxScreenshotTest | 1 | Passed |
| NeoDatePickerScreenshotTest | 1 | Passed |
| NeoDropdownScreenshotTest | 2 | Passed |
| NeoIconScreenshotTest | 1 | Passed |
| NeoIconButtonScreenshotTest | 1 | Passed |
| NeoProgressBarScreenshotTest | 1 | Passed |
| NeoScaffoldScreenshotTest | 3 | Passed |
| NeoSnackbarHostScreenshotTest | 1 | Passed |
| NeoSwitchScreenshotTest | 1 | Passed |
| NeoTextFieldScreenshotTest | 1 | Passed |
| NeoTopAppBarScreenshotTest | 1 | Passed |

> **Note:** `NeoDialog` is excluded from screenshot tests. The `Dialog` composable renders in a separate popup that is not captured by Roborazzi's `onRoot().captureRoboImage()`.

### Test Source

```
neoui/src/jvmTest/kotlin/com/pushforcestudio/neoui/screenshot/
├── NeoAccordionScreenshotTest.kt
├── NeoBadgeScreenshotTest.kt
├── NeoBannerScreenshotTest.kt
├── NeoButtonScreenshotTest.kt
├── NeoCardScreenshotTest.kt
├── NeoCheckboxScreenshotTest.kt
├── NeoDatePickerScreenshotTest.kt
├── NeoDropdownScreenshotTest.kt
├── NeoIconButtonScreenshotTest.kt
├── NeoIconScreenshotTest.kt
├── NeoProgressBarScreenshotTest.kt
├── NeoScaffoldScreenshotTest.kt
├── NeoSnackbarHostScreenshotTest.kt
├── NeoSwitchScreenshotTest.kt
├── NeoTextFieldScreenshotTest.kt
└── NeoTopAppBarScreenshotTest.kt
```

---

## Building & Publishing

```bash
# Build all targets
./gradlew :core:assemble

# Build Android AAR
./gradlew :core:bundleAndroidMainAar

# Publish to local Maven
./gradlew :core:publishToMavenLocal

# Run JVM desktop tests
./gradlew :core:jvmTest

# Run iOS simulator tests
./gradlew :core:iosSimulatorArm64Test
```

**Targets:** Android (minSdk 24, compileSdk 36), iOS (arm64, simulator arm64), JVM (desktop)

**Tech Stack:** Kotlin 2.3.21 · Compose Multiplatform 1.11.0 · AGP 9.0.1 · Gradle 9.1 · Roborazzi 1.63

---

## License

MIT © [PushForce Studio](https://github.com/emrepbu)
