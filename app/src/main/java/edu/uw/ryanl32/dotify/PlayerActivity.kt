package edu.uw.ryanl32.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.ryanl32.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random

//private const val SONG_TITLE_KEY = "song_title"
//private const val SONG_ARTIST_KEY = "song_artist"
//private const val SONG_IMAGE_KEY = "song_image"
private const val SONG_KEY = "song_key"
private const val NUM_PLAYS_KEY = "num_plays_key"

fun startPlayerActivity(context: Context, song: Song) {
    with(context) {
        val intent = Intent(context, PlayerActivity::class.java).apply {
//            val bundle = Bundle().apply {
//                putString(SONG_TITLE_KEY, song.title)
//                putString(SONG_ARTIST_KEY, song.artist)
//                putInt(SONG_IMAGE_KEY, song.largeImageID)
//                putParcelable(SONG_KEY, song)
//            }
//            putExtras(bundle)

            putExtra(SONG_KEY, song)
        }
        startActivity(intent)
    }
}

class PlayerActivity : AppCompatActivity() {

    private var numPlaysCounter = Random.nextInt(0, 300)
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            numPlaysCounter = savedInstanceState.getInt(NUM_PLAYS_KEY)
        }

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            tvNumPlays.text = "${numPlaysCounter} plays"

            btnPrev.setOnClickListener {
                btnPrevClicked()
            }
            btnNext.setOnClickListener {
                btnNextClicked()
            }
            btnPlay.setOnClickListener {
                tvNumPlays.text = btnPlayClicked()
            }

            val song = intent.extras?.getParcelable<Song>(SONG_KEY)

            val image: Int? = song?.largeImageID
            val title: String? = song?.title
            val artist: String? = song?.artist

            if (image != null) {
                ivAlbumCover.setImageResource(image)
            }
            tvSongTitle.text = title
            tvArtists.text = artist

            btnSettings.setOnClickListener {
                //val defaultSong: Song = SongDataProvider.createRandomSong()
                //val songToSend = intent.extras?.getParcelable(SONG_KEY)
                launchSettingsActivity(this@PlayerActivity, song, numPlaysCounter)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(NUM_PLAYS_KEY, numPlaysCounter)
        super.onSaveInstanceState(outState)
    }

    private fun btnPrevClicked() {
        Toast.makeText(
            this,
            "Skipping to previous track.",
            Toast.LENGTH_SHORT)
            .show()
    }

    private fun btnNextClicked() {
        Toast.makeText(
            this,
            "Skipping to next track.",
            Toast.LENGTH_SHORT)
            .show()
    }

    private fun btnPlayClicked(): String {
        numPlaysCounter++
        return "${numPlaysCounter.toString()} plays"
    }
}