package com.example.hangedman

import android.content.Intent
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
                    if(chosenLetter.lowercase() == x.toString()) {
                        wordHint.text = replace(wordHint.text.toString(), location, chosenLetter)
                    }
                    location += 1
                }
                if (wordHint.text.toString().lowercase() == wordToGuess.lowercase()){
                    Toast.makeText(this, "you are winner !!!", Toast.LENGTH_LONG).show()
                    checkButton.isEnabled = false
                    inputText.isEnabled = false
                    stateImage.setImageResource(R.drawable.bruh)
                }
                return@setOnClickListener
            }
            else{
                health -= 1
                Toast.makeText(this,"Health left " + health.toString(), Toast.LENGTH_SHORT).show()
            }
            when(health){
                5 -> stateImage.setImageResource(R.drawable.healthstate5)
                4 -> stateImage.setImageResource(R.drawable.healthstate4)
                3 -> stateImage.setImageResource(R.drawable.healthstate3)
                2 -> stateImage.setImageResource(R.drawable.healthstate2)
                1 -> stateImage.setImageResource(R.drawable.healthstate1)
                0 -> {stateImage.setImageResource(R.drawable.healthstate0)
                      inputText.isEnabled = false
                       checkButton.isEnabled = false
                      wordHint.text = wordToGuess}
            }
        }
        reset(resetButton)
    }
    fun reset(view: Button){
        view.setOnClickListener {
            val intent1 = Intent(this, firstActivity::class.java)
            startActivity(intent1)
        }
    }
    fun replace(str:String, location: Int, char: String): String{
        var currentlocation = 1
        var newStr = ""
        for(x in str){
            if (currentlocation == location){
                newStr+= char.toString().lowercase()
            }
            else{
                newStr+=x.toString()
            }
            currentlocation+=1
        }
        return newStr
    }

}

