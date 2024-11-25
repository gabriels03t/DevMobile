package  com.example.lembretedeeventos.ui.theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.lembretedeeventos.data.Evento

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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
                            onAddClick(evento.copy(titulo = titulo, data = data, hora = hora, nota = nota))
                            onDismissRequest()
                        },
                        modifier = Modifier.weight(0.5f),
                        enabled = !isEditMode
                    ) {
                        Text("Adicionar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onEditClick(evento.copy(titulo = titulo, data = data, hora = hora, nota = nota))
                            onDismissRequest()
                        },
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text("Editar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onDeleteClick(evento.id)
                            onDismissRequest()
                        },
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text("Excluir")
                    }
                }
            }
        }
    }
}