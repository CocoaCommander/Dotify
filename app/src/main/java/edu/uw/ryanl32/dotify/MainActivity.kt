package edu.uw.ryanl32.dotify

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var numPlaysCounter = Random.nextInt(0, 300)
    private lateinit var tvUser: TextView
    private lateinit var etUser: EditText
    private lateinit var btnChangeUser: Button
    private lateinit var tvNumPlays: TextView
    private lateinit var btnPrev: ImageButton
    private lateinit var btnPlay: ImageButton
    private lateinit var btnNext: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvUser = findViewById(R.id.tvUser)
        etUser = findViewById(R.id.etUser)
        btnChangeUser = findViewById(R.id.btnChangeUser)
        tvNumPlays = findViewById(R.id.tvNumPlays)
        btnPrev = findViewById(R.id.btnPrev)
        btnPlay = findViewById(R.id.btnPlay)
        btnNext = findViewById(R.id.btnNext)

        tvNumPlays.text = numPlaysCounter.toString() + "plays"

        btnChangeUser.setOnClickListener {
            changeUserClicked()
        }
        btnPrev.setOnClickListener {
            btnPrevClicked()
        }
        btnNext.setOnClickListener {
            btnNextClicked()
        }
        btnPlay.setOnClickListener {
            btnPlayClicked()
        }
    }

    fun changeUserClicked() {
        if (tvUser.visibility == View.VISIBLE) {
            tvUser.visibility = View.INVISIBLE
            etUser.visibility = View.VISIBLE
            btnChangeUser.text = "Apply"
        } else {
            tvUser.visibility = View.VISIBLE
            etUser.visibility = View.INVISIBLE
            btnChangeUser.text = "Change user"
            if (etUser.text.toString() == "") {
                Toast.makeText(
                    this,
                    "You need to type in a username of one or more characters.",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                tvUser.text = etUser.text
            }
        }
    }

    fun btnPrevClicked() {
        Toast.makeText(
            this,
            "Skipping to previous track.",
            Toast.LENGTH_SHORT)
            .show()
    }

    fun btnNextClicked() {
        Toast.makeText(
            this,
            "Skipping to next track.",
            Toast.LENGTH_SHORT)
            .show()
    }

    fun btnPlayClicked() {
        numPlaysCounter++
        tvNumPlays.text = numPlaysCounter.toString() + "plays"
    }
}