package edu.uw.ryanl32.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.ericchee.songdataprovider.Song
import edu.uw.ryanl32.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random

private const val SONG_TITLE_KEY = "song_title"
private const val SONG_ARTIST_KEY = "song_artist"
private const val SONG_IMAGE_KEY = "song_image"
private const val SONG_KEY = "song_key"

fun startPlayerActivity(context: Context, song: Song) {
    with(context) {
        val intent = Intent(context, PlayerActivity::class.java).apply {
            val bundle = Bundle().apply {
                putString(SONG_TITLE_KEY, song.title)
                putString(SONG_ARTIST_KEY, song.artist)
                putInt(SONG_IMAGE_KEY, song.largeImageID)
                putParcelable(SONG_KEY, song)
            }
            putExtras(bundle)
        }
        startActivity(intent)
    }
}

class PlayerActivity : AppCompatActivity() {

    private var numPlaysCounter = Random.nextInt(0, 300)
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            tvNumPlays.text = numPlaysCounter.toString() + "plays"

            btnPrev.setOnClickListener {
                btnPrevClicked()
            }
            btnNext.setOnClickListener {
                btnNextClicked()
            }
            btnPlay.setOnClickListener {
                tvNumPlays.text = btnPlayClicked()
            }

            val image: Int? = intent.extras?.getInt(SONG_IMAGE_KEY, 0)
            val title: String? = intent.extras?.getString(SONG_TITLE_KEY, "")
            val artist: String? = intent.extras?.getString(SONG_ARTIST_KEY, "")

            if (image != null) {
                ivAlbumCover.setImageResource(image)
            }
            tvSongTitle.text = title
            tvArtists.text = artist
        }
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
        return numPlaysCounter.toString() + "plays"
    }
}