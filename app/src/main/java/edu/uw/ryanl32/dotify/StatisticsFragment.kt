package edu.uw.ryanl32.dotify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.uw.ryanl32.dotify.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStatisticsBinding.inflate(layoutInflater)

        var receivedPlays = safeArgs.playsKey
        var receivedSong = safeArgs.songKey

        with(binding) {
            tvPlayCount.text = "Play Count: $receivedPlays"
            ivStatFragImage.setImageResource(receivedSong.largeImageID)
        }

        return binding.root
    }
}