package app.antran.kmm.playground.android.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import app.antran.kmm.playground.android.R

@Composable
fun LocalImage(
    resName: String,
    modifier: Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale= ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    Image(
        imageVector = ImageVector.vectorResource(resNameToId(resName)),
        modifier = modifier,
        contentDescription = null,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

private fun resNameToId(resName: String): Int = when (resName) {
    "ic_edit.xml" -> R.drawable.ic_edit
    "ic_add.xml" -> R.drawable.ic_add
    else -> error("Unknown resName: $resName")
}