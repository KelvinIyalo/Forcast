package com.example.forcast.data.provider

import com.example.forcast.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}