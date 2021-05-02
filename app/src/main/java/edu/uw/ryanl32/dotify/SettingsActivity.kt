package edu.uw.ryanl32.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ericchee.songdataprovider.Song
import edu.uw.ryanl32.dotify.databinding.ActivityPlayerBinding
import edu.uw.ryanl32.dotify.databinding.ActivitySettingsBinding
//
//private const val SONG_TITLE_KEY = "song_title"
//private const val SONG_ARTIST_KEY = "song_artist"
//private const val SONG_IMAGE_KEY = "song_image"
private const val SONG_KEY = "song_key"
private const val PLAYS_KEY = "plays_key"

fun launchSettingsActivity(context: Context, song: Song?, plays: Int) {
    with(context) {
        val intent = Intent(context, SettingsActivity::class.java).apply {
            val bundle = Bundle().apply {
                putInt(PLAYS_KEY, plays)
                putParcelable(SONG_KEY, song)
            }
            putExtras(bundle)
        }
        startActivity(intent)
    }
}

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val navController: NavController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {

            navController.setGraph(R.navigation.nav_graph, intent.extras)
//            val song = intent.extras?.getParcelable<Song>(SONG_KEY)
//            val plays = intent.extras?.getInt(PLAYS_KEY)
//            if (song != null) {
//                Toast.makeText(this@SettingsActivity, "$song \n PLAYS $plays", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}