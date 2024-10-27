class LembreteViewModel(application: Application) : AndroidViewModel(application) {

    private val lembreteDao = AppDatabase.getDatabase(application).lembreteDao()
    private val tipoEventoDao = AppDatabase.getDatabase(application).tipoEventoDao()

    val lembretes: LiveData<List<Lembrete>> = liveData {
        val data = lembreteDao.getAllLembretes()
        emit(data)
    }

    fun addLembrete(lembrete: Lembrete) {
        viewModelScope.launch {
            lembreteDao.insertLembrete(lembrete)
        }
    }

    fun updateLembrete(lembrete: Lembrete) {
        viewModelScope.launch {
            lembreteDao.updateLembrete(lembrete)
        }
    }

    fun deleteLembrete(lembrete: Lembrete) {
        viewModelScope.launch {
            lembreteDao.deleteLembrete(lembrete)
        }
    }
}
