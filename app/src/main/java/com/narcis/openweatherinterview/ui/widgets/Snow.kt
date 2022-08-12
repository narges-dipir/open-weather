package com.narcis.openweatherinterview.ui.widgets

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.ui.theme.Blue300
import kotlin.math.roundToInt



@Composable
fun AnimatableSnow(modifier: Modifier = Modifier) {
    Snow(modifier , true)
}

@Composable
fun Snow(modifier: Modifier = Modifier, animate : Boolean = false) {
Layout(modifier = modifier,
       content = {
           if (animate) {
               AnimationSnowDrop(modifier.fillMaxSize(), 2200)
               AnimationSnowDrop(modifier.fillMaxSize(), 1600)
               AnimationSnowDrop(modifier.fillMaxSize(), 1800)
           } else {
               SnowDrop(modifier.fillMaxSize())
               SnowDrop(modifier.fillMaxSize())
               SnowDrop(modifier.fillMaxSize())

           }
       }) { measurables, constraints ->
    val placeables = measurables.mapIndexed {index, measurable ->
        val height = when(index) {
            0 -> constraints.maxHeight * 0.6f
            1 -> constraints.maxHeight * 1.0f
            2 -> constraints.maxHeight * 0.7f
            else -> 0f
        }
        measurable.measure(
            constraints.copy(
                minWidth = 0,
                minHeight =  0,
                maxWidth = constraints.maxWidth / 5,
                maxHeight = height.roundToInt()
            )
        )
    }

    layout(constraints.maxWidth, constraints.maxHeight) {
    var xPosition = constraints.maxWidth / (placeables.size + 1)

     placeables.forEachIndexed { index, placeable ->
         placeable.place(x = xPosition, y = -(constraints.maxHeight * 0.2).roundToInt())
         xPosition += (constraints.maxWidth / ((placeables.size + 1) * 0.9f)).roundToInt()
     }
    }

}
}

@Composable
fun AnimationSnowDrop(modifier: Modifier = Modifier, duration: Int = 1000) {
    val transition = rememberInfiniteTransition()

    val animateY by transition.animateFloat(
        initialValue = 0f,
        targetValue = 2.5f,
        animationSpec = infiniteRepeatable(
            tween(duration, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    val animateX by transition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(duration / 3,
            easing = LinearEasing),
        RepeatMode.Reverse)
    )

    val animateAlpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(duration,
            easing = FastOutLinearInEasing))
    )
SnowDrop(modifier = modifier, animateX, animateY, animateAlpha)
}

@Composable
fun SnowDrop(modifier: Modifier = Modifier,
             xOffset : Float = 1f,
             yOffset : Float = 1f,
            alpha: Float = 1f) {
        Canvas(modifier){
           val radius = size.width / 2

           val _center = center.copy(
               x = center.x + center.x * xOffset,
               y = center.y + center.y + yOffset
           )

           drawCircle(color = Color.White.copy(alpha = alpha),
           center = _center,
           radius = radius)

           drawCircle(
               color = Blue300.copy(alpha = alpha),
               center = _center,
               radius = radius,
               style = Stroke(width = radius * 0.5f)
           )
        }
}

@Preview
@Composable
fun PreviewSnow() {
    Row {
        Column {
            AnimatableSnow(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
            )
        }
    }
}