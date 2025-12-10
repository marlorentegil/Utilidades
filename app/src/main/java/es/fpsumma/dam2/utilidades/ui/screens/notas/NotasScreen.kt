package es.fpsumma.dam2.utilidades.ui.screens.notas

import android.R.attr.priority
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var nota by rememberSaveable { mutableStateOf(0.0) } //Hay que poner 0.0 para que no sea un Int



    //Controlamos el campo asignatura (no puede estar vacío, min 2 caracteres y max 50)
    val error: String? by remember(asignatura) {
        derivedStateOf {
            if (asignatura.trim().isEmpty()) {
                "El campo no puede estar vacío";
            }else if (asignatura.trim().length < 2) {
                "La asignatura no puede tener menos de 2 caracteres";
            }else if (asignatura.trim().length > 50) {
                "La asignatura no puede tener mas de 50 caracteres";
            }
            else null
        }
    }

    fun submit() {
        if (error == null) {
            priority
        }
    }

    fun handleAddNota(){
        vm.addNota(asignatura, trimestre, nota.toDouble())
        asignatura=""
        trimestre=""
        nota=0.0
    }

    fun handleDeleteNota(nota: Nota){
        vm.deleteNota(nota)
    }

    Scaffold (


    ){ padding ->

        Column(
            modifier = Modifier.fillMaxWidth().padding(padding).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){



            OutlinedTextField(
                value = asignatura,
                onValueChange = { asignatura = it },
                label = { Text("Asignatura") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                isError = error != null,   //Marca el campo como erróneo
                supportingText = {
                    if (error != null) {
                        Text(
                            text = error!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(Modifier.height(8.dp))

            //Seleccion de trimestre
            Text("Elige un trimestre: ")

            Row (modifier = Modifier.padding(top = 8.dp)){
                FilterChip(
                    selected = trimestre == "1er trimestre",
                    onClick = {trimestre = "1er trimestre"},
                    label = {Text("1er trimestre")}
                )

                FilterChip(
                    selected = trimestre == "2er trimestre",
                    onClick = {trimestre = "2er trimestre"},
                    label = {Text("2er trimestre")}
                )

                FilterChip(
                    selected = trimestre == "3er trimestre",
                    onClick = {trimestre = "3er trimestre"},
                    label = {Text("3er trimestre")}
                )
            }

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = nota.toString(),
                onValueChange = { nota = it.toDouble() },
                label = { Text("Nota") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))
            Button(
                onClick = ::handleAddNota,
                modifier = Modifier.fillMaxWidth(),
                enabled = error === null && asignatura.trim().isNotBlank()
            ) {
                Text("Añadir nota")
            }

            HorizontalDivider(modifier.padding(vertical = 16.dp))

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                items(
                    items = notas,
                    key = { it.id }
                ) { nota ->
                    Card (
                        modifier = modifier,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        ListItem(
                            headlineContent = { Text(nota.asignatura) },
                            supportingContent = { Text(nota.trimestre + ": " + nota.nota) },
                            trailingContent = {
                                IconButton(
                                    onClick = {handleDeleteNota(nota)},
                                    modifier = modifier.size(48.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Borrar nota"
                                    )
                                }
                            }
                        )
                    }
                }
            }



        }

    }

}