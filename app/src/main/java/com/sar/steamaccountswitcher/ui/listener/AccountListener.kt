package com.sar.steamaccountswitcher.ui.listener

import android.net.Uri
import com.sar.steamaccountswitcher.ui.model.Account

interface AccountListener {
    fun onAccountSelected(account: Account)
    fun onAvatarClicked(profileUri: Uri)
}