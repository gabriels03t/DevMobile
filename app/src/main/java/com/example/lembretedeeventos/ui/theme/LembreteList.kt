package com.example.lembretedeeventos.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lembretedeeventos.data.Evento

@Composable
fun LembreteList(evento: Evento, onClick: (Evento) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(evento) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Título: ${evento.titulo}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Data: ${evento.data}")
            Text(text = "Hora: ${evento.hora}")
            Text(text = "Nota: ${evento.nota}")
        }
    }
}