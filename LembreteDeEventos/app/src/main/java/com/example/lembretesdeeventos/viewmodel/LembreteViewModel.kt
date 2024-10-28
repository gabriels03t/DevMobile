class LembreteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LembreteRepository
    val allLembretes: LiveData<List<Lembrete>>

    init {
        val lembreteDao = AppDatabase.getDatabase(application).lembreteDao()
        repository = LembreteRepository(lembreteDao)
        allLembretes = repository.allLembretes.asLiveData()
    }

    fun insert(lembrete: Lembrete) = viewModelScope.launch {
        repository.insert(lembrete)
    }

    fun update(lembrete: Lembrete) = viewModelScope.launch {
        repository.update(lembrete)
    }

    fun delete(lembrete: Lembrete) = viewModelScope.launch {
        repository.delete(lembrete)
    }
}
