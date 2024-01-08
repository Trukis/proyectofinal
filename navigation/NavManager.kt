package com.rickamr.proyectofinal.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickamr.proyectofinal.viewModels.LoginViewModel
import com.rickamr.proyectofinal.views.login.BlankView
import com.rickamr.proyectofinal.views.login.TabsView
import com.rickamr.proyectofinal.views.tareas.TareasView

@Composable
fun NavManager(loginVM: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Blank"){
        composable("Blank"){
            BlankView(navController = navController)
        }
        composable("Login"){
            TabsView(navController, loginVM)
        }
        composable("Tareas"){
            TareasView(navController)
        }
    }
}