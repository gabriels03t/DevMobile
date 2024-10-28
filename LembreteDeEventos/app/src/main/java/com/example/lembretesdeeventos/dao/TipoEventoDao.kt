@Dao
interface TipoEventoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTipoEvento(tipoEvento: TipoEvento)

    @Query("SELECT * FROM tipos_evento")
    fun getAllTiposEvento(): Flow<List<TipoEvento>>
}
