package com.ezedev.generarqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.ezedev.generarqr.R
import com.ezedev.generarqr.databinding.ActivityUserInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)


        auth = Firebase.auth

        val currentUser = auth.currentUser
        val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("Usuarios").document(uid).get().addOnSuccessListener {
            binding.eTxtNombreUser.text = ("Nombre: " + it.get("nombre") as String?)
            binding.eTxtApellidoUser.text = ("Apellido Paterno: " + it.get("apellido1") as String?)
            binding.eTxtApellidoUser1.text = ("Apellido Materno: " + it.get("apellido2") as String?)
            binding.eTxtCorreoUser.text = ("Correo: " + it.get("correo_electronico") as String?)
            binding.eTxtCodigoEmpleadoUser.text = ("Codigo Empleado: " + it.get("codigo_empleado") as String?)
            binding.eTxtCodigoTerminalUser.text = ("Codigo Terminal: " + it.get("codigo_terminal") as String?)
            binding.eTxtIdentificacionUser.text = ("Identificacion: " + it.get("identificacion") as String?)
            binding.eTxtDescripcionUser.text = ("Descripcion: " + it.get("descripcion") as String?)
            binding.eTxtNombreEmpleadoUser.text = ("Nombre Empleado: " + it.get("nombre") as String?+ it.get("apellido1") as String?)

        }
        binding.btnListar.setOnClickListener {
            listaUsuario()
        }
        binding.btnLogout.setOnClickListener {
            cerrarSesion()
        }

    }

    private fun listaUsuario() {
        val intent = Intent(this, ListFragment::class.java)
        startActivity(
            intent
        )
    }

    private fun cerrarSesion() {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(
            intent
        )
    }


    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        } else {

        }
    }

    private fun reload() {

    }

}


