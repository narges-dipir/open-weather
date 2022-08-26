package com.narcis.database.utile

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.narcis.database.data.weather.db.OpenWeatherDatabase


internal fun createTestDatabase() : OpenWeatherDatabase {
    return Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        OpenWeatherDatabase::class.java
    ).build()
}