package com.example.lembretedeeventos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lembretedeeventos.data.TipoEvento

@Dao
interface TipoEventoDao {

    @Insert
    suspend fun inserir(tipoEvento: TipoEvento)

    @Update
    suspend fun atualizar(tipoEvento: TipoEvento)

    @Query("SELECT * FROM tipos_evento")
    suspend fun listarTodos(): List<TipoEvento>

    @Query("DELETE FROM tipos_evento WHERE id = :id")
    suspend fun deletar(id: Int)
}