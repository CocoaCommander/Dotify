package edu.uw.ryanl32.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.ryanl32.dotify.databinding.ActivitySongListBinding

private const val VISIBILITY_KEY = "visibility_key"
private const val SELECTED_SONG_KEY = "selected_song_key"

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

            if(savedInstanceState != null) {
                clMiniPlayer.visibility = savedInstanceState.getInt(VISIBILITY_KEY, View.INVISIBLE)
                if (savedInstanceState.getParcelable<Song>(SELECTED_SONG_KEY) != null) {
                    currSong = savedInstanceState.getParcelable(SELECTED_SONG_KEY)!!
                }
            }

            adapter.onSongClickListener = { _, song ->
                currSong = song
                var title = "${song.title} - ${song.artist}"
                if (title.length > 25) {
                    title = title.removeRange(25, title.length) + " . . ."
                }
                tvMiniPlayerSongTitle.text = title
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

    override fun onSaveInstanceState(outState: Bundle) {
        with(binding) {
            outState.putInt(VISIBILITY_KEY, clMiniPlayer.visibility)
            outState.putParcelable(SELECTED_SONG_KEY, currSong)
        }
        super.onSaveInstanceState(outState)
    }
}