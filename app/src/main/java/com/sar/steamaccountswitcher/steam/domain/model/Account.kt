package com.sar.steamaccountswitcher.steam.domain.model

import android.net.Uri

data class Account(
    val username: String,
    val accountName: String,
    val level: Long,
    val isBanned: Boolean,
    val avatar: Uri,
    val profileUri: Uri,
    val isLevelAvailable: Boolean = level >= 0
)
