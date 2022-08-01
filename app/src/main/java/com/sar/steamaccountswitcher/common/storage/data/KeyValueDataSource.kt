package com.sar.steamaccountswitcher.common.storage.data

interface KeyValueDataSource {
    fun get(key: String, defaultValue: String): String
    fun set(key: String, value: String)
}
