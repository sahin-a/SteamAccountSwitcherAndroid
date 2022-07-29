package com.sar.steamaccountswitcher.steam.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sar.steamaccountswitcher.R
import com.sar.steamaccountswitcher.databinding.MainFragmentBinding
import com.sar.steamaccountswitcher.steam.ui.adapter.AccountAdapter
import com.sar.steamaccountswitcher.steam.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
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

    private fun navigateToSettings() {
        findNavController().navigate(R.id.action_global_settingsFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_settings -> {
                navigateToSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
