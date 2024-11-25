package com.example.lembretedeeventos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tipos_evento")
data class TipoEvento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val descricao: String
)