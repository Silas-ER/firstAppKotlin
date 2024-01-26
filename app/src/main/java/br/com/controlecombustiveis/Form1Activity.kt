package br.com.controlecombustiveis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import android.widget.AdapterView
import android.widget.ArrayAdapter
import java.util.*
import android.view.View
import java.io.Serializable

class Form1Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms1)

        /*Configurações para captura de data atual*/
        val textViewDate = findViewById<TextView>(R.id.textViewDate)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()
        textViewDate.text = dateFormat.format(date)

        /*Atribuições de variaveis de input*/
        val inputUser = findViewById<EditText>(R.id.inputUser)

        val spinner: Spinner = findViewById<Spinner>(R.id.inputBarco)
        val options = arrayOf("CAMBORI", "DONA ILVA", "IBIZA", "MARIA CLARA", "KOPESCA", "KOWALSKI V", "MARBELLA I", "KR III", "NATAL PESCA VII", "OULED SI MOHAND", "NATAL PESCA IX", "RIO JAPURA", "RIO POTENGI", "TUNASA I", "LEAL SANTOS 7", "MARLIN II", "MUCURIPE III", "NETUNO S", "TRANSMAR I", "AZTECA III", "GUADALAJARA", "STA PAULINA", "ROMULO", "FILHO DA PROMESSA")

        // Criando um ArrayAdapter usando um simples layout de spinner e a lista de opções
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val inputBarco = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(applicationContext, "Por favor, selecione um barco", Toast.LENGTH_LONG).show()
            }
        }

        val inputTempMCP = findViewById<EditText>(R.id.inputTempMCP)
        val inputOleoMCP = findViewById<EditText>(R.id.inputOleoMCP)

        /*Lógica do clique*/
        val continueButton = findViewById<Button>(R.id.continueButton)
        continueButton.setOnClickListener {
            val selectedBarco = spinner.selectedItem?.toString() ?: ""
            val usuario = inputUser.text.toString()
            val temperaturaMCP = inputTempMCP.text.toString()
            val pressaoOleoMCP = inputOleoMCP.text.toString()

            if (selectedBarco.isEmpty()) {
                Toast.makeText(applicationContext, "Por favor, selecione um barco", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val formDataPart1: HashMap<String, Serializable> = hashMapOf(
                "Data" to textViewDate.text.toString(),
                "Usuario" to usuario,
                "Barco" to selectedBarco,
                "Temperatura_MCP" to (temperaturaMCP.toDoubleOrNull() ?: 0.0),
                "Pressao_Oleo_MCP" to (pressaoOleoMCP.toDoubleOrNull() ?: 0.0)
            )

            val continueForm = Intent(this, Form2Activity::class.java).apply {
                putExtra("formDataPart1", formDataPart1)
            }
            startActivity(continueForm)
        }
    }
}