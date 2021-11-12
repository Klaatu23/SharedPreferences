package com.example.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declaración de los widgets
        val namePerson = findViewById<EditText>(R.id.name)
        val botonPerson = findViewById<Button>(R.id.boton1)
        val botonPerson2 = findViewById<Button>(R.id.boton2)
        val txto = findViewById<TextView>(R.id.txt1)
        //SharedPreferences
        val preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE)
        namePerson.setText(preferencias.getString("nombre", ""))

        botonPerson.setOnClickListener {
            val editor = preferencias.edit()
            editor.putString("nombre", namePerson.text.toString())
            editor.putInt("edad", 43)
            editor.commit()
            Toast.makeText(this, namePerson.text.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Datos grabados", Toast.LENGTH_LONG).show()
            namePerson.setText("Nombre")
        }

        botonPerson2.setOnClickListener {

            val datos = preferencias.getString("nombre", "")
            val edad = preferencias.getInt("edad", 0)
            if (datos != null) {
                if (datos == namePerson.text.toString()) {
                    if (datos.isNotEmpty()) {
                        txto.setText("Hay datos: ${datos}-${edad}")
                    } else {
                        txto.setText("No hay datos")
                    }
                } else {
                    txto.setText("No hay persona registrada")
                }

            }
        }
    }
}