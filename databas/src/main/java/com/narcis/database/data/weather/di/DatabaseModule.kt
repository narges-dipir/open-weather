package com.narcis.database.data.weather.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.*
import com.narcis.database.data.weather.db.JsonConverter
import com.narcis.database.data.weather.db.MIGRATIONS
import com.narcis.database.data.weather.db.OpenWeatherDatabase
import com.narcis.database.data.weather.db.WeatherItemMapper
import com.narcis.database.data.weather.db.WeatherItemMapperImp
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideOpenWeatherDatabase(@ApplicationContext context: Context) : OpenWeatherDatabase {
        return Room.databaseBuilder(
            context,
            OpenWeatherDatabase::class.java,
            "openWeather.db"
        ).addMigrations(*MIGRATIONS).build()
    }



    @Provides
    @Singleton
    fun provideWeatherTable(openWeatherDatabase: OpenWeatherDatabase) : WeatherDao {
        return openWeatherDatabase.weatherDao

    }

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi) : JsonConverter = JsonConverter(moshi)

    @Provides
    @Singleton
    fun provideWeatherItemMapper(jsonConverter: JsonConverter) : WeatherItemMapperImp {
        return WeatherItemMapperImp(jsonConverter)
    }
}