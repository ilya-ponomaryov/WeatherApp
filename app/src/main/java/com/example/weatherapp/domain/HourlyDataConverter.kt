package com.example.weatherapp.domain

import android.util.Log
import com.example.weatherapp.data.models.Hourly
import java.util.*
import kotlin.collections.ArrayList

class HourlyDataConverter {
    fun getHourlyData(hourlyList: List<Hourly>): List<List<Hourly>> {
        val hours = arrayListOf(0, 3, 6, 9, 12, 15, 18, 21)
        val newList = arrayListOf<Hourly>()
        val bufferList = arrayListOf<Hourly>()
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
        for (n in newList) {
            val localDate = Date(n.dt.toLong() * 1000)
            if (bDay == localDate.day) {
                for (h in hours) {
                    if (localDate.hours == h) {
                        //bufferList.add(n)
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
                bDay = localDate.day
                Log.d("AddToData", "Add to data")
                p=+1
            }

        }


        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)


        //Log.d("Today", today.toString())
        /*for (i in hourlyList) {
            val date = Date(i.dt.toLong() * 1000)
            val bufferDay = date.day
            Log.d("Date", date.day.toString())
            Log.d("bufferDay", bufferDay.toString())
            if (bufferDay != today.day) {
                var p = 0
                for (j in hourlyList) {
                    Log.d("J", j.toString())
                    val ld = Date(j.dt.toLong() * 1000)
                    if (ld.day == bufferDay) {
                        for (h in hours) {
                            //Log.d("Ld", ld.hours.toString())
                            if (ld.hours == h.toInt()) {
                                Log.d("h", h.toString())
                                Log.d("ldh", ld.hours.toString())
                                bufferList.add(j)
                                Log.d("Add", "Add")
                                Log.d("P", p.toString())

                            }
                        }
                    } else  if (ld.day != today.day && p < 4){
                        data.add(bufferList)
                        Log.d("DataSize", data.size.toString())
                        bufferList.clear()
                        Log.d("Add", "Add to list")
                        p += 1
                    }

                }
            }
        }*/

        Log.d("data", data.toString())
        Log.d("dataSize", data.size.toString())
        return data
    }
}