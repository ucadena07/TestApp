package com.example.testapp.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.Instant

import java.time.ZoneId
fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("MM/dd/yyyy")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDateTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}
fun formatApiDateToTimestamp(dateString: String?): String {
     if(!dateString.isNullOrBlank()){
        val formatter = DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
            .toFormatter()
         return try {
             val dateTime = LocalDateTime.parse(dateString, formatter)
             val timestamp = dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
             // Convert timestamp to LocalDateTime
             val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
             // Define the date format
             val formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy")

             // Format LocalDateTime to string
            return  dateTime.format(formatter2)

         }catch (e: DateTimeParseException) {
             println("Failed to parse the date.")
             e.localizedMessage?.let { Log.d("PARSING ERROR", it) }
             ""
         }

    }else{
       return  ""
    }
}

fun hasRequiredPermissions(context: Context): Boolean{
    return  SD.CAMERAX_PERMISSIONS.all{
        ContextCompat.checkSelfPermission(
                context,it
                ) == PackageManager.PERMISSION_GRANTED
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}