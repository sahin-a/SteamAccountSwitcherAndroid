package com.sar.steamaccountswitcher.steam.ui.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sar.steamaccountswitcher.steam.domain.model.Account
import com.sar.steamaccountswitcher.steam.domain.repository.SteamAccountSwitcherService
import com.sar.steamaccountswitcher.steam.ui.listener.AccountListener
import kotlinx.coroutines.launch

class MainViewModel(
    private val steamService: SteamAccountSwitcherService
) : ViewModel(), AccountListener {
    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    private val _profileUri = MutableLiveData<Uri>()
    val profileUri: LiveData<Uri> = _profileUri

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    init {
        loadAccounts()
    }

    private fun toggleProgressBar(show: Boolean) {
        _progressBar.value = show
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            toggleProgressBar(true)
            steamService.getAccounts().let {
                _accounts.value = it
            }
            toggleProgressBar(false)
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
