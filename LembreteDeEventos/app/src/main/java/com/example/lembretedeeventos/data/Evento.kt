package  com.example.lembretedeeventos.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos")
data class Evento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val data: String,
    val hora: String,
    val nota: String,
    val tipo_evento_id: Int
)