package com.narcis.openweatherinterview.ui.places.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narcis.openweatherinterview.R
import com.narcis.openweatherinterview.data.model.Temperature
import com.narcis.openweatherinterview.data.model.Weather
import com.narcis.openweatherinterview.data.model.WeatherItem



@Composable
fun WeatherItem(
    weatherItem: WeatherItem
) {
  Column(
      modifier = Modifier.clickable {  },
  horizontalAlignment = Alignment.CenterHorizontally) {
      
      Box(modifier = Modifier
          .requiredSize(70.dp)
          .clip(CircleShape)
          .background(Color.Green)) {
Image(painter = painterResource(id =  R.drawable.ic_launcher_foreground),
    contentDescription = "img", modifier = Modifier
        .width(70.dp)
        .height(70.dp)
        .padding(8.dp)
        .align(Alignment.Center))

      }

      Text(
          text = weatherItem.name,
          style = MaterialTheme.typography.caption,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
      )

      Text(
          text = "6 p.m",
          style = MaterialTheme.typography.body1,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
      )
      
  }
}

@Preview
@Composable
fun previewLocationNoted() {

 var wth : Weather = Weather("clear", "sunny")
val tmp : Temperature
= Temperature(44.4, 55.55, 66.6)
    var weather : WeatherItem = WeatherItem(wth, tmp, "Tehran")
    WeatherItem(weather)
}