package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

fun oklchColor(l: Float, c: Float, h: Float): Color {
    val hRad = h * kotlin.math.PI.toFloat() / 180f
    val aOklab = c * cos(hRad)
    val bOklab = c * sin(hRad)

    val lLine = l + 0.3963377774f * aOklab + 0.2158037573f * bOklab
    val mLine = l - 0.1055613458f * aOklab - 0.0638541728f * bOklab
    val sLine = l - 0.0894841775f * aOklab - 1.2914855480f * bOklab

    val lmsL = lLine.pow(3)
    val lmsM = mLine.pow(3)
    val lmsS = sLine.pow(3)

    val linearR = +4.0767416621f * lmsL - 3.3077115913f * lmsM + 0.2309699292f * lmsS
    val linearG = -1.2684380046f * lmsL + 2.6097574011f * lmsM - 0.3413193965f * lmsS
    val linearB = -0.0041960863f * lmsL - 0.7034186147f * lmsM + 1.7076147010f * lmsS

    fun linearToSrgb(c: Float): Float {
        val clamped = c.coerceIn(0f, 1f)
        return if (clamped <= 0.0031308f) {
            12.92f * clamped
        } else {
            1.055f * clamped.pow(1f / 2.4f) - 0.055f
        }
    }

    return Color(
        red = linearToSrgb(linearR).coerceIn(0f, 1f),
        green = linearToSrgb(linearG).coerceIn(0f, 1f),
        blue = linearToSrgb(linearB).coerceIn(0f, 1f),
    )
}

@Immutable
data class NeoColorScheme(
    val name: String,
    val light: NeoColors,
    val dark: NeoColors,
) {
    companion object {
        val Red = NeoColorScheme(
            name = "red",
            light = NeoColors(
                primary = oklchColor(0.6728f, 0.2147f, 24.22f),
                background = oklchColor(0.9330f, 0.0339f, 17.77f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7049f, 0.1869f, 22.23f),
                background = oklchColor(0.2495f, 0.0491f, 20.19f),
            ),
        )

        val Orange = NeoColorScheme(
            name = "orange",
            light = NeoColors(
                primary = oklchColor(0.7227f, 0.1894f, 50.19f),
                background = oklchColor(0.9538f, 0.0357f, 72.89f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6756f, 0.1796f, 49.61f),
                background = oklchColor(0.2686f, 0.0327f, 60.06f),
            ),
        )

        val Amber = NeoColorScheme(
            name = "amber",
            light = NeoColors(
                primary = oklchColor(0.8408f, 0.1725f, 84.2f),
                background = oklchColor(0.9622f, 0.0569f, 95.61f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7770f, 0.1594f, 84.38f),
                background = oklchColor(0.2891f, 0.0359f, 90.09f),
            ),
        )

        val Yellow = NeoColorScheme(
            name = "yellow",
            light = NeoColors(
                primary = oklchColor(0.8603f, 0.1760f, 92.36f),
                background = oklchColor(0.9679f, 0.0654f, 102.26f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7936f, 0.1624f, 92.49f),
                background = oklchColor(0.2928f, 0.0373f, 94.38f),
            ),
        )

        val Lime = NeoColorScheme(
            name = "lime",
            light = NeoColors(
                primary = oklchColor(0.8329f, 0.2331f, 132.51f),
                background = oklchColor(0.9537f, 0.0549f, 125.19f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7626f, 0.2131f, 132.40f),
                background = oklchColor(0.2310f, 0.0346f, 126.75f),
            ),
        )

        val Green = NeoColorScheme(
            name = "green",
            light = NeoColors(
                primary = oklchColor(0.7976f, 0.2044f, 153.08f),
                background = oklchColor(0.9647f, 0.0401f, 157.79f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7303f, 0.1865f, 153.23f),
                background = oklchColor(0.2245f, 0.0316f, 158.41f),
            ),
        )

        val Emerald = NeoColorScheme(
            name = "emerald",
            light = NeoColors(
                primary = oklchColor(0.7754f, 0.1681f, 162.78f),
                background = oklchColor(0.9531f, 0.0496f, 169.04f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7054f, 0.1525f, 162.97f),
                background = oklchColor(0.2271f, 0.0252f, 182.05f),
            ),
        )

        val Teal = NeoColorScheme(
            name = "teal",
            light = NeoColors(
                primary = oklchColor(0.7857f, 0.1422f, 180.36f),
                background = oklchColor(0.9508f, 0.0481f, 184.07f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7147f, 0.1293f, 180.47f),
                background = oklchColor(0.2265f, 0.0236f, 198.49f),
            ),
        )

        val Cyan = NeoColorScheme(
            name = "cyan",
            light = NeoColors(
                primary = oklchColor(0.7689f, 0.1392f, 219.13f),
                background = oklchColor(0.9461f, 0.0430f, 211.12f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6437f, 0.1162f, 218.75f),
                background = oklchColor(0.2711f, 0.0303f, 225.38f),
            ),
        )

        val Sky = NeoColorScheme(
            name = "sky",
            light = NeoColors(
                primary = oklchColor(0.6690f, 0.1837f, 248.81f),
                background = oklchColor(0.9427f, 0.0268f, 242.57f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6190f, 0.1691f, 248.60f),
                background = oklchColor(0.2708f, 0.0336f, 240.69f),
            ),
        )

        val Blue = NeoColorScheme(
            name = "blue",
            light = NeoColors(
                primary = oklchColor(0.6747f, 0.1726f, 259.49f),
                background = oklchColor(0.9346f, 0.0305f, 255.11f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6747f, 0.1726f, 259.49f),
                background = oklchColor(0.2923f, 0.0626f, 270.49f),
            ),
        )

        val Indigo = NeoColorScheme(
            name = "indigo",
            light = NeoColors(
                primary = oklchColor(0.6634f, 0.1806f, 277.20f),
                background = oklchColor(0.9213f, 0.0388f, 282.36f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6634f, 0.1806f, 277.20f),
                background = oklchColor(0.2658f, 0.0737f, 283.96f),
            ),
        )

        val Violet = NeoColorScheme(
            name = "violet",
            light = NeoColors(
                primary = oklchColor(0.7028f, 0.1753f, 295.36f),
                background = oklchColor(0.9388f, 0.0330f, 300.19f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.7028f, 0.1753f, 295.36f),
                background = oklchColor(0.3014f, 0.0826f, 296.50f),
            ),
        )

        val Purple = NeoColorScheme(
            name = "purple",
            light = NeoColors(
                primary = oklchColor(0.7190f, 0.1980f, 310.03f),
                background = oklchColor(0.9411f, 0.0366f, 308.03f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6734f, 0.2314f, 309.13f),
                background = oklchColor(0.2968f, 0.0791f, 315.62f),
            ),
        )

        val Fuchsia = NeoColorScheme(
            name = "fuchsia",
            light = NeoColors(
                primary = oklchColor(0.7343f, 0.2332f, 321.41f),
                background = oklchColor(0.9479f, 0.0407f, 320.60f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6062f, 0.2915f, 319.64f),
                background = oklchColor(0.2629f, 0.0683f, 327.30f),
            ),
        )

        val Pink = NeoColorScheme(
            name = "pink",
            light = NeoColors(
                primary = oklchColor(0.7150f, 0.1970f, 354.23f),
                background = oklchColor(0.9516f, 0.0242f, 343.23f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6598f, 0.2407f, 358.64f),
                background = oklchColor(0.2630f, 0.0540f, 358.23f),
            ),
        )

        val Rose = NeoColorScheme(
            name = "rose",
            light = NeoColors(
                primary = oklchColor(0.7079f, 0.1862f, 16.25f),
                background = oklchColor(0.9337f, 0.0339f, 12.05f),
            ),
            dark = NeoColors(
                primary = oklchColor(0.6758f, 0.2135f, 18.63f),
                background = oklchColor(0.2515f, 0.0495f, 7.54f),
            ),
        )

        val All = listOf(
            Red, Orange, Amber, Yellow,
            Lime, Green, Emerald, Teal,
            Cyan, Sky, Blue, Indigo,
            Violet, Purple, Fuchsia, Pink, Rose,
        )
    }
}
