package com.example.s205865lykkehjullet

sealed class StateOfGame {
    class Running(val UsedLetters: String,
                  val underscoreWord: String) : StateOfGame()
    class Lost(val wordToGuess: String) : StateOfGame()
    class Won(val wordToGuess: String) : StateOfGame()
}
