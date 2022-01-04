package com.example.hangedman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.media.MediaPlayer

class gameActivity : AppCompatActivity() {
    lateinit var stateImage : ImageView
    lateinit var wordHint : TextView
    lateinit var inputText : EditText
    lateinit var checkButton : Button
    lateinit var resetButton : Button
    lateinit var healthDisplay: TextView
    lateinit var usedLetters : TextView
    var bruhSound : MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        stateImage = findViewById(R.id.healthStateImage)
        wordHint = findViewById(R.id.wordHint)
        inputText = findViewById(R.id.letterInput)
        checkButton = findViewById(R.id.letterCheckButton)
        resetButton = findViewById(R.id.resetGameButton)
        healthDisplay = findViewById(R.id.healthDisplay)
        usedLetters = findViewById(R.id.usedLetters)
        var wordToGuess = intent?.extras?.getString("secretWord")
        var health = 5
        var allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        healthDisplay.text = health.toString()
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
            if (chosenLetter.lowercase() in usedLetters.text.toString()){
                Toast.makeText(this, "Letter already used !!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(chosenLetter !in allowedChars){Toast.makeText(this, "Letter already used !!", Toast.LENGTH_SHORT).show()
            return@setOnClickListener}
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
                    bruhSound = MediaPlayer.create(this, R.raw.yeyyyy)
                    bruhSound!!.start()
                    checkButton.isEnabled = false
                    inputText.isEnabled = false
                    stateImage.setImageResource(R.drawable.bruh)
                }
                usedLetters.text = usedLetters.text.toString()+chosenLetter.lowercase()
                return@setOnClickListener
            }
            else{
                health -= 1
                if (health == 0){healthDisplay.text = "BOI DEAD AS HELL XDDD"}
                else{healthDisplay.text = health.toString()}
                usedLetters.text = usedLetters.text.toString()+chosenLetter.lowercase()
            }
            when(health){
                5 -> stateImage.setImageResource(R.drawable.healthstate5)
                4 -> {stateImage.setImageResource(R.drawable.healthstate4)
                    bruhSound = MediaPlayer.create(this, R.raw.vineboom)
                    bruhSound!!.start()}
                3 -> {stateImage.setImageResource(R.drawable.healthstate3)
                    bruhSound = MediaPlayer.create(this, R.raw.vineboom)
                    bruhSound!!.start()}
                2 -> {stateImage.setImageResource(R.drawable.healthstate2)
                    bruhSound = MediaPlayer.create(this, R.raw.vineboom)
                    bruhSound!!.start()}
                1 -> {stateImage.setImageResource(R.drawable.healthstate1)
                    bruhSound = MediaPlayer.create(this, R.raw.vineboom)
                    bruhSound!!.start()}
                0 -> {stateImage.setImageResource(R.drawable.healthstate0)
                        bruhSound = MediaPlayer.create(this, R.raw.darksouled)
                        bruhSound!!.start()
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

    