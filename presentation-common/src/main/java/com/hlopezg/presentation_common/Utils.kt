package com.hlopezg.presentation_common

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.concurrent.TimeUnit

fun LocalDateTime.getCurrentTimeDifference(): String{
    val date = Date()
    val differenceInMilliseconds = date.time - this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val seconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMilliseconds)
    if(seconds < 60){
        return "$seconds seconds ago"
    }

    val minutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMilliseconds)
    if(minutes < 60){
        return "$minutes minutes ago"
    }

    val hours = TimeUnit.MILLISECONDS.toHours(differenceInMilliseconds)
    if(hours < 24){
        return "$hours hours ago"
    }

    val days = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds)
    if(days < 31){
        return "$days days ago"
    }

    val months = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds) / 30
    if(months < 12){
        return "$months months ago"
    }

    val years = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds) / 365
    return if(years < 2){
        "$years year ago"
    }else{
        "$years years ago"
    }
}