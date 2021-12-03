package com.example.s205865lykkehjullet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val playButton: Button = findViewById(R.id.Start_game)

        playButton.setOnClickListener {
            val intent = Intent(this, Start::class.java)
            startActivity(intent)
        }*/


        /*private fun replaceFragment(fragment: Fragment) {

            if (fragment != null) {

                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)
                    .commit()

            }
        }*/

    }
}