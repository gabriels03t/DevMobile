import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoEventoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTipoEvento(tipoEvento: TipoEvento)

    @Query("SELECT * FROM tipos_evento")
    fun getAllTiposEvento(): Flow<List<TipoEvento>>
}
