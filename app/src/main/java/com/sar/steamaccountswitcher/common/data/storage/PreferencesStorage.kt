package com.sar.steamaccountswitcher.common.data.storage

abstract class PreferencesStorage<T>(
    protected val key: String,
    protected val dataSource: KeyValueDataSource
) : Storage<T>
