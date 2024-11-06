import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LembreteViewModel : ViewModel() {
    private val _lembretes = MutableStateFlow<List<Lembrete>>(emptyList())
    val lembretes: StateFlow<List<Lembrete>> = _lembretes.asStateFlow()

    // Função para deletar um lembrete
    fun delete(lembrete: Lembrete) {
        _lembretes.value = _lembretes.value - lembrete
    }

    // Outras funções para adicionar/editar lembretes
    fun addLembrete(lembrete: Lembrete) {
        _lembretes.value = _lembretes.value + lembrete
    }
}
