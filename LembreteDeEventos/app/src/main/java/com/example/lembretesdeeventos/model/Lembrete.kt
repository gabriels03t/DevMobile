@Entity(tableName = "lembretes")
data class Lembrete(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val data: String,
    val hora: String,
    val nota: String?,
    @ColumnInfo(name = "tipo_evento_id") val tipoEventoId: Int
)



