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
    var randomNumber = 0
    var attempts = 0

    var editText: EditText? = null
    var congrats: TextView? = null
    var reset: Button? = null
    var guess: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)

        randomNumber = Random.nextInt(1, 1000)

        editText = findViewById<EditText>(R.id.editText)
        congrats = findViewById<TextView>(R.id.congrats)
        reset = findViewById<Button>(R.id.resest)
        guess = findViewById<Button>(R.id.guessButton)
    }

    fun guess(view: View) {
        try {
            val enteredNumber = editText?.text.toString().toInt()

            attempts++
            congrats?.isVisible = true
            congrats?.text = "Intentos: $attempts"

            when {
                enteredNumber > randomNumber -> {
                    Toast.makeText(this, "El número aleatorio es menor", Toast.LENGTH_LONG).show()
                }
                enteredNumber < randomNumber -> {
                    Toast.makeText(this, "El número aleatorio es mayor", Toast.LENGTH_LONG).show()
                }
                else -> {
                    congrats?.text = "Felicitaciones, has adivinado el número $randomNumber usando $attempts intentos"

                    editText?.isEnabled = false
                    view.isEnabled = false
                    reset?.isVisible = true
                }
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Ingresa un número", Toast.LENGTH_LONG).show()
        }
    }

    fun reset(view: View) {
        reset?.isVisible = false
        congrats?.isVisible = false
        editText?.isEnabled = true
        editText?.setText("")
        view.isEnabled = true
        attempts = 0
        randomNumber = Random.nextInt(0, 1000)
        guess?.isEnabled = true
    }
}