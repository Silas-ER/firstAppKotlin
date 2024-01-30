package br.com.controlecombustiveis

import android.app.Activity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.widget.CheckBox
import android.content.Context

class MainActivity : Activity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Define o layout

        auth = FirebaseAuth.getInstance()

        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val rememberMeCheckBox = findViewById<CheckBox>(R.id.checkbox_remember_me)

        /*Lógica de salvar a próxima entrada*/
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPref.getString("email", null)
        val savedPassword = sharedPref.getString("password", null)

        if (savedEmail != null && savedPassword != null) {
            emailEditText.setText(savedEmail)
            passwordEditText.setText(savedPassword)
            rememberMeCheckBox.isChecked = true
        }

        /*Lógica do clique do botão*/
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val rememberMe = rememberMeCheckBox.isChecked
            val listOfAdminEmails = listOf("silasn364@gmail.com")

            /*Lógica para o remember checkbox*/
            if (rememberMe) {
                val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putString("email", email)
                    putString("password", password)
                    apply()
                }
            }

            /*Autenticar com Firebase*/
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                task -> if (task.isSuccessful) {
                    // Login bem-sucedido
                    Log.d("Login", "signInWithEmail:success")
                    val user = auth.currentUser
                    if (user != null && user.email in listOfAdminEmails) {
                        // O usuário é um administrador
                        val adminIntent = Intent(this, AdmChoice::class.java)
                        startActivity(adminIntent)
                    } else {
                        // O usuário não é um administrador
                        val userIntent = Intent(this, Form1Activity::class.java)
                        startActivity(userIntent)
                    }
                } else {
                    // Se falhar, exibir mensagem ao usuário
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Autenticação falhou.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
