package com.sar.steamaccountswitcher.ui.model

import android.net.Uri
import com.sar.steamaccountswitcher.steam.service.AccountDto

data class Account(
    val username: String,
    val accountName: String,
    val level: Long,
    val isBanned: Boolean,
    val avatar: Uri,
    val profileUri: Uri
)

fun AccountDto.toAccount() = Account(
    username = username,
    accountName = name,
    isBanned = isCommunityBanned || isVacBanned,
    level = level,
    avatar = Uri.parse(avatarUrl),
    profileUri = Uri.parse(profileUrl)
)

fun List<AccountDto>.toAccounts() = map { it.toAccount() }
