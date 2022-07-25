package com.sar.steamaccountswitcher.steam.service

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

data class SwitcherDto(
    val accountName: String
)

interface SteamAccountSwitcherAPI {
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
            .baseUrl("localhost:9080")
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(SteamAccountSwitcherAPI::class.java)
    }

    suspend fun getAccounts(): List<AccountDto> = apiClient.getAccounts()

    suspend fun login(accountName: String) = apiClient.login(SwitcherDto(accountName))
}
