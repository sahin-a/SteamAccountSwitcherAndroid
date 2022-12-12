package com.sar.steamaccountswitcher.steam.domain.service

import com.sar.steamaccountswitcher.steam.domain.model.Account

interface SteamAccountSwitcherService {
    suspend fun getAccounts(): List<Account>
    suspend fun login(accountName: String): Boolean
}
