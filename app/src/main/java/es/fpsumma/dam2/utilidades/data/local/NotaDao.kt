package es.fpsumma.dam2.utilidades.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import es.fpsumma.dam2.utilidades.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {

    //Inserta una nota en la tabla
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNota(nota: Nota)

    //Actualiza una nota ya creada
    @Update
    suspend fun updateNota(nota: Nota)

    //Borra una nota
    @Delete
    suspend fun deleteNota (nota: Nota)

    //Muestra la nota de ese id
    @Query("SELECT * from notas WHERE id = :id")
    fun getNota(id: Int): Flow<Nota>

}