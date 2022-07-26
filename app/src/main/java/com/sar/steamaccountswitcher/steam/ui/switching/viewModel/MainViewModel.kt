package com.sar.steamaccountswitcher.steam.ui.switching.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sar.steamaccountswitcher.steam.domain.model.Account
import com.sar.steamaccountswitcher.steam.domain.service.SteamAccountSwitcherService
import com.sar.steamaccountswitcher.steam.domain.useCase.GetAccountsWithDetailsUseCase
import com.sar.steamaccountswitcher.steam.domain.useCase.SwitchAccountUseCase
import com.sar.steamaccountswitcher.steam.ui.switching.listener.AccountListener
import kotlinx.coroutines.launch

class MainViewModel(
    private val switchAccountUseCase: SwitchAccountUseCase,
    private val getAccountsWithDetailsUseCase: GetAccountsWithDetailsUseCase
) : ViewModel(), AccountListener, SwipeRefreshLayout.OnRefreshListener {
    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    private val _profileUri = MutableLiveData<Uri>()
    val profileUri: LiveData<Uri> = _profileUri

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadAccounts()
    }

    private fun toggleLoadingIndicator(show: Boolean) {
        _isLoading.value = show
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            toggleLoadingIndicator(true)
            getAccountsWithDetailsUseCase.execute().let {
                _accounts.value = it
            }
            toggleLoadingIndicator(false)
        }
    }

    override fun onAccountSelected(account: Account) {
        viewModelScope.launch {
            switchAccountUseCase.execute(account.accountName)
        }
    }

    override fun onAvatarClicked(profileUri: Uri) {
        _profileUri.value = profileUri
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        loadAccounts()
    }
}
