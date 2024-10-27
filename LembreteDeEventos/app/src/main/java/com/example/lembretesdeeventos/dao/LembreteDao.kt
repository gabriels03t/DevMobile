@Dao
interface LembreteDao {
    @Insert
    suspend fun insertLembrete(lembrete: Lembrete)

    @Update
    suspend fun updateLembrete(lembrete: Lembrete)

    @Delete
    suspend fun deleteLembrete(lembrete: Lembrete)

    @Query("SELECT * FROM lembretes")
    suspend fun getAllLembretes(): List<Lembrete>
}