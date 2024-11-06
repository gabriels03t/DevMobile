import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lembretes")
data class Lembrete(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val data: String,
    val hora: String,
    val nota: String? = null
)
