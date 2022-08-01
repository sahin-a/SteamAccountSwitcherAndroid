package com.sar.steamaccountswitcher.common.storage.data

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesDataSource(private val sharedPreferences: SharedPreferences) :
    KeyValueDataSource {

    override fun get(key: String, defaultValue: String): String =
        sharedPreferences.getString(key, defaultValue)!!

    override fun set(key: String, value: String) = sharedPreferences.edit {
        putString(key, value)
        commit()
    }
}
