package com.sar.steamaccountswitcher.steam.service

import android.net.Uri
import com.sar.steamaccountswitcher.ui.model.Account
import com.sar.steamaccountswitcher.ui.model.toAccounts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

data class AccountDto(
    val id: String,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val profileUrl: String,
    val isVacBanned: Boolean,
    val isCommunityBanned: Boolean,
    val isLoginValid: Boolean,
    val lastLogin: String,
    val level: Long
)

private data class SwitcherDto(
    val accountName: String
)

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

        return@withContext listOf(
            Account(
                username = "Sahin der Boss",
                accountName = "sahina",
                level = 150,
                isBanned = false,
                Uri.parse("https://steamavatar.io/img/1477351919tzqFl.jpg"),
                Uri.parse("https://steamcommunity.com/id/triniox")
            ),
            Account(
                username = "Elias",
                accountName = "botelias",
                level = 5000,
                isBanned = true,
                Uri.parse("https://steamavatar.io/img/1477351913X9n5u.jpg"),
                Uri.parse("https://steamcommunity.com/id/triniox")
            )
        )
    }

    suspend fun login(accountName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            apiClient.login(SwitcherDto(accountName))
            return@withContext true
        } catch (e: Exception) {

        }

        return@withContext false
    }
}
