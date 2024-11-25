package com.example.lembretedeeventos.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lembretesdeeventos.LembreteApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LembreteViewModel(application: LembreteApplication) : ViewModel() {
    private val eventoDao = application.database.eventoDao()
    private val _eventoList = MutableStateFlow<List<Evento>>(emptyList())
    val eventoList: StateFlow<List<Evento>> = _eventoList

    init {
        listarTodos()
    }

    // Construtor padrão necessário para ViewModelProvider
    @Suppress("unused")
    constructor() : this(LembreteApplication.instance)

    fun listarTodos() {
        viewModelScope.launch {
            _eventoList.value = eventoDao.listarTodos()
        }
    }

    fun inserir(evento: Evento) {
        viewModelScope.launch {
            eventoDao.inserir(evento)
            listarTodos()
        }
    }

    fun atualizar(evento: Evento) {
        viewModelScope.launch {
            eventoDao.atualizar(evento)
            listarTodos()
        }
    }

    fun deletar(id: Int) {
        viewModelScope.launch {
            eventoDao.deletar(id)
            listarTodos()
        }
    }
}