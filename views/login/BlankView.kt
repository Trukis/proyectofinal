package com.rickamr.proyectofinal.views.login

//Vista que nos va a servir es simplemente para decidir si vamos al login o si vamos a home
//dependiendo de nuesta sesión, es decir, el manejo de sesión.

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun BlankView(navController: NavController) {
    LaunchedEffect(Unit){
        //Revisar si hay una sesion guardada
        if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate("Tareas")
        }else{
            navController.navigate("Login")
        }
    }
}