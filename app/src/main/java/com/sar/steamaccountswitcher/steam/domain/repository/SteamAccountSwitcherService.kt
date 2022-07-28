package com.sar.steamaccountswitcher.steam.domain.repository

import com.sar.steamaccountswitcher.steam.domain.model.Account

interface SteamAccountSwitcherService {
    fun getAccounts(): List<Account>
    fun loginAccount(account: Account)
}