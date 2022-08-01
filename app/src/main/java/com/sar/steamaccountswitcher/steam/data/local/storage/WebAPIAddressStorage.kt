package com.sar.steamaccountswitcher.steam.data.local.storage

import com.sar.steamaccountswitcher.common.storage.data.PreferencesStorage
import com.sar.steamaccountswitcher.common.storage.data.SharedPreferencesDataSource

class WebAPIAddressStorage(key: String, dataSource: SharedPreferencesDataSource) :
    PreferencesStorage<String>(
        key,
        dataSource
    ) {

    override fun set(value: String) = dataSource.set(key, value)

    override fun get(defaultValue: String) = dataSource.get(key, defaultValue)
}
