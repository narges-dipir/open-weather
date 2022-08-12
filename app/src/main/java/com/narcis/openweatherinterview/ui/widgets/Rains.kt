package com.narcis.openweatherinterview.ui.widgets

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.ui.theme.Blue300
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt


@Composable
fun AnimatableRains(modifier: Modifier = Modifier, lightRain: Boolean = false) {
    Rains(modifier, true, lightRain)
}


@Composable
fun Rains(modifier: Modifier = Modifier,
        animate :Boolean = false,
        lightRain: Boolean = false) {

    Layout(modifier = modifier,
    content = {
        if (animate) {
            AnimatableRainDrops(modifier.fillMaxSize(), 500)
            AnimatableRainDrops(modifier.fillMaxSize(), 600)
            if (!lightRain) { AnimatableRainDrops(modifier.fillMaxSize(), 600) }
        } else {
            RainDrop(modifier.fillMaxSize())
            RainDrop(modifier.fillMaxSize())
            if (!lightRain) {
                RainDrop(modifier.fillMaxSize())
            }
        }

    }) { measureables, constraints ->
        val placesables = measureables.mapIndexed{ index, measurable ->
            val height = when(index) {
                0 -> constraints.maxHeight * 0.8f
                1 -> constraints.maxHeight * 0.9f
                2 -> constraints.maxHeight * 0.6f
                else -> 0f
            }


            measurable.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = constraints.maxWidth / 10, // raindrop width
                    maxHeight = height.toInt(),
                )
            )
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var xPosition = constraints.maxWidth / ((placesables.size + 1) * 2)

            // Place children in the parent layout
            placesables.forEachIndexed { index, placeable ->
                // Position item on the screen
                placeable.place(x = xPosition, y = 0)

                // Record the y co-ord placed up to
                xPosition += (constraints.maxWidth / ((placesables.size + 1) * 0.8f)).roundToInt()
            }
        }
    }

}




@Composable
fun AnimatableRainDrops(modifier: Modifier = Modifier,
    durationMillis: Int = 800) {
    val transition = rememberInfiniteTransition()
    val animate by transition.animateFloat(initialValue = 0f
        , targetValue = 1f, animationSpec =
        infiniteRepeatable(
            tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ))
    RainDrop(modifier = modifier, animate)
}

@Composable
fun RainDrop( modifier: Modifier = Modifier, spacePosition: Float = 0f ) {

    Canvas(modifier) {
        val width = size.width
        val x: Float = size.width / 2
        val scopeHeight = size.height - width / 2

        val space = size.height / 2.2f + width / 2
        val spacePos = scopeHeight * spacePosition
        val sy1 = spacePos - space / 2
        val sy2 = spacePos + space / 2

        val lineHeight = scopeHeight - space

        val line1y1 = max(0f, sy1 - lineHeight)
        val line1y2 = max(line1y1, sy1)

        val line2y1 = min(sy2, scopeHeight)
        val line2y2 = min(line2y1 + lineHeight, scopeHeight)

        drawLine(
            Color.Blue,
            Offset(x, line1y1),
            Offset(x, line1y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(
                Color.Blue
            ),
            cap = StrokeCap.Round
        )

        drawLine(
            Blue300,
            Offset(x, line2y1),
            Offset(x, line2y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(
                Blue300
            ),
            cap = StrokeCap.Round
        )

    }
}

@Preview
@Composable
fun PreviewRains() {
    Row {
        Column {
            Rains(modifier = Modifier.size(150.dp))
            Spacer(Modifier.height(5.dp))
            AnimatableRains(modifier = Modifier.size(150.dp))
        }
        Divider(
            Modifier
                .height(300.dp)
                .padding(10.dp)
                .width(1.dp)
        )
        Column {
            Rains(modifier = Modifier.size(150.dp), lightRain = true)
            Spacer(Modifier.height(5.dp))
            AnimatableRains(modifier = Modifier.size(150.dp), lightRain = true)
        }
    }
}
