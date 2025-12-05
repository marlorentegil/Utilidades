package es.fpsumma.dam2.utilidades.ui.screens.notas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.fpsumma.dam2.utilidades.model.Nota
import es.fpsumma.dam2.utilidades.ui.viewmodel.NotasViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotasScreen (navController: NavController, vm: NotasViewModel, modifier: Modifier= Modifier){

    val notas by vm.notas.collectAsState()

    var asignatura by rememberSaveable { mutableStateOf("") }
    var trimestre by rememberSaveable { mutableStateOf("") }
    var nota by rememberSaveable { mutableStateOf(0) }

    fun handleAddNota(){
        vm.addNota(asignatura, trimestre, nota.toDouble())
        asignatura=""
        trimestre=""
        nota=0
    }

    fun handleDeleteNota(nota: Nota){
        vm.deleteNota(nota)
    }

    Scaffold (


    ){ padding ->

        Column(
            modifier = Modifier.fillMaxWidth().padding(padding).padding(20.dp)
            
        ){

        }

    }

}