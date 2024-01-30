package br.com.controlecombustiveis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class AdmQuery : Activity() {
    private lateinit var selectedBoat: String // Variável para armazenar o barco selecionado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.query_admin)

        val listOfBoat = listOf("SANTA VITORIA", "DURAN", "STEPHANIE SEIF I", "FLAVIA MONTEIRO", "JR LUCAS III", "KIYOMÃ", "WATER FISH", "JOEL SANTOS II")
        val spinnerBoat: Spinner = findViewById(R.id.filterBoat)

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfBoat)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBoat.adapter = spinnerAdapter
        spinnerBoat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedBoat = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(applicationContext, "Por favor, selecione um barco", Toast.LENGTH_LONG).show()
            }
        }

        val filterButton = findViewById<Button>(R.id.filterButton)
        val dateInit = findViewById<EditText>(R.id.editTextStartDate)
        val dateEnd = findViewById<EditText>(R.id.editTextEndDate)

        filterButton.setOnClickListener {
            // Implemente a lógica de filtragem aqui
            // Use selectedBoat, dateInit.text.toString(), e dateEnd.text.toString() para a consulta
        }
    }
}
