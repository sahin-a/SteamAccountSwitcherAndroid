package com.sar.steamaccountswitcher.steam.data.remote.service

import com.sar.steamaccountswitcher.steam.data.local.storage.WebAPIAddressStorage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: garbage solution, refactor this garbage
class SteamAccountSwitcherAPIFactory(private val apiAddressStorage: WebAPIAddressStorage) {
    private var _previousAddress: String = ""
    private var _instance: SteamAccountSwitcherAPI? = null

    fun getClient(): SteamAccountSwitcherAPI {
        val baseUrl = apiAddressStorage.get(defaultValue = "")

        if (_instance == null) {
            _instance = createClient(baseUrl)
        } else if (_previousAddress != baseUrl) {
            _instance = createClient(baseUrl)
        }

        return _instance!!
    }

    private fun createClient(baseUrl: String): SteamAccountSwitcherAPI {
        val gsonConverterFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(SteamAccountSwitcherAPI::class.java)
    }
}
