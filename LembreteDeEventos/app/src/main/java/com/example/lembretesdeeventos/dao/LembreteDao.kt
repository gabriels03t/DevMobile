import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LembreteDao {
    @Insert
    suspend fun insert(lembrete: Lembrete)

    @Delete
    suspend fun delete(lembrete: Lembrete)

    @Query("SELECT * FROM lembretes")
    fun getAll(): Flow<List<Lembrete>>
}
