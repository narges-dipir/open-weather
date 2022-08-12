package com.narcis.openweatherinterview.ui.widgets

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.R



@Composable
fun AnimationCloud(modifier: Modifier = Modifier, duration: Int = 1500){

    val transient = rememberInfiniteTransition()
    val animateTween by transient.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(delayMillis = duration, easing = LinearEasing), RepeatMode.Reverse
        )
    )

    Cloud(modifier = modifier.offset(5.dp * animateTween))
}


@Composable
fun Cloud(modifier: Modifier = Modifier) {

    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_cloud)
        , contentDescription = "", modifier = modifier)

}

@Preview
@Composable
fun previewCloud() {
    AnimationCloud()
}