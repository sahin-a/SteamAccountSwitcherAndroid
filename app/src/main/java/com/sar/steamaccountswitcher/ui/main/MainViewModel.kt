package com.sar.steamaccountswitcher.ui.main

import androidx.lifecycle.ViewModel
import com.sar.steamaccountswitcher.steam.service.SteamAccountSwitcherService

class MainViewModel : ViewModel() {
    private val steamService: SteamAccountSwitcherService = SteamAccountSwitcherService()
}