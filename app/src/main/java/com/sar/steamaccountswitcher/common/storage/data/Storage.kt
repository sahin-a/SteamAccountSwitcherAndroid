package com.sar.steamaccountswitcher.common.storage.data

interface Storage<T> {
    fun set(value: T)
    fun get(defaultValue: T): T
}
