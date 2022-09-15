package com.ezedev.generarqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ezedev.generarqr.R
import com.ezedev.generarqr.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.txtViewRegistrarUsuario.setOnClickListener {
            val intentRegistro = Intent(this, RegistrationActivity::class.java)
            startActivity(intentRegistro)
        }



        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            signIn(
                binding.eTxtCorreoLogin.text.toString(),
                binding.eTxtPasswordLogin.text.toString()
            )
        }

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        checkUserAlreadyLoggedIn(currentUser)

    }

    private fun signIn(correo_electronico: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(correo_electronico, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Sesion iniciada", Toast.LENGTH_SHORT).show()
                    infoUser()
                } else {

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        // [END sign_in_with_email]
    }

    private fun infoUser() {
        val infoUserIntent = Intent(this, UserInfoActivity::class.java)
        startActivity(infoUserIntent)
        this.finish()
    }

    private fun checkUserAlreadyLoggedIn(currentUser: FirebaseUser?) {
        if (currentUser != null) {

        } else {

        }
    }


}