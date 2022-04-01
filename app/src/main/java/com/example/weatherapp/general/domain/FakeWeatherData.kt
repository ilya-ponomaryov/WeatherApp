package com.example.weatherapp.general.domain

import com.example.weatherapp.general.data.weather.models.*

fun getFakeWeatherData(): WeatherData {
    val weatherList = listOf<Weather>(
        Weather(
            description = "Описание",
            icon = "04d",
            id = 0,
            main = "main"
        )
    )

    val current = Current(
        clouds = 0,
        dewPoint = 0.0,
        date = 1648020228,
        feelsLike = 0.0,
        humidity = 0,
        pressure = 0,
        sunrise = 0,
        sunset = 0,
        temperature = 0.0,
        uvi = 0.0,
        visibility = 0,
        weather = weatherList,
        windDegrees = 0,
        windGust = 0.0,
        windSpeed = 0.0,
    )

    return WeatherData(
        current,
        getDailyList(),
        getHourlyList(),
        lat = 0.0,
        lon = 0.0,
        timezone = "timezone",
        timezone_offset = 0
    )
}

private fun getDailyList(): List<Daily> {
    val feelsLike = FeelsLike(
        day = 0.0,
        eve = 0.0,
        morn = 0.0,
        night = 0.0
    )

    val temp = Temp(
        day = 0.0,
        eve = 0.0,
        max = 0.0,
        min = 0.0,
        morn = 0.0,
        night = 0.0
    )

    val weatherXList = listOf<WeatherX>(
        WeatherX(
            description = "Описанин",
            icon = "04d",
            id = 0,
            main = "main"
        )
    )

    return listOf(
        Daily(
            clouds = 0,
            dewPoint = 0.0,
            date = 1647916167,
            feelsLike = feelsLike,
            humidity = 0,
            moonPhase = 0.0,
            moonrise = 0,
            moonSet = 0,
            probabilityOfPrecipitation = 0.0,
            pressure = 0,
            rain = 0.0,
            snow = 0.0,
            sunrise = 0,
            sunset = 0,
            temperature = temp,
            uvi = 0.0,
            weather = weatherXList,
            windDegrees = 0,
            windGust = 0.0,
            windSpeed = 0.0
        ),
        Daily(
            clouds = 0,
            dewPoint = 0.0,
            date = 1645174800,
            feelsLike = feelsLike,
            humidity = 0,
            moonPhase = 0.0,
            moonrise = 0,
            moonSet = 0,
            probabilityOfPrecipitation = 0.0,
            pressure = 0,
            rain = 0.0,
            snow = 0.0,
            sunrise = 0,
            sunset = 0,
            temperature = temp,
            uvi = 0.0,
            weather = weatherXList,
            windDegrees = 0,
            windGust = 0.0,
            windSpeed = 0.0
        ),
        Daily(
            clouds = 0,
            dewPoint = 0.0,
            date = 1645261200,
            feelsLike = feelsLike,
            humidity = 0,
            moonPhase = 0.0,
            moonrise = 0,
            moonSet = 0,
            probabilityOfPrecipitation = 0.0,
            pressure = 0,
            rain = 0.0,
            snow = 0.0,
            sunrise = 0,
            sunset = 0,
            temperature = temp,
            uvi = 0.0,
            weather = weatherXList,
            windDegrees = 0,
            windGust = 0.0,
            windSpeed = 0.0
        ),
        Daily(
            clouds = 0,
            dewPoint = 0.0,
            date = 1645347600,
            feelsLike = feelsLike,
            humidity = 0,
            moonPhase = 0.0,
            moonrise = 0,
            moonSet = 0,
            probabilityOfPrecipitation = 0.0,
            pressure = 0,
            rain = 0.0,
            snow = 0.0,
            sunrise = 0,
            sunset = 0,
            temperature = temp,
            uvi = 0.0,
            weather = weatherXList,
            windDegrees = 0,
            windGust = 0.0,
            windSpeed = 0.0
        ),
        Daily(
            clouds = 0,
            dewPoint = 0.0,
            date = 1645434000,
            feelsLike = feelsLike,
            humidity = 0,
            moonPhase = 0.0,
            moonrise = 0,
            moonSet = 0,
            probabilityOfPrecipitation = 0.0,
            pressure = 0,
            rain = 0.0,
            snow = 0.0,
            sunrise = 0,
            sunset = 0,
            temperature = temp,
            uvi = 0.0,
            weather = weatherXList,
            windDegrees = 0,
            windGust = 0.0,
            windSpeed = 0.0
        )
    )
}

private fun getHourlyList(): List<Hourly> {
    val weatherXXList = listOf<WeatherXX>(
        WeatherXX(
            description = "Описание",
            icon = "04d",
            id = 0,
            main = "main"
        )
    )

    return listOf(
        Hourly(
            clouds = 0,
            dew_point = 0.0,
            dt = 1645174800,
            feels_like = 0.0,
            humidity = 0,
            pop = 0.0,
            pressure = 0,
            snow = Snow(oneHour = 0.0),
            temp = 0.0,
            uvi = 0.0,
            visibility = 0,
            weather = weatherXXList,
            wind_deg = 0,
            wind_gust = 0.0,
            wind_speed = 0.0
        ),
        Hourly(
            clouds = 0,
            dew_point = 0.0,
            dt = 1645261200,
            feels_like = 0.0,
            humidity = 0,
            pop = 0.0,
            pressure = 0,
            snow = Snow(oneHour = 0.0),
            temp = 0.0,
            uvi = 0.0,
            visibility = 0,
            weather = weatherXXList,
            wind_deg = 0,
            wind_gust = 0.0,
            wind_speed = 0.0
        ),
        Hourly(
            clouds = 0,
            dew_point = 0.0,
            dt = 1645444800,
            feels_like = 0.0,
            humidity = 0,
            pop = 0.0,
            pressure = 0,
            snow = Snow(oneHour = 0.0),
            temp = 0.0,
            uvi = 0.0,
            visibility = 0,
            weather = weatherXXList,
            wind_deg = 0,
            wind_gust = 0.0,
            wind_speed = 0.0
        )
    )
}