package com.example.lembretedeeventos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lembretedeeventos.dao.EventoDao
import com.example.lembretedeeventos.dao.TipoEventoDao
import com.example.lembretedeeventos.data.Evento
import com.example.lembretedeeventos.data.TipoEvento

@Database(entities = [Evento::class, TipoEvento::class], version = 1)
abstract class LembreteDatabase : RoomDatabase() {
    abstract fun eventoDao(): EventoDao
    abstract fun tipoEventoDao(): TipoEventoDao
}