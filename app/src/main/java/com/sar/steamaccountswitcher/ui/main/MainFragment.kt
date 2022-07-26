package com.sar.steamaccountswitcher.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sar.steamaccountswitcher.databinding.MainFragmentBinding
import com.sar.steamaccountswitcher.ui.adapter.AccountAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val binding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = this@MainFragment.viewModel
            lifecycleOwner = this@MainFragment
            adapter = AccountAdapter(this@MainFragment.viewModel)
        }
        viewModel.profileUri.observe(viewLifecycleOwner) {
            val intent = Intent(Intent.ACTION_VIEW, it)
            startActivity(intent)
        }

        return binding.root
    }
}