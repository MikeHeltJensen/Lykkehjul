package com.example.s205865lykkehjullet

import kotlin.random.Random

class RuleManager {
    private var UsedLetters: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val Lives = 7
    private var currentTries = 0

    fun startNewGame(): StateOfGame {
        UsedLetters = ""
        currentTries = 0
        val randomIndex = Random.nextInt(0, WordList.words.size)
        wordToGuess = WordList.words[randomIndex]
        generateUnderscores(wordToGuess)
        return getStateOfGame()
    }

    fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append(' ')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    fun play(letter: Char): StateOfGame {
        if (UsedLetters.contains(letter)) {
            return StateOfGame.Running(UsedLetters, underscoreWord)
        }

        UsedLetters += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord // _ _ _ _ _ _ _ -> E _ _ _ _ _ _
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return getStateOfGame()
    }


    private fun getStateOfGame(): StateOfGame {
        if (underscoreWord.equals(wordToGuess, true)) {
            return StateOfGame.Won(wordToGuess)
        }

        if (currentTries == Lives) {
            return StateOfGame.Lost(wordToGuess)
        }

        return StateOfGame.Running(UsedLetters, underscoreWord)
    }
}