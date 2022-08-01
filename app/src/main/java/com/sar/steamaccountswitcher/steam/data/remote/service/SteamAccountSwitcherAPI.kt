package com.sar.steamaccountswitcher.steam.data.remote.service

import com.sar.steamaccountswitcher.steam.data.remote.service.dto.AccountDto
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.SwitcherDto
import com.sar.steamaccountswitcher.steam.data.remote.service.dto.toAccounts
import com.sar.steamaccountswitcher.steam.domain.model.Account
import com.sar.steamaccountswitcher.steam.domain.repository.SteamAccountSwitcherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SteamAccountSwitcherAPI {
    @GET("api/Account")
    suspend fun getAccounts(): List<AccountDto>

    @POST("api/Switcher")
    suspend fun login(@Body dto: SwitcherDto)
}

class SteamAccountSwitcherServiceImpl(
    private val apiClientFactory: SteamAccountSwitcherAPIFactory
) : SteamAccountSwitcherService {

    override suspend fun getAccounts(): List<Account> = withContext(Dispatchers.IO) {
        try {
            return@withContext apiClientFactory.getClient().getAccounts().toAccounts()
        } catch (e: Exception) {

        }

        return@withContext emptyList()
    }

    override suspend fun login(accountName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            apiClientFactory.getClient().login(SwitcherDto(accountName))
            return@withContext true
        } catch (e: Exception) {

        }

        return@withContext false
    }
}
