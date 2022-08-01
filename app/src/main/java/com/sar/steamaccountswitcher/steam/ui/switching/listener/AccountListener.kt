package com.sar.steamaccountswitcher.steam.ui.switching.listener

import android.net.Uri
import com.sar.steamaccountswitcher.steam.domain.model.Account

interface AccountListener {
    fun onAccountSelected(account: Account)
    fun onAvatarClicked(profileUri: Uri)
}