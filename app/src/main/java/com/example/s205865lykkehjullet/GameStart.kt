package com.example.s205865lykkehjullet;

 /** most of the code for the Gamestart fragment have been taken from from the hangman example in this yt video
 I have tried to edit it so it was more in the style for the fortune wheel game we want but sadly
 had some problems hence the reason to its so resembling that.
 "link" for yt video here: https://www.youtube.com/watch?v=kGGpH7ypxAU
 but for some reason It didn't seem to work when I tried to copy the kotlin code to this class and run the app, it just keeps stopping
 the code in this class together with it in StateOfGame, RuleManager, the Wordlist and line 15-20 in the MainActivity code class has been copied from the video in attempt to rework it
  so that it matched the requirement for this app's functionalities but hence it keeps crashing idk if its a bug in the attempt to chance some parts of this code.
  */

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children

class GameStart : AppCompatActivity() {
 private val gameManager = RuleManager()

 private lateinit var wordTextView: TextView
 private lateinit var lettersUsedTextView: TextView
 private lateinit var gameLostTextView: TextView
 private lateinit var gameWonTextView: TextView
 private lateinit var newGameButton: Button
 private lateinit var lettersLayout: ConstraintLayout

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.fragment_gamestart)
  wordTextView = findViewById(R.id.wordTextView)
  lettersUsedTextView = findViewById(R.id.lettersUsedTextView)
  /*gameLostTextView = findViewById(R.id.gameLostTextView)
  gameWonTextView = findViewById(R.id.gameWonTextView)*/
  newGameButton = findViewById(R.id.newGameButton)
  lettersLayout = findViewById(R.id.lettersLayout)
  newGameButton.setOnClickListener {
   startNewGame()
  }
  val gameState = gameManager.startNewGame()
  updateUI(gameState)

  lettersLayout.children.forEach { letterView ->
   if (letterView is TextView) {
    letterView.setOnClickListener {
     val gameState = gameManager.play((letterView).text[0])
     updateUI(gameState)
     letterView.visibility = View.GONE
    }
   }
  }
 }

 @SuppressLint("SetTextI18n")
 private fun updateUI(gameState: StateOfGame) {
  when (gameState) {
   is StateOfGame.Lost -> showGameLost(gameState.wordToGuess)
   is StateOfGame.Running -> {
    wordTextView.text = gameState.underscoreWord
    lettersUsedTextView.text = "Letters used: ${gameState.UsedLetters}"
   }
   is StateOfGame.Won -> showGameWon(gameState.wordToGuess)
  }
 }

 private fun showGameLost(wordToGuess: String) {
  wordTextView.text = wordToGuess
  gameLostTextView.visibility = View.VISIBLE
  lettersLayout.visibility = View.GONE
 }

 private fun showGameWon(wordToGuess: String) {
  wordTextView.text = wordToGuess
  gameWonTextView.visibility = View.VISIBLE
  lettersLayout.visibility = View.GONE
 }

 private fun startNewGame() {
  gameLostTextView.visibility = View.GONE
  gameWonTextView.visibility = View.GONE
  val gameState = gameManager.startNewGame()
  lettersLayout.visibility = View.VISIBLE
  lettersLayout.children.forEach { letterView ->
   letterView.visibility = View.VISIBLE
  }
  updateUI(gameState)
 }

}
