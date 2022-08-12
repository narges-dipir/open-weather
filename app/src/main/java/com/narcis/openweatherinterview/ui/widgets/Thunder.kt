package com.narcis.openweatherinterview.ui.widgets

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.R


@Composable
fun AnimationThunder(modifier: Modifier = Modifier, duration: Int = 600) {
    val transition = rememberInfiniteTransition()

    val animationAlpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(tween(duration, easing =
        CubicBezierEasing(0f, 0.2f,0.7f, 1f),
        delayMillis = duration*3),
        RepeatMode.Reverse)
    )

    val animationY by transition.animateFloat(
        initialValue = -5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(duration /10,
         easing = LinearEasing, delayMillis = duration/5 ),
        RepeatMode.Reverse)
    )

    Thunder(modifier = modifier.alpha(alpha = animationAlpha % 2f)
        .offset(0.dp, animationY.dp))


}

@Composable
fun Thunder(modifier: Modifier = Modifier) {
    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_thunder),
        contentDescription = "",
    modifier = modifier.clearAndSetSemantics {  })
}

@Preview
@Composable
fun PreviewAnimatableThunder() {
    AnimationThunder()
}
