 package com.narcis.openweatherinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.narcis.openweatherinterview.ui.places.viewModel.ForecastViewModel
import com.narcis.openweatherinterview.ui.places.viewModel.WeatherViewModel
import com.narcis.openweatherinterview.ui.places.weather.WeatherContent
import com.narcis.openweatherinterview.ui.theme.OpenWeatherInterviewTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


 @AndroidEntryPoint
 class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OpenWeatherInterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlacesCategoriesDestination()
//                    WeatherContent()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

 @ExperimentalPermissionsApi
 @Composable fun PlacesCategoriesDestination() {
     val weatherViewModel: WeatherViewModel = hiltViewModel()
     val forecastViewModel : ForecastViewModel = hiltViewModel()
     weatherViewModel.getWeatherByLat()
     forecastViewModel.getForecastByLat()
     WeatherContent(weatherViewModel, forecastViewModel)
 }
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OpenWeatherInterviewTheme {
        val viewModel: WeatherViewModel = hiltViewModel()
        val forecastViewModel : ForecastViewModel = hiltViewModel()

        WeatherContent(viewModel, forecastViewModel)
    }
}