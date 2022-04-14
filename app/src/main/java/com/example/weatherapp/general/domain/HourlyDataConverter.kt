package com.example.weatherapp.general.domain


import com.example.weatherapp.general.data.weather.models.Hourly
import java.util.*
import kotlin.collections.ArrayList

class HourlyDataConverter {
    fun getHourlyData(hourlyList: List<Hourly>): List<List<Hourly>> {
        val newList = removeTodayDataFromList(hourlyList)

        return distributeDataByDay(newList)
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

        val data = arrayListOf<ArrayList<Hourly>>()
        val data1 = arrayListOf<Hourly>()
        val data2 = arrayListOf<Hourly>()
        val data3 = arrayListOf<Hourly>()
        val data4 = arrayListOf<Hourly>()

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
                                0 -> data1.add(hourlyItem)
                                1 -> data2.add(hourlyItem)
                                2 -> data3.add(hourlyItem)
                                3 -> data4.add(hourlyItem)
                            }
                            hourlyItem = null
                        }

                        when (position) {
                            0 -> data1.add(list)
                            1 -> data2.add(list)
                            2 -> data3.add(list)
                            3 -> data4.add(list)
                        }
                    }
                }
            } else {
                hourlyItem = list
                firstDay = localDate.day
                position = +1
            }
        }
        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)
        return data
    }
}