package com.ezedev.generarqr

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ezedev.generarqr.R
import com.ezedev.generarqr.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth
    lateinit var datePickerDialog: DatePickerDialog


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)



        binding.btnEnviarRegistro.setOnClickListener {
            val nombre = binding.eTxtNombreRegistro.text.toString()
            val apellido1 = binding.eTxtApellidoRegistro1.text.toString()
            val apellido2 = binding.eTxtApellidoRegistro2.text.toString()
            val codigo_terminal = binding.eTxtCodigoTerminal.text.toString()
            val codigo_empleado = binding.eTxtCodigoEmpleado.text.toString()
            val descripcion = binding.eTxtDescripcion.text.toString()
            val identificacion = binding.eTxtIdentificacion.text.toString()
            val correo_electronico = binding.eTxtCorreoRegistro.text.toString()
            val password = binding.eTxtPasswordRegistro.text.toString()

            if (nombre.isNotEmpty() && apellido1.isNotEmpty() && apellido2.isNotEmpty() && codigo_terminal.isNotEmpty() && codigo_empleado.isNotEmpty() && descripcion.isNotEmpty() && identificacion.isNotEmpty() && correo_electronico.isNotEmpty() && password.isNotEmpty()) {
                registrarUsuario(
                    nombre,
                    apellido1,
                    apellido2,
                    codigo_terminal,
                    codigo_empleado,
                    descripcion,
                    identificacion,
                    correo_electronico,
                    password
                )
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }

        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        } else {

        }
    }


    private fun registrarUsuario(
        nombre: String,
        apellido1: String,
        apellido2: String,
        codigo_terminal: String,
        codigo_empleado: String,
        descripcion: String,
        identificacion: String,
        correo_electronico: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(correo_electronico, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    val user = auth.currentUser

                    val uid = user!!.uid

                    val map = hashMapOf(
                        "nombre" to nombre,
                        "apellido1" to apellido1,
                        "apellido2" to apellido2,
                        "codigo_terminal" to codigo_terminal,
                        "codigo_empleado" to codigo_empleado,
                        "descripcion" to descripcion,
                        "identificacion" to identificacion,
                        "correo_electronico" to correo_electronico
                    )

                    val db = Firebase.firestore

                    db.collection("Usuarios").document(uid).set(map).addOnSuccessListener {
                        infoUser()
                        Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Fallo al guardar la informacion",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {


                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }


    }

    private fun infoUser() {
        val infoUserIntent = Intent(this, UserInfoActivity::class.java)
        startActivity(infoUserIntent)

    }


    private fun reload() {
    }
}