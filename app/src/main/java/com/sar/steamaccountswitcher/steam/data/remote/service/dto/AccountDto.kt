package com.sar.steamaccountswitcher.steam.data.remote.service.dto

import android.net.Uri
import com.sar.steamaccountswitcher.steam.domain.model.Account

data class AccountDto(
    val id: String,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val profileUrl: String,
    val isVacBanned: Boolean,
    val isCommunityBanned: Boolean,
    val isLoginValid: Boolean,
    val lastLogin: String,
    val level: Long
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
