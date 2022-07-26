package com.sar.steamaccountswitcher.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sar.steamaccountswitcher.steam.service.SteamAccountSwitcherService
import com.sar.steamaccountswitcher.ui.listener.AccountListener
import com.sar.steamaccountswitcher.ui.model.Account
import kotlinx.coroutines.launch

class MainViewModel(
    private val steamService: SteamAccountSwitcherService = SteamAccountSwitcherService()
) : ViewModel(), AccountListener {
    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> get() = _accounts

    private val _profileUri = MutableLiveData<Uri>()
    val profileUri: LiveData<Uri> = _profileUri

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    init {
        loadAccounts()
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            _progressBar.value = true
            steamService.getAccounts().let {
                _accounts.value = it
            }
            _progressBar.value = false
        }
    }

    override fun onAccountSelected(account: Account) {
        viewModelScope.launch {
            steamService.login(account.accountName)
        }
    }

    override fun onAvatarClicked(profileUri: Uri) {
        _profileUri.value = profileUri
    }
}