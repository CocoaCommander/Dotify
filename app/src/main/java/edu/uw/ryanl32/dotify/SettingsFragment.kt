package edu.uw.ryanl32.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.ryanl32.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(layoutInflater)

        val receivedPlays = safeArgs.playsKey
        val receivedSong = safeArgs.songKey

        with(binding) {
            btnStatistics.setOnClickListener { navController.navigate(
                SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment2(receivedSong, receivedPlays)
            ) }
            btnAbout.setOnClickListener { navController.navigate(R.id.aboutFragment) }
            btnProfile.setOnClickListener { navController.navigate(R.id.profileFragment) }
        }

        return binding.root
    }
}