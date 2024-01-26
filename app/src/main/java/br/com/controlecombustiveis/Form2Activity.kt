package br.com.controlecombustiveis

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

class Form2Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms2)

        // Extrair os dados da parte 1
        val formDataPart1 = intent.getSerializableExtra("formDataPart1") as HashMap<String, Serializable>

        /* Atribuições de variáveis */
        val inputRPMMCP = findViewById<EditText>(R.id.inputRPMMCP)
        val inputTempMCA = findViewById<EditText>(R.id.inputTempMCA)
        val inputOleoMCA = findViewById<EditText>(R.id.inputOleoMCA)
        val inputRPMMCA = findViewById<EditText>(R.id.inputRPMMCA)

        val sendButton = findViewById<Button>(R.id.sendButton)
        sendButton.setOnClickListener {
            val RPMMCP = inputRPMMCP.text.toString().toDoubleOrNull() ?: 0.0
            val TempMCA = inputTempMCA.text.toString().toDoubleOrNull() ?: 0.0
            val OleoMCA = inputOleoMCA.text.toString().toDoubleOrNull() ?: 0.0
            val RPMMCA = inputRPMMCA.text.toString().toDoubleOrNull() ?: 0.0

            val formDataPart2 = hashMapOf(
                "RPM_MCP" to RPMMCP,
                "Temperatura_MCA" to TempMCA,
                "Pressao_Oleo_MCA" to OleoMCA,
                "RPM_MCA" to RPMMCA
            )

            val formDataCompleto = HashMap<String, Any>().apply {
                putAll(formDataPart1.mapValues { it.value.toString() })
                putAll(formDataPart2)
            }

            val db = FirebaseFirestore.getInstance()
            db.collection("Forms")
                .add(formDataCompleto)
                .addOnSuccessListener {
                    val intent = Intent(this, Success::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    val intent = Intent(this, Failure::class.java)
                    startActivity(intent)
                }
        }
    }
}
