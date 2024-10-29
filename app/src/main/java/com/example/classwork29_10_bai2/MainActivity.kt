package com.example.classwork29_10_bai2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNumber: EditText = findViewById(R.id.inputNumber)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val radioButton1: RadioButton = findViewById(R.id.radioButton1)
        val radioButton2: RadioButton = findViewById(R.id.radioButton2)
        val radioButton3: RadioButton = findViewById(R.id.radioButton3)
        val buttonShow: Button = findViewById(R.id.buttonShow)
        val textViewError: TextView = findViewById(R.id.textViewError)
        val listResult: ListView = findViewById(R.id.listResult)

        buttonShow.setOnClickListener {
            textViewError.visibility = View.GONE
            listResult.visibility = View.GONE
            val selectedId = radioGroup.checkedRadioButtonId // Lấy ID của RadioButton được chọn

            val inputText = inputNumber.text.toString()
            if (inputText.isEmpty()) {
                textViewError.text = "Chưa có N"
                textViewError.visibility = View.VISIBLE
            } else {
                val n = inputText.toIntOrNull() // Sử dụng toIntOrNull để tránh lỗi
                if (n != null) {
                    if (selectedId != -1) { // -1 có nghĩa là không có RadioButton nào được chọn
                        val selectedRadioButton: RadioButton = findViewById(selectedId)
                        val selectedText = selectedRadioButton.text.toString()

                        val numbers = when (selectedText) {
                            "Chẵn" -> getEvenNumbers(n)
                            "Lẻ" -> getOddNumbers(n)
                            else -> getSquareNumbers(n)
                        }

                        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
                        listResult.adapter = adapter
                        listResult.visibility = View.VISIBLE
                    } else {
                        textViewError.text = "Bạn chưa chọn phương thức hiển thị"
                        textViewError.visibility = View.VISIBLE
                    }
                } else {
                    textViewError.text = "N không hợp lệ"
                    textViewError.visibility = View.VISIBLE
                }
            }
        }
    }
    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}