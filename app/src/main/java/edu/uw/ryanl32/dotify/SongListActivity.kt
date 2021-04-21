package edu.uw.ryanl32.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.ryanl32.dotify.databinding.ActivitySongListBinding
class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding
    private lateinit var currSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        setTitle("All Songs")

        with(binding) {
            val allSongs = SongDataProvider.getAllSongs()

            val adapter = SongListAdapter(allSongs)

            rvSongList.adapter = adapter

            adapter.onSongClickListener = { _, song ->
                currSong = song
                tvMiniPlayerSongTitle.text = "${song.title} - ${song.artist}"
                clMiniPlayer.visibility = View.VISIBLE
            }

            btnShuffle.setOnClickListener {
                adapter.updateSongs(allSongs.toMutableList().shuffled())
            }

            clMiniPlayer.setOnClickListener {
                startPlayerActivity(this@SongListActivity, currSong)
            }
        }
    }
}