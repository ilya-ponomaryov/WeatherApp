package com.example.weatherapp.general.domain


import com.example.weatherapp.general.data.weather.models.Hourly
import java.util.*
import kotlin.collections.ArrayList

class HourlyDataConverter {
    fun getHourlyData(hourlyList: List<Hourly>): List<List<Hourly>> {
        val hourlyWithoutTodayList = removeTodayDataFromList(hourlyList)

        return distributeDataByDay(hourlyWithoutTodayList)
    }

    private fun removeTodayDataFromList(hourlyList: List<Hourly>): List<Hourly> {
        val hourlyWithoutTodayList = arrayListOf<Hourly>()
        val today = Date(hourlyList[0].date.toLong() * 1000)

        for (hourly in hourlyList) {
            val hourlyDate = Date(hourly.date.toLong() * 1000)

            if (hourlyDate.day != today.day) {
                hourlyWithoutTodayList.add(hourly)
            }
        }
        return hourlyWithoutTodayList
    }

    private fun distributeDataByDay(newList: List<Hourly>): List<ArrayList<Hourly>> {
        val hours = arrayListOf(0, 3, 6, 9, 12, 15, 18, 21)

        val hourlyListByDays = arrayListOf<ArrayList<Hourly>>()
        val firstDayHourlyList = arrayListOf<Hourly>()
        val secondDayHourlyList = arrayListOf<Hourly>()
        val thirdDayHourlyList = arrayListOf<Hourly>()
        val fourthDayHourlyList = arrayListOf<Hourly>()

        val firstDate = Date(newList[0].date.toLong() * 1000)
        var firstDay = firstDate.day

        var position = 0

        var hourlyItem: Hourly? = null

        for (list in newList) {
            val localDate = Date(list.date.toLong() * 1000)
            if (firstDay == localDate.day) {
                for (hour in hours) {
                    if (localDate.hours == hour) {
                        if (hourlyItem != null) {
                            when (position) {
                                0 -> firstDayHourlyList.add(hourlyItem)
                                1 -> secondDayHourlyList.add(hourlyItem)
                                2 -> thirdDayHourlyList.add(hourlyItem)
                                3 -> fourthDayHourlyList.add(hourlyItem)
                            }
                            hourlyItem = null
                        }

                        when (position) {
                            0 -> firstDayHourlyList.add(list)
                            1 -> secondDayHourlyList.add(list)
                            2 -> thirdDayHourlyList.add(list)
                            3 -> fourthDayHourlyList.add(list)
                        }
                    }
                }
            } else {
                hourlyItem = list
                firstDay = localDate.day
                position = +1
            }
        }
        hourlyListByDays.add(firstDayHourlyList)
        hourlyListByDays.add(secondDayHourlyList)
        hourlyListByDays.add(thirdDayHourlyList)
        hourlyListByDays.add(fourthDayHourlyList)
        return hourlyListByDays
    }
}