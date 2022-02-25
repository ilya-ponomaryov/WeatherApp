package com.example.weatherapp.domain

import android.util.Log
import com.example.weatherapp.data.models.Hourly
import java.util.*
import kotlin.collections.ArrayList

class HourlyDataConverter {
    fun getHourlyData(hourlyList: List<Hourly>): List<List<Hourly>> {
        val hours = arrayListOf(0, 3, 6, 9, 12, 15, 18, 21)
        val newList = arrayListOf<Hourly>()
        val data = arrayListOf<ArrayList<Hourly>>()
        val data1 = arrayListOf<Hourly>()
        val data2 = arrayListOf<Hourly>()
        val data3 = arrayListOf<Hourly>()
        val data4 = arrayListOf<Hourly>()
        val today = Date(hourlyList[0].dt.toLong() * 1000)

        for (i in hourlyList) {
            val d = Date(i.dt.toLong() * 1000)
            if (d.day != today.day) {
                newList.add(i)
                Log.d("NewList", newList.toString())
            }
        }
        Log.d("NewListSize", newList.size.toString())
        val b = Date(newList[0].dt.toLong() * 1000)
        var bDay = b.day
        var p = 0
        var bData: Hourly? = null
        for (n in newList) {
            val localDate = Date(n.dt.toLong() * 1000)
            if (bDay == localDate.day) {
                for (h in hours) {
                    if (localDate.hours == h) {
                        if (bData != null) {
                                when(p) {
                                    0 -> data1.add(bData)
                                    1 -> data2.add(bData)
                                    2 -> data3.add(bData)
                                    3 -> data4.add(bData)
                                }
                            bData = null
                        }
                        Log.d("Hour", h.toString())
                        when(p) {
                            0 -> data1.add(n)
                            1 -> data2.add(n)
                            2 -> data3.add(n)
                            3 -> data4.add(n)
                        }


                    }
                }
            } else {
                bData = n
                bDay = localDate.day
                p=+1
            }
        }
        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)
        return data
    }
}