package com.sar.steamaccountswitcher.common.data.storage

interface KeyValueDataSource {
    fun get(key: String, defaultValue: String): String
    fun set(key: String, value: String)
}
