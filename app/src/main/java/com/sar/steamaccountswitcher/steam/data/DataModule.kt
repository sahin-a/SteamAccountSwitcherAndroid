package com.sar.steamaccountswitcher.steam.data

import com.sar.steamaccountswitcher.steam.data.remote.service.SteamAccountSwitcherAPI
import com.sar.steamaccountswitcher.steam.data.remote.service.SteamAccountSwitcherServiceImpl
import com.sar.steamaccountswitcher.steam.domain.repository.SteamAccountSwitcherService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<SteamAccountSwitcherService> {
        val gsonConverterFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://localhost:9080")
            .addConverterFactory(gsonConverterFactory)
            .build()
        val apiClient =
            retrofit.create(SteamAccountSwitcherAPI::class.java)

        SteamAccountSwitcherServiceImpl(apiClient)
    }
}
