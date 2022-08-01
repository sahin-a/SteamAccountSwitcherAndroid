package com.sar.steamaccountswitcher.steam.data

import androidx.preference.PreferenceManager
import com.sar.steamaccountswitcher.R
import com.sar.steamaccountswitcher.common.data.storage.SharedPreferencesDataSource
import com.sar.steamaccountswitcher.steam.data.local.storage.WebAPIAddressStorage
import com.sar.steamaccountswitcher.steam.data.logging.LoggerImpl
import com.sar.steamaccountswitcher.steam.data.remote.service.SteamAccountSwitcherAPIFactory
import com.sar.steamaccountswitcher.steam.data.remote.service.SteamAccountSwitcherServiceImpl
import com.sar.steamaccountswitcher.steam.domain.logging.Logger
import com.sar.steamaccountswitcher.steam.domain.repository.SteamAccountSwitcherService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<Logger> { LoggerImpl() }
    single { SharedPreferencesDataSource(PreferenceManager.getDefaultSharedPreferences(get())) }
    single {
        val key = androidContext().getString(R.string.sas_webapi_address_preference_key)
        WebAPIAddressStorage(key, get())
    }
    single<SteamAccountSwitcherService> {
        val clientFactory = SteamAccountSwitcherAPIFactory(get())
        SteamAccountSwitcherServiceImpl(clientFactory)
    }
}
