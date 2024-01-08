package com.rickamr.proyectofinal

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity  
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rickamr.proyectofinal.navigation.NavManager
import com.rickamr.proyectofinal.ui.theme.ProyectoFinalTheme
import com.rickamr.proyectofinal.viewModels.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginVM : LoginViewModel by viewModels()

        setContent {
            ProyectoFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(loginVM)
                }
            }
        }
    }
}
