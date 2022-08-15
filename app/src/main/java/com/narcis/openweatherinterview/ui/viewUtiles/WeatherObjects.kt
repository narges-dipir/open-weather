package com.narcis.openweatherinterview.ui.viewUtiles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.ui.widgets.AnimatableRains
import com.narcis.openweatherinterview.ui.widgets.AnimationCloud
import com.narcis.openweatherinterview.ui.widgets.AnimationThunder
import com.narcis.openweatherinterview.ui.widgets.Cloud


val ThunderRain = @Composable {
    Box(modifier = Modifier.size(40.dp)) {
        AnimatableRains(
            Modifier
                .size(25.dp)
                .offset(5.dp, 8.dp))
        AnimationCloud(
            Modifier
                .size(30.dp)
                .align(Alignment.TopCenter))
        AnimationThunder(
            Modifier
                .size(20.dp)
                .offset(10.dp, 18.dp), 300)
    }

}

val ThunderStorm = @Composable {
    Box(Modifier.size(40.dp)) {
        AnimationCloud(Modifier.size(30.dp))
        AnimationThunder(Modifier.size(20.dp).offset(10.dp, 18.dp), 300)
    }
}