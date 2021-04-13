package edu.uw.ryanl32.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var numPlays = 0
    private lateinit var username: EditText
    private lateinit var btnChangeUser: Button
    private lateinit var numPlaysDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}