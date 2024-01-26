package br.com.controlecombustiveis

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button

class Failure : Activity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.failed_send_data)

        val finalButton = findViewById<Button>(R.id.final2Button)

        finalButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}