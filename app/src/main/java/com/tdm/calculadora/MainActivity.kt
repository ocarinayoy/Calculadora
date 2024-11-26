package com.tdm.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = "0" // El texto que se muestra en el display
    private var previousInput = "" // Para el historial de entradas
    private var operator = "" // Para la operación actual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Permite el modo Edge-to-Edge
        setContentView(R.layout.activity_main)

        // Referencias a los botones y display
        display = findViewById(R.id.display)

        val btnClearAll: Button = findViewById(R.id.btnClearAll)
        val btnDelete: Button = findViewById(R.id.btnDelete)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btnSubtract: Button = findViewById(R.id.btnSubtract)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btn0: Button = findViewById(R.id.btn0)
        val btnDot: Button = findViewById(R.id.btnDot)
        val btnEquals: Button = findViewById(R.id.btnEquals)

        // Asigna las acciones a los botones
        btnClearAll.setOnClickListener { clearAll() }
        btnDelete.setOnClickListener { delete() }
        btnDivide.setOnClickListener { setOperator("/") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }
        btnMultiply.setOnClickListener { setOperator("*") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btnSubtract.setOnClickListener { setOperator("-") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btnPlus.setOnClickListener { setOperator("+") }
        btn0.setOnClickListener { appendNumber("0") }
        btnDot.setOnClickListener { appendDot() }
        btnEquals.setOnClickListener { calculate() }

        // Configuración de los bordes Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gridLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Funciones para los botones

    private fun appendNumber(number: String) {
        if (currentInput == "0") {
            currentInput = number
        } else {
            currentInput += number
        }
        display.text = currentInput
    }

    private fun appendDot() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            display.text = currentInput
        }
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            previousInput = currentInput
            currentInput = "0"
            operator = op
        }
    }

    private fun calculate() {
        if (previousInput.isNotEmpty() && currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val num1 = previousInput.toDouble()
            val num2 = currentInput.toDouble()
            var result = 0.0

            when (operator) {
                "+" -> result = num1 + num2
                "-" -> result = num1 - num2
                "*" -> result = num1 * num2
                "/" -> {
                    result = if (num2 != 0.0) {
                        num1 / num2
                    } else {
                        0.0 // Manejo de división por cero
                    }
                }
            }

            currentInput = result.toString()
            display.text = currentInput
            operator = ""
            previousInput = ""
        }
    }

    private fun clearAll() {
        currentInput = "0"
        previousInput = ""
        operator = ""
        display.text = currentInput
    }

    private fun delete() {
        if (currentInput.length > 1) {
            currentInput = currentInput.substring(0, currentInput.length - 1)
        } else {
            currentInput = "0"
        }
        display.text = currentInput
    }
}
