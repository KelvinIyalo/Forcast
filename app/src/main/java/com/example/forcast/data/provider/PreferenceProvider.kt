package com.example.forcast.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

abstract class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    val prefrences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
}