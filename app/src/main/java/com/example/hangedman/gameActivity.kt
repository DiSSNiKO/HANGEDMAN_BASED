package com.example.hangedman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class gameActivity : AppCompatActivity() {
    lateinit var stateImage : ImageView
    lateinit var wordHint : TextView
    lateinit var inputText : EditText
    lateinit var checkButton : Button
    lateinit var resetButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        stateImage = findViewById(R.id.healthStateImage)
        wordHint = findViewById(R.id.wordHint)
        inputText = findViewById(R.id.letterInput)
        checkButton = findViewById(R.id.letterCheckButton)
        resetButton = findViewById(R.id.resetGameButton)
        var wordToGuess = intent?.extras?.getString("secretWord")
        var health = 5
        var numOfChars = intent?.extras?.getInt("numofchars")
        var wordHintStr = wordHint.text.toString()
        wordHintStr = ""
        for(i in wordToGuess!!){
            if(i.toString()==" "){
                wordHintStr += " "
            }
            else{wordHintStr+="-"}
        }
        wordHint.text = wordHintStr

        checkButton.setOnClickListener{var letterCheckNumCount = 0
            for (i in inputText.text.toString()){letterCheckNumCount+=1}
            if (letterCheckNumCount > 1){
                Toast.makeText(this, "No more than 1 character", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var chosenLetter = inputText.text.toString()
            inputText.text.clear()
            if(chosenLetter.lowercase() in wordToGuess.lowercase()){
                var location = 1
                for(x in wordToGuess.lowercase()){
                    if(chosenLetter == x.toString()) {
                        wordHint.text = replace(wordHint.text.toString(), location, chosenLetter)
                    }
                    location += 1
                }
                if (wordHint.text.toString() == wordToGuess){
                    Toast.makeText(this, "you are winner !!!", Toast.LENGTH_LONG).show()
                    checkButton.isEnabled = false
                    inputText.isEnabled = false

                }
                return@setOnClickListener
            }
            else{
                health -= 1
                Toast.makeText(this, health.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun replace(str:String, location: Int, char: String): String{
        var currentlocation = 1
        var newStr = ""
        for(x in str){
            if (currentlocation == location){
                newStr+= char.toString()
            }
            else{
                newStr+=x.toString()
            }
            currentlocation+=1
        }
        return newStr
    }
}

