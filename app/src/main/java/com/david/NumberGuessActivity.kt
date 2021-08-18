package com.david

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlin.random.Random

class NumberGuessActivity : AppCompatActivity() {

    var randomNumber: Int = 0
    var attempts: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)
        attempts = 0
        randomNumber = Random.nextInt(0, 1000)
    }

    fun guess(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val congrats = findViewById<TextView>(R.id.congrats)
        try {
            val enteredNumber = editText.text.toString().toInt()

            attempts++
            congrats.isVisible = true
            congrats.text = "Intentos: $attempts"

            if (enteredNumber > randomNumber) {
                Toast.makeText(this,"El número aleatorio es menor", Toast.LENGTH_LONG).show()
            } else if (enteredNumber < randomNumber) {
                Toast.makeText(this,"El número aleatorio es mayor", Toast.LENGTH_LONG).show()
            } else {
                congrats.text = "Felicitaciones, has adivinado el número $randomNumber usando $attempts intentos"
                editText.isEnabled = false
                view.isEnabled = false
                val reset = findViewById<Button>(R.id.resest)
                reset.isVisible = true

                reset.setOnClickListener { _->
                    reset.isVisible = false
                    congrats.isVisible = false
                    editText.isEnabled = true
                    editText.setText("")
                    view.isEnabled = true
                    attempts = 0
                    randomNumber = Random.nextInt(0, 1000)
                }
            }
        } catch(e: Exception){
            Toast.makeText(this,"Ingresa un número", Toast.LENGTH_LONG).show()
        }
    }
}