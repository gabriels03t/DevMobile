@Dao
interface LembreteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLembrete(lembrete: Lembrete)

    @Update
    suspend fun updateLembrete(lembrete: Lembrete)

    @Delete
    suspend fun deleteLembrete(lembrete: Lembrete)

    @Query("SELECT * FROM lembretes")
    fun getAllLembretes(): Flow<List<Lembrete>>
}
