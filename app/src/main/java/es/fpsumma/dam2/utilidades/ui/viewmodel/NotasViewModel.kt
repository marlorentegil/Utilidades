package es.fpsumma.dam2.utilidades.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import es.fpsumma.dam2.utilidades.data.local.AppDatabase
import es.fpsumma.dam2.utilidades.model.Nota
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotasViewModel (app: Application) : AndroidViewModel(app){

    // 1. Creamos la bd donde guardamos las notas ("notas.bd")
    private val db = Room.databaseBuilder(
        app, AppDatabase::class.java, "notas.bd"
    ).fallbackToDestructiveMigration(false).build()

    // 2. El dao hace las operaciones (guardar, borrar, leer, etc)
    private val dao = db.NotaDao()

    // 3. Lista las notas (cuando bd cambia, se actualiza, la UI se entera)
    val notas: StateFlow<List<Nota>> =
        dao.getAllNotas().stateIn(
            viewModelScope,                    //Se ejecuta mientas exista el ViewModel
            SharingStarted.Lazily,    //Empieza cuando alguien lo está viendo
            emptyList()            //Valor inicial (al principio no hay nada)
        )

    // 4. Añadir nota (en 2do plano para no bloquear la app)
    fun addNota(asignatura: String, trimestre: String, nota: Double) = viewModelScope.launch {
        dao.insertNota(Nota(asignatura = asignatura, trimestre = trimestre, nota = nota))
    }

    // 5. Borrar nota (en 2do plano para no bloquear la app)
    fun deleteNota(nota: Nota) = viewModelScope.launch {
        dao.deleteNota(nota)
    }

}
