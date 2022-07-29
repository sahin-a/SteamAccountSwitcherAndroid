package com.sar.steamaccountswitcher.steam

import com.sar.steamaccountswitcher.steam.data.dataModule
import com.sar.steamaccountswitcher.steam.domain.domainModule
import com.sar.steamaccountswitcher.steam.ui.presentationModule

val steamModule = dataModule + domainModule + presentationModule
