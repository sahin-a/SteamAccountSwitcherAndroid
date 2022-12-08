package com.sar.steamaccountswitcher.steam.data.local.storage

import com.sar.steamaccountswitcher.common.storage.data.SharedPreferencesDataSource

class WebAPIAllowSelfSignedCertsStorage(
    private val key: String,
    private val dataSource: SharedPreferencesDataSource
) {

    fun set(value: Boolean) = dataSource.setBool(key, value)

    fun get(defaultValue: Boolean) = dataSource.getBool(key, defaultValue)
}