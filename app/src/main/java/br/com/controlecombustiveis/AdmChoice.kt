package br.com.controlecombustiveis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class AdmChoice : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_admin)

        val buttonQuery = findViewById<Button>(R.id.toQuery)
        val buttonForms = findViewById<Button>(R.id.toForms)

        buttonQuery.setOnClickListener {
            val intent = Intent(this, AdmQuery::class.java)
            startActivity(intent)
        }
        buttonForms.setOnClickListener{
            val intent = Intent(this, Form1Activity::class.java)
            startActivity(intent)
        }
    }
}