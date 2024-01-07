package app.simple.movies.common.ui

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun Bitmap.findDominantColor(): Color {
    val palette = Palette.from(this).generate()

    val rgb = with(palette) {
        getLightVibrantColor(getVibrantColor(getDarkVibrantColor(getDominantColor(0))))
    }
    return Color(rgb)
}