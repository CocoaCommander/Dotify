package edu.uw.ryanl32.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.ryanl32.dotify.databinding.FragmentSettingsBinding
import edu.uw.ryanl32.dotify.databinding.FragmentStatisticsBinding

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

        //Toast.makeText(context, "$receivedPlays $receivedSong", Toast.LENGTH_SHORT).show()

        with(binding) {
            btnStatistics.setOnClickListener { navController.navigate(R.id.statisticsFragment) }
            btnAbout.setOnClickListener { navController.navigate(R.id.aboutFragment) }
            btnProfile.setOnClickListener { navController.navigate((R.id.profileFragment)) }
        }

        return binding.root
    }
}