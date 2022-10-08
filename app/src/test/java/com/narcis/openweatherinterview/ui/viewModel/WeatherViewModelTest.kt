package com.narcis.openweatherinterview.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.github.javafaker.Faker
import com.narcis.database.domain.weather.GetAllWeatherItemsUseCase
import com.narcis.database.domain.weather.GetWeatherByNameUseCase
import com.narcis.database.domain.weather.SaveWeatherItemUseCase
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.openweatherinterview.coroutine.MainTestCoroutine
import com.narcis.openweatherinterview.coroutine.runBlockingTest
import com.narcis.openweatherinterview.domain.useCase.GetCurrentLocationUseCase
import com.narcis.openweatherinterview.domain.useCase.weather.GetCurrentWeatherUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime


const val currentVersionDate = "20220826"
class WeatherViewModelTest {
    @get:Rule
    val mainTestCoroutine = MainTestCoroutine()

    @get:Rule
    val instantTaskCoroutine = InstantTaskExecutorRule()

    @MockK
    private lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    @MockK
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @MockK
    private lateinit var saveWeatherItem: SaveWeatherItemUseCase

    @MockK
    private lateinit var getAllWeatherItemsUseCase: GetAllWeatherItemsUseCase

    @MockK
    private lateinit var getWeatherByNameUseCase: GetWeatherByNameUseCase
    private val locationModel by lazy {
        LocationModel(
            Faker().address().latitude().toDouble(),
            Faker().address().longitude().toDouble()
        )
    }
    private lateinit var weatherViewModel: WeatherViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        weatherViewModel = WeatherViewModel(
            getCurrentWeatherUseCase,
            getCurrentLocationUseCase,
            getAllWeatherItemsUseCase,
            getWeatherByNameUseCase
        )
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `get weather by location`() =
        mainTestCoroutine.runBlockingTest {
            coEvery { getCurrentLocationUseCase(Unit) } returns
                    flowOf(ResultWrapper.Success(locationModel))

            coEvery { getCurrentWeatherUseCase(any()) } returns flowOf(ResultWrapper.Success(WEATHER_ITEM))

            weatherViewModel.getWeatherByLat()
            weatherViewModel.weatherResultsByLocation.test {
                Assert.assertEquals(expectItem(), WEATHER_ITEM)
            }

        }



}