package com.narcis.openweatherinterview.ui.places.weather

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.maps.model.SquareCap
import com.narcis.openweatherinterview.R
import com.narcis.openweatherinterview.data.model.Temperature
import com.narcis.openweatherinterview.data.model.Weather
import com.narcis.openweatherinterview.data.model.WeatherItem

@Preview
@Composable
fun mat() {
    //Surface(modifier = Modifier.fillMaxSize(),
    //color = Color.Black) {
    WeatherContent()
    //}

}

@Composable
fun WeatherContent() {
    LazyColumn {
        item {
            MainCard()
        }
        item { 
            Spacer(modifier = Modifier.padding(16.dp))
        }
        item {
//            var lst : MutableList<WeatherItem> ?= null


                val wth = Weather("clear", "sunny")
            val tmp = Temperature(44.4, 55.55, 66.6)
            val weather = WeatherItem(wth, tmp, "Tehran")
            val lst : List<WeatherItem> = listOf(weather, weather,weather, weather,weather)
            WeatherDaily(weathers = lst)

        }
    }

}


@Composable
fun MainCard() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        val (mainTemp, otherTemp, locationIcon, location, image) = createRefs()
        createVerticalChain(mainTemp, otherTemp, locationIcon, chainStyle = ChainStyle.Packed)


        Text(
            text = "23 °C",
            style = MaterialTheme.typography.h3,
            color = Color.White,
            modifier = Modifier.constrainAs(mainTemp) {
                top.linkTo(parent.top, margin = 4.dp)
                start.linkTo(parent.start)

            }

        )
        Text(
            text = "24 ° / 19 °",
            style = MaterialTheme.typography.body1,
            color = Color.White.copy(alpha = (0.7f)),
            modifier = Modifier.constrainAs(otherTemp) {
                start.linkTo(mainTemp.end)
                end.linkTo(locationIcon.start)


            }
        )

        Card(
            modifier = Modifier
                .constrainAs(locationIcon) {},
            elevation = 4.dp,
            backgroundColor = Color.White.copy(alpha = 0.2f),
        ) {
        Row(modifier = Modifier.padding(2.dp)) {


            Image(
                painter = painterResource(
                    id = R.drawable.ic_location
                ),
                contentDescription = "location",
            )

            Text(
                text = "New York",
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )

        }
    }
        Image(
            painter = painterResource(
                id = R.drawable.few_cloud_morning_foreground
            ),
            contentDescription = "Avatar",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)

            }
        )
    }


}
