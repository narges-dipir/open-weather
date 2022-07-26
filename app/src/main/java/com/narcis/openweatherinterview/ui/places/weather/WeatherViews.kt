package com.narcis.openweatherinterview.ui.places.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.narcis.openweatherinterview.R
import com.narcis.openweatherinterview.data.model.Temperature
import com.narcis.openweatherinterview.data.model.Weather
import com.narcis.openweatherinterview.data.model.WeatherItem


@Composable
fun WeatherDaily(weathers : List<WeatherItem>) {
    LazyRow {
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(
                WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
            )
            )
        }
        itemsIndexed(weathers) {index, item ->
            if (index > 0)
                Divider(thickness = 8.dp)
                Spacer(modifier = Modifier.padding(2.dp))


            WeatherItem(weatherItem = item)
        }
    }
}



@Composable
fun WeatherItem(
    weatherItem: WeatherItem
) {
  Column(
      modifier = Modifier.clickable {  },
  horizontalAlignment = Alignment.CenterHorizontally) {
      
      Box(modifier = Modifier
          .requiredSize(70.dp)
          .clip(CircleShape)) {
    Image(painter = painterResource(id =  R.drawable.few_cloud_morning_foreground),
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
          text = "19 °C",
          style = MaterialTheme.typography.body1,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
      )
      
  }
}


@Composable
fun ForecastWeakly( weathers: List<WeatherItem> ) {
LazyColumn {
    item {
        Spacer(modifier = Modifier.padding(4.dp))
    }

    itemsIndexed(weathers) { index, item ->
        if (index > 0)
            Divider(thickness = 4.dp)
        ForecastWeaklyItem(item)
    }

}
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForecastWeaklyItem(weatherItem: WeatherItem) {
    ConstraintLayout(modifier =
    Modifier
        .fillMaxWidth()
        .padding(2.dp)) {
        val (day, temps) = createRefs()
        createHorizontalChain(day, temps, chainStyle = ChainStyle.SpreadInside)
        
        Text(text = "Tomorrow",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.constrainAs(day) {
            top.linkTo(parent.top, margin = 4.dp)
            start.linkTo(parent.start)
        })
        Row(modifier = Modifier.constrainAs(temps) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)

        }) {

            Image(
                painter = painterResource(id = R.drawable.few_cloud_morning_foreground),
                contentDescription = "icon",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(text = "26°",
            modifier = Modifier.padding(2.dp))
            Text(text = "/",
                modifier = Modifier.padding(2.dp))
            Text(text = "17°",
                modifier = Modifier.padding(2.dp),
            color = Color.White.copy(alpha = 0.7f))



        }
    }
}

@Preview
@Composable
fun previewLocationNoted() {

 var wth : Weather = Weather("clear", "sunny")
val tmp : Temperature
= Temperature(44.4, 55.55, 66.6)
    var weather : WeatherItem = WeatherItem(wth, tmp, "Tehran")
//    WeatherItem(weather)
    ForecastWeaklyItem( weather)
}
