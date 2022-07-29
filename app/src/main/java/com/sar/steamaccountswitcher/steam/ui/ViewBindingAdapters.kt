package com.sar.steamaccountswitcher.ui

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.sar.steamaccountswitcher.R
import com.sar.steamaccountswitcher.steam.domain.model.Account
import com.sar.steamaccountswitcher.steam.ui.adapter.AccountAdapter

@BindingAdapter("submitAccounts")
fun submitAccounts(recyclerView: RecyclerView, items: List<Account>?) {
    val adapter = recyclerView.adapter as AccountAdapter?
    adapter?.setItems(items)
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, avatarUri: Uri) {
    Glide.with(imageView.context)
        .load(avatarUri)
        .placeholder(R.drawable.steam_avatar_placeholder)
        .fitCenter()
        .into(imageView)
}

@BindingAdapter("setVisibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<AccountAdapter.ViewHolder>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("setOnRefreshListener")
fun setOnRefreshListener(
    refreshLayout: SwipeRefreshLayout,
    onRefreshListener: SwipeRefreshLayout.OnRefreshListener
) {
    refreshLayout.setOnRefreshListener(onRefreshListener)
}

@BindingAdapter("setRefreshingState")
fun setRefreshingState(
    refreshLayout: SwipeRefreshLayout,
    isRefreshing: Boolean
) {
    refreshLayout.isRefreshing = isRefreshing
}