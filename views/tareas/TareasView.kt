package com.rickamr.proyectofinal.views.tareas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import org.intellij.lang.annotations.JdkConstants.FontStyle
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.LocalDateTime
import androidx.compose.foundation.lazy.LazyColumn as LazyColumn

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasView(navController: NavHostController) {
    val db = Firebase.firestore
    var tarea by remember { mutableStateOf("")}
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp)
        .background(color = Color.Cyan)) {
        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(18.dp),
            style = TextStyle(fontSize = 32.sp),
            textAlign = TextAlign.Center,
            text = "Tareas")
        OutlinedTextField(
            modifier = Modifier.padding(18.dp),
            label = {Text(text = "Tarea: ")},
            value = tarea,
            onValueChange = {tarea = it})
        Button(modifier = Modifier
            .padding(18.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                val newTarea = hashMapOf(
                    "Desc" to tarea,
                    "Fecha" to LocalDateTime.now()
                )
                db.collection("tareas").add(newTarea)
        }) {
            Text(text = "Nueva Tarea")
        }
        var lista = ListaTareas()
        LazyColumn {
            items(lista){ elemento ->
                Text(text = elemento)
            }
        }
        Button(modifier = Modifier
            .padding(18.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                Firebase.auth.signOut()
                navController.navigate("Blank")
        }) {
            Text(text = "Cerrar sesion")
        }
    }
}

@Composable
fun ListaTareas (): MutableList<String> {
    val db = Firebase.firestore
    var listaTareas = mutableListOf<String>()
    db.collection("tareas").get().addOnSuccessListener { registro ->
        for (dato in registro){
            listaTareas.add(dato.data["Desc"].toString())
        }
    }
    return listaTareas
}