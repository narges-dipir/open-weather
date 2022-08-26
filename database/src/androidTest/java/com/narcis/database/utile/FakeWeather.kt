package com.narcis.database.utile

import com.github.javafaker.Faker
import com.narcis.database.data.weather.daily.entities.WeatherEntity


private val faker : Faker by lazy {
    Faker()
}

internal val Weather_Faker = WeatherEntity(
    id = 800,
    main = "clear sky",
    name = faker.lorem().word(),
    description = faker.lorem().word(),
    temp = faker.number().digit().toDouble(),
    tempMax = faker.number().digit().toDouble(),
    tempMin = faker.number().digit().toDouble(),
)

