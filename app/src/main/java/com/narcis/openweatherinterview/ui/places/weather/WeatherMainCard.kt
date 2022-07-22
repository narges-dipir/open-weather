package com.narcis.openweatherinterview.ui.places.weather

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.google.android.gms.maps.model.SquareCap
import com.narcis.openweatherinterview.R

@Preview
@Composable
fun mat() {
    Surface(modifier = Modifier.fillMaxSize(),
    color = Color.Black) {
        MainCard()
    }

}

@Composable
fun MainCard() {

    LazyRow {

        item {
            Column {

                Text(
                    text = "23 °C",
                    style = MaterialTheme.typography.h1,
                    color = Color.White,

                    )
                Text(
                    text = "24 ° / 19 °",
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
                Row {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_location
                        ),
                        contentDescription = "location"
                    )

                    Text(
                        text = "New York",
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )

                }
            }

        }



        item {
            Box(
                modifier =
                Modifier
                    .requiredSize(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {


                Image(
                    painter = painterResource(
                        id = R.drawable.few_cloud_morning_foreground
                    ),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)

                )
            }
        }

    }

}
//    Column(
//        modifier = Modifier.clickable {  },
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = "23 °C",
//        style = MaterialTheme.typography.h1,
//        color = Color.White)
//    }

