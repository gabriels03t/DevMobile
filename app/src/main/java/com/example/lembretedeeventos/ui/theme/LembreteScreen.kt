package com.example.lembretedeeventos.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lembretedeeventos.data.Evento
import com.example.lembretedeeventos.data.LembreteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LembreteScreen(viewModel: LembreteViewModel) {
    Text(text = "Tela de Lembretes")
    val eventoList by viewModel.eventoList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedEvento by remember { mutableStateOf<Evento?>(null) }

    LaunchedEffect(Unit) {
        viewModel.listarTodos()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lembretes de Eventos") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar Lembrete")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            eventoList.forEach { evento ->
                LembreteList(evento = evento) { selectedEvento = evento; showDialog = true }
            }
        }

        if (showDialog) {
            LembreteFormDialog(
                evento = selectedEvento ?: Evento(
                    titulo = "",
                    data = "",
                    hora = "",
                    nota = "",
                    tipo_evento_id = 0
                ), // Passa um novo objeto com valores padrão
                isEditMode = selectedEvento != null, // Indica se estamos editando
                onDismissRequest = { showDialog = false },
                onAddClick = { newEvento ->
                    viewModel.inserir(newEvento)
                    showDialog = false
                },
                onEditClick = { updatedEvento ->
                    viewModel.atualizar(updatedEvento)
                    showDialog = false
                },
                onDeleteClick = { id ->
                    viewModel.deletar(id)
                    showDialog = false
                }
            )
        }
    }
}