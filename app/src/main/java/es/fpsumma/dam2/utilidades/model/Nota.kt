package es.fpsumma.dam2.utilidades.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
class Nota (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "asignatura")
    val asignatura: String,

    @ColumnInfo(name = "trimestre")
    val trimestre: String,

    @ColumnInfo(name = "nota")
    val nota: Double,

)