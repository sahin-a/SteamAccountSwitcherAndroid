package com.sar.steamaccountswitcher.steam

import androidx.preference.PreferenceManager
import com.sar.steamaccountswitcher.R
import com.sar.steamaccountswitcher.common.storage.data.SharedPreferencesDataSource
import com.sar.steamaccountswitcher.steam.data.local.storage.WebAPIAddressStorage
import com.sar.steamaccountswitcher.steam.data.remote.service.SteamAccountSwitcherAPIFactory
import com.sar.steamaccountswitcher.steam.data.remote.service.SteamAccountSwitcherServiceImpl
import com.sar.steamaccountswitcher.steam.domain.service.SteamAccountSwitcherService
import com.sar.steamaccountswitcher.steam.domain.useCase.GetAccountsWithDetailsUseCase
import com.sar.steamaccountswitcher.steam.domain.useCase.SwitchAccountUseCase
import com.sar.steamaccountswitcher.steam.ui.switching.viewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {
    single { SharedPreferencesDataSource(PreferenceManager.getDefaultSharedPreferences(get())) }
    single {
        val key = androidContext().getString(R.string.sas_webapi_address_preference_key)
        WebAPIAddressStorage(key, get())
    }
    single<SteamAccountSwitcherService> {
        val clientFactory = SteamAccountSwitcherAPIFactory(get())
        SteamAccountSwitcherServiceImpl(clientFactory)
    }
    single { SwitchAccountUseCase(get()) }
    single { GetAccountsWithDetailsUseCase(get()) }
}

private val domainModule = module {

}

private val presentationModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val steamModule = dataModule + domainModule + presentationModule
