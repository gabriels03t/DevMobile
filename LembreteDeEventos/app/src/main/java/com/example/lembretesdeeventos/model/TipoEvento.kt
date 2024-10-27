@Entity(tableName = "tipos_evento")
data class TipoEvento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val descricao: String
)



