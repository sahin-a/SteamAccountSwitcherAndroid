package com.sar.steamaccountswitcher.steam.domain.useCase

import com.sar.steamaccountswitcher.steam.domain.model.Account
import com.sar.steamaccountswitcher.steam.domain.service.SteamAccountSwitcherService

class GetAccountsWithDetailsUseCase(private val steamService: SteamAccountSwitcherService) {

    suspend fun execute(): List<Account> = steamService.getAccounts()
}
