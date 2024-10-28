import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LembreteScreen(
    lembreteViewModel: LembreteViewModel = viewModel(),
    onAddLembrete: () -> Unit,
    onEditLembrete: (Lembrete) -> Unit
) {
    val lembretes by lembreteViewModel.lembretes.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lembretes de Eventos") },
                actions = {
                    IconButton(onClick = { onAddLembrete() }) {
                        Icon(Icons.Default.Add, contentDescription = "Adicionar Lembrete")
                    }
                }
            )
        },
        content = { padding ->
            LembreteList(
                lembretes = lembretes,
                onEditLembrete = onEditLembrete,
                onDeleteLembrete = { lembrete -> lembreteViewModel.deleteLembrete(lembrete) }, 
                modifier = Modifier.padding(padding)
            )
        }
    )
}

@Composable
fun LembreteList(
    lembretes: List<Lembrete>,
    onEditLembrete: (Lembrete) -> Unit,
    onDeleteLembrete: (Lembrete) -> Unit,
    modifier: Modifier = Modifier
) {
    if (lembretes.isEmpty()) {
        Text(
            text = "Nenhum lembrete encontrado.",
            modifier = modifier.fillMaxSize(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(lembretes) { lembrete ->
                LembreteItem(
                    lembrete = lembrete,
                    onEditLembrete = onEditLembrete,
                    onDeleteLembrete = onDeleteLembrete
                )
            }
        }
    }
}

@Composable
fun LembreteItem(
    lembrete: Lembrete,
    onEditLembrete: (Lembrete) -> Unit,
    onDeleteLembrete: (Lembrete) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = lembrete.titulo, style = MaterialTheme.typography.h6)
                Text(text = lembrete.data + " " + lembrete.hora, style = MaterialTheme.typography.body2)
                lembrete.nota?.let {
                    Text(text = it, style = MaterialTheme.typography.body2)
                }
            }
            IconButton(onClick = { onEditLembrete(lembrete) }) {
                Icon(Icons.Default.Edit, contentDescription = "Editar Lembrete")
            }
            IconButton(onClick = { onDeleteLembrete(lembrete) }) {
                Icon(Icons.Default.Delete, contentDescription = "Excluir Lembrete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LembreteScreenPreview() {
    LembreteScreen(
        onAddLembrete = {},
        onEditLembrete = {}
    )
}