package com.sar.steamaccountswitcher.common.data.storage

interface Storage<T> {
    fun set(value: T)
    fun get(defaultValue: T): T
}
