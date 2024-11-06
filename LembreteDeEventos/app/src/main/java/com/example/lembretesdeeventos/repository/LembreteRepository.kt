import kotlinx.coroutines.flow.Flow

class LembreteRepository(private val lembreteDao: LembreteDao) {
    val allLembretes: Flow<List<Lembrete>> = lembreteDao.getAllLembretes()

    suspend fun insert(lembrete: Lembrete) {
        lembreteDao.insertLembrete(lembrete)
    }

    suspend fun update(lembrete: Lembrete) {
        lembreteDao.updateLembrete(lembrete)
    }

    suspend fun delete(lembrete: Lembrete) {
        lembreteDao.deleteLembrete(lembrete)
    }
}
