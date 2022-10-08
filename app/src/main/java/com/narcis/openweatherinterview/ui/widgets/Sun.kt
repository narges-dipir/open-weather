package com.narcis.openweatherinterview.ui.widgets

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.ui.theme.OrangeLittle
import com.narcis.openweatherinterview.ui.theme.YellowLittle
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimationSun(modifier: Modifier = Modifier) {
 val transient = rememberInfiniteTransition()
 val a : State<Float> = transient.animateFloat(initialValue = 0f,
     targetValue = 360f, animationSpec = infiniteRepeatable(tween(8000),
         RepeatMode.Reverse))
    val animateTween by transient.animateFloat(initialValue = 0f,
        targetValue = 360f, animationSpec = infiniteRepeatable(tween(8000),
        RepeatMode.Reverse))

    Canvas(modifier.rotate(animateTween)) {

        val radius = {  size.width / 6}
        val stroke = {size.width / 20 }
        val centerOffset = {Offset(size.width / 30, size.width / 30) }
        val centerValue = { center + centerOffset() }

        // draw circle
        drawCircle(
            color = OrangeLittle,
            radius = radius() + stroke() / 2,
            style = Stroke(width = stroke()),
            center = centerValue()
        )
        drawCircle(
            color = Color.White,
            radius = radius(),
            style = Fill,
            center = centerValue()
        )

        // draw line

        val lineLength = {radius() * 0.6f }
        val lineOffset = {radius() * 1.8f }
        (0..7).forEach { i ->

            val radians = {Math.toRadians(i * 45.0)}

            val offsetX = {lineOffset() * cos(radians()).toFloat() }
            val offsetY = { lineOffset() * sin(radians()).toFloat() }

            val x1 = {size.width / 2 + offsetX() + centerOffset().x }
            val x2 = { x1() + lineLength() * cos(radians()).toFloat() }

            val y1 = {size.height / 2 + offsetY() + centerOffset().y}
            val y2 = {y1() + lineLength() * sin(radians()).toFloat()}

            drawLine(
                color = YellowLittle,
                start = Offset(x1(), y1()),
                end = Offset(x2(), y2()),
                strokeWidth = stroke(),
                cap = StrokeCap.Round
            )
        }
    }

}


@Composable
fun Sun(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.width / 6
        val stroke = size.width / 20

        drawCircle(
            color = OrangeLittle,
            radius = radius + stroke/2,
            style = Stroke(width = stroke)
        )

        drawCircle(
            color = Color.White,
            radius = radius,
            style = Fill
        )

        val lineLength = radius * 0.2f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->
            val radian = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radian).toFloat()
            val offsetY = lineOffset * sin(radian).toFloat()

            val x1 = size.width /2 + offsetX
            val x2 = x1 + lineLength * cos(radian).toFloat()

            val y1 = size.height /2 + offsetY
            val y2 = y1 + lineLength * sin(radian).toFloat()

            drawLine(
                color = Color.Yellow,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )

        }
    }
}

@Preview
@Composable
fun PreviewAnimatableSun() {
    AnimationSun(Modifier.size(100.dp))
}
