@Database(entities = [Lembrete::class, TipoEvento::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lembreteDao(): LembreteDao
    abstract fun tipoEventoDao(): TipoEventoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lembretes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
