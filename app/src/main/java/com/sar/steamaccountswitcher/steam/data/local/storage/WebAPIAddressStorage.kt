package com.sar.steamaccountswitcher.steam.data.local.storage

import com.sar.steamaccountswitcher.common.storage.data.SharedPreferencesDataSource

class WebAPIAddressStorage(
    private val key: String,
    private val dataSource: SharedPreferencesDataSource
) {

    fun set(value: String) = dataSource.setString(key, value)

    fun get(defaultValue: String) = dataSource.getString(key, defaultValue)
}
