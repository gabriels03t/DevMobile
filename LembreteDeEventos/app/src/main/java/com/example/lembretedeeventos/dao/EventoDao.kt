package  com.example.lembretedeeventos.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lembretedeeventos.data.Evento

@Dao
interface EventoDao {

    @Insert
    suspend fun inserir(evento: Evento)

    @Update
    suspend fun atualizar(evento: Evento)

    @Query("SELECT * FROM eventos")
    suspend fun listarTodos(): List<Evento>

    @Query("DELETE FROM eventos WHERE id = :id")
    suspend fun deletar(id: Int)
}