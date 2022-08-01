package com.sar.steamaccountswitcher.common.storage.data

abstract class PreferencesStorage<T>(
    protected val key: String,
    protected val dataSource: KeyValueDataSource
) : Storage<T>
