package com.sar.steamaccountswitcher.common.storage.data

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesDataSource(private val sharedPreferences: SharedPreferences) {

    fun getString(key: String, defaultValue: String): String =
        sharedPreferences.getString(key, defaultValue)!!

    fun setString(key: String, value: String) = sharedPreferences.edit {
        putString(key, value)
        commit()
    }

    fun getBool(key: String, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    fun setBool(key: String, value: Boolean) = sharedPreferences.edit {
        putBoolean(key, value)
        commit()
    }

}
