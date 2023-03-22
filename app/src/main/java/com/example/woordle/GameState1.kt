package com.example.woordle

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class GameState1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamestate_main)

        var ErrorCount = 0;
        var Correct = 0;

        val WordGen = WordGenerator();
        val IntIDs = IntArray(9)

        for (i in 1..9) {
            val button = findViewById<Button>(resources.getIdentifier("Block$i", "id", packageName))
            IntIDs[i - 1] = button.id
        }

        val arrayOfButtons = Array<Button>(9) { i -> findViewById(resources.getIdentifier("Block${i+1}", "id", packageName)) }

        WordGen.PopulateButtons(IntIDs,this);

        for (button in arrayOfButtons) {
            button.setOnClickListener {
                // Your code here


                    if(ErrorCount > 6)
                    {

                        Toast.makeText(this, "You've Lost. Resetting game!", Toast.LENGTH_SHORT).show()
                        ErrorCount = 0;
                        Correct = 0;
                        val intent = Intent(this,GameState1::class.java)
                        startActivity(intent)

                    }
                else
                    {
                        if(Correct == 3)
                        {
                            Toast.makeText(this, "You guessed the word, congragts!", Toast.LENGTH_SHORT).show()
                            ErrorCount = 0;
                            Correct = 0;
                            val intent = Intent(this,GameState1::class.java)
                            startActivity(intent)
                        }

                        else {


                            if (WordGen.CheckLetter(button.text.single()) > -1) {
                                val textView = findViewById<TextView>(R.id.tvAnswer)
                                textView.text = WordGen.GetEmpty().joinToString()
                                button.setBackgroundColor(Color.parseColor("#00FF00"))
                                Correct += 1
                                button.isClickable = false
                                if(Correct == 3)
                                {
                                    val word = WordGen.GetEmpty().joinToString()
                                    Toast.makeText(this, "You guessed the word: $word !", Toast.LENGTH_SHORT).show()
                                    ErrorCount = 0;
                                    Correct = 0;

                                    val intent = Intent(this,GameState1::class.java)
                                    startActivity(intent)
                                }


                            } else {
                                ErrorCount += 1;
                                button.isClickable = false
                                button.setBackgroundColor(Color.parseColor("#FF0000"))
                                if(ErrorCount == 6)
                                {
                                    Toast.makeText(this, "You've Lost. Resetting game!", Toast.LENGTH_SHORT).show()

                                    ErrorCount = 0;
                                    Correct = 0;
                                    val intent = Intent(this,GameState1::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                    }

            }
        }

    }
}