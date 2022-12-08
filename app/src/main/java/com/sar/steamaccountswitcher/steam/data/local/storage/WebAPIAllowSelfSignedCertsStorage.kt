package com.sar.steamaccountswitcher.steam.data.local.storage

import com.sar.steamaccountswitcher.common.storage.data.PreferencesStorage
import com.sar.steamaccountswitcher.common.storage.data.SharedPreferencesDataSource

class WebAPIAllowSelfSignedCertsStorage(key: String, dataSource: SharedPreferencesDataSource) :
    PreferencesStorage<Boolean>(
        key,
        dataSource
    ) {

    override fun set(value: Boolean) = dataSource.set(key, value.toString())

    override fun get(defaultValue: Boolean) =
        dataSource.get(key, defaultValue.toString()).toBoolean()
}