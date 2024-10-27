@Dao
interface TipoEventoDao {
    @Insert
    suspend fun insertTipoEvento(tipoEvento: TipoEvento)

    @Update
    suspend fun updateTipoEvento(tipoEvento: TipoEvento)

    @Delete
    suspend fun deleteTipoEvento(tipoEvento: TipoEvento)

    @Query("SELECT * FROM tipos_evento")
    suspend fun getAllTiposEvento(): List<TipoEvento>
}