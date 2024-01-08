package com.rickamr.proyectofinal.viewModels

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import androidx.compose.ui.platform.LocalContext

class LoginViewModel : ViewModel() {
    var showAlert by mutableStateOf(false)

    fun closeAlert() {
        showAlert = false
    }

    fun createUser(email: String, password: String, username: String, context: Context, function: () -> Unit){
        var auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(){
            Toast.makeText(context,"Bienvenido",Toast.LENGTH_LONG).show()
            run(function)
        }.addOnFailureListener{
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
        }
    }

    fun login(email: String, password: String, context: Context , function: () -> Unit) {
        var auth = Firebase.auth
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Toast.makeText(context,"Bienvenido",Toast.LENGTH_LONG).show()
            run(function)
        }.addOnFailureListener {
            Toast.makeText(context,"Correo o contraseña incorrecto",Toast.LENGTH_LONG).show()
        }
    }
}