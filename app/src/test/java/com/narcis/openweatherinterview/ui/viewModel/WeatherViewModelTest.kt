package com.narcis.openweatherinterview.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.javafaker.Faker
import com.narcis.model.weatherActions.LocationModel
import com.narcis.openweatherinterview.coroutine.MainTestCoroutine
import com.narcis.openweatherinterview.domain.useCase.GetCurrentLocationUseCase
import io.mockk.impl.annotations.MockK
import org.junit.Rule


const val currentVersionDate = "20220826"
class WeatherViewModelTest {
    @get:Rule
    val mainTestCoroutine = MainTestCoroutine()

    @get:Rule
    val instantTaskCoroutine = InstantTaskExecutorRule()

    @MockK
    private lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    private val locationModel by lazy {
        LocationModel(
            Faker().address().latitude().toDouble(),
            Faker().address().longitude().toDouble()
        )
    }



}