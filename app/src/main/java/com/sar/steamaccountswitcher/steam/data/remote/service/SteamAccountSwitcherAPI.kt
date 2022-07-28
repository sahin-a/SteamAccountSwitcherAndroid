package com.sar.steamaccountswitcher.steam.data.remote.service

import android.net.Uri
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.AccountDto
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.SwitcherDto
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.toAccounts
import com.sar.steamaccountswitcher.steam.domain.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private interface SteamAccountSwitcherAPI {
    @GET("api/Account")
    suspend fun getAccounts(): List<AccountDto>

    @POST("api/Switcher")
    suspend fun login(dto: SwitcherDto)
}

class SteamAccountSwitcherService {
    private val apiClient: SteamAccountSwitcherAPI by lazy { createApiClient() }

    private fun createApiClient(): SteamAccountSwitcherAPI {
        val gsonConverterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://localhost:9080")
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(SteamAccountSwitcherAPI::class.java)
    }

    suspend fun getAccounts(): List<Account> = withContext(Dispatchers.IO) {
        try {
            return@withContext apiClient.getAccounts().toAccounts()
        } catch (e: Exception) {

        }

        return@withContext fallbackDemoData
    }

    private val fallbackDemoData = listOf(
        Account(
            username = "Sahin der Boss",
            accountName = "sahina",
            level = 150,
            isBanned = false,
            Uri.parse("https://steamavatar.io/img/1477351919tzqFl.jpg"),
            Uri.parse("https://steamcommunity.com/id/gabelogannewell")
        ),
        Account(
            username = "Elias",
            accountName = "botelias",
            level = 5000,
            isBanned = true,
            Uri.parse("https://steamavatar.io/img/1477351913X9n5u.jpg"),
            Uri.parse("https://steamcommunity.com/id/gabelogannewell")
        )
    )

    suspend fun login(accountName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            apiClient.login(SwitcherDto(accountName))
            return@withContext true
        } catch (e: Exception) {

        }

        return@withContext false
    }
}
