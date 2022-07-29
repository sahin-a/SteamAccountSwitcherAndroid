package com.sar.steamaccountswitcher.steam.domain.repository

import com.sar.steamaccountswitcher.steam.domain.model.Account

interface SteamAccountSwitcherService {
    suspend fun getAccounts(): List<Account>
    suspend fun login(accountName: String): Boolean
}
