package com.example.lembretedeeventos
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.lembretedeeventos.data.Evento
import com.example.lembretedeeventos.data.LembreteViewModel
import com.example.lembretedeeventos.ui.theme.LembreteDeEventosTheme

class MainActivity : ComponentActivity() {
    private val viewModel: LembreteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LembreteDeEventosTheme {
                LembreteScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun LembreteList(evento: Evento, onClick: (Evento) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(evento) }, // Adicionando a ação de clique
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Título: ${evento.titulo}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Data: ${evento.data}")
            // Adicione mais campos conforme necessário
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LembreteScreen(viewModel: LembreteViewModel) {
    val eventoList by viewModel.eventoList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedEvento by remember { mutableStateOf<Evento?>(null) } // Evento selecionado

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
                evento = selectedEvento ?: Evento(titulo = "", data = "", hora = "", nota = "", tipo_evento_id = 0), // Novo objeto vazio
                isEditMode = selectedEvento != null,
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

// Implementação do LembreteFormDialog
@Composable
fun LembreteFormDialog(
    evento: Evento,
    isEditMode: Boolean,
    onDismissRequest: () -> Unit,
    onAddClick: (Evento) -> Unit,
    onEditClick: (Evento) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var titulo by remember { mutableStateOf(evento.titulo) }
                var data by remember { mutableStateOf(evento.data) }
                var hora by remember { mutableStateOf(evento.hora) }
                var nota by remember { mutableStateOf(evento.nota) }

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = data,
                    onValueChange = { data = it },
                    label = { Text("Data") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it },
                    label = { Text("Hora") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nota,
                    onValueChange = { nota = it },
                    label = { Text("Nota") }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (isEditMode) {
                                onEditClick(evento.copy(titulo = titulo, data = data, hora = hora, nota = nota))
                            } else {
                                onAddClick(Evento(titulo = titulo, data = data, hora = hora, nota = nota, tipo_evento_id = 0))
                            }
                            onDismissRequest()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (isEditMode) "Editar" else "Adicionar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    if (isEditMode) {
                        Button(
                            onClick = {
                                onDeleteClick(evento.id)
                                onDismissRequest()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Deletar")
                        }
                    }
                }
            }
        }
    }
}