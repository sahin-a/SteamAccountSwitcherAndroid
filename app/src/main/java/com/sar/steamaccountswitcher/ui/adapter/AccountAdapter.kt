package com.sar.steamaccountswitcher.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sar.steamaccountswitcher.BR
import com.sar.steamaccountswitcher.R
import com.sar.steamaccountswitcher.ui.listener.AccountListener
import com.sar.steamaccountswitcher.ui.model.Account

class AccountAdapter(private val listener: AccountListener) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    private var accounts: List<Account> = emptyList()

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(account: Account, listener: AccountListener) {
            binding.setVariable(BR.account, account)
            binding.setVariable(BR.accountListener, listener)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.account_item,
            viewGroup,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(accounts[position], listener)
    }

    fun setItems(items: List<Account>?) {
        accounts = items ?: emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = accounts.size
}
