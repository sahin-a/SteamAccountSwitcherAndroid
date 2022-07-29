package com.sar.steamaccountswitcher.steam.data.remote.service

import android.net.Uri
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.AccountDto
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.SwitcherDto
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.toAccounts
import com.sar.steamaccountswitcher.steam.domain.model.Account
import com.sar.steamaccountswitcher.steam.domain.repository.SteamAccountSwitcherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.POST

interface SteamAccountSwitcherAPI {
    @GET("api/Account")
    suspend fun getAccounts(): List<AccountDto>

    @POST("api/Switcher")
    suspend fun login(dto: SwitcherDto)
}

class SteamAccountSwitcherServiceImpl(private val apiClient: SteamAccountSwitcherAPI) :
    SteamAccountSwitcherService {

    override suspend fun getAccounts(): List<Account> = withContext(Dispatchers.IO) {
        try {
            return@withContext apiClient.getAccounts().toAccounts()
        } catch (e: Exception) {

        }

        return@withContext fallbackDemoData
    }

    override suspend fun login(accountName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            apiClient.login(SwitcherDto(accountName))
            return@withContext true
        } catch (e: Exception) {

        }

        return@withContext false
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
}
