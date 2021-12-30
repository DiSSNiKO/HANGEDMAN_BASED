package com.example.hangedman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.Toast

class firstActivity : AppCompatActivity() {
    lateinit var secretwordET : EditText
    lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)
        var numOfChars = 0
        secretwordET = findViewById(R.id.chosenword)
        startButton = findViewById(R.id.startGame)
        startButton.setOnClickListener{
            var secretword = secretwordET.text.toString()
            for(x in secretwordET.text.toString()){numOfChars+=1}
            if(secretwordET.text.isEmpty()||numOfChars>30){
                numOfChars = 0
                secretword = ""
                Toast.makeText(this, "Should not be empty or more less 30 characters >:(", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, gameActivity::class.java)
            intent.putExtra("secretWord", secretword)
            intent.putExtra("numofchars", numOfChars)
            startActivity(intent)
        }
    }
}


