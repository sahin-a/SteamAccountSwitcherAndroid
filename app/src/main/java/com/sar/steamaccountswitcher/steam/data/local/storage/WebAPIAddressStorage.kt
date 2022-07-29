package com.sar.steamaccountswitcher.steam.data.local.storage

import com.sar.steamaccountswitcher.common.data.storage.PreferencesStorage
import com.sar.steamaccountswitcher.common.data.storage.SharedPreferencesDataSource

class WebAPIAddressStorage(key: String, dataSource: SharedPreferencesDataSource) :
    PreferencesStorage<String>(
        key,
        dataSource
    ) {

    override fun set(value: String) = dataSource.set(key, value)

    override fun get(defaultValue: String) = dataSource.get(key, defaultValue)
}
