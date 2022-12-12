package com.sar.steamaccountswitcher.steam.domain.useCase

import com.sar.steamaccountswitcher.steam.domain.service.SteamAccountSwitcherService

class SwitchAccountUseCase(private val steamService: SteamAccountSwitcherService) {

    suspend fun execute(steamId: String) = steamService.login(steamId)
}
