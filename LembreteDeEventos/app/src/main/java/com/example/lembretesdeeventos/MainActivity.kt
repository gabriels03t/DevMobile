package com.example.lembretesdeeventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lembretesdeeventos.ui.theme.LembretesDeEventosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LembretesDeEventosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LembretesDeEventosTheme {
        Greeting("Android")
    }
}

@Composable
fun LembreteScreen(viewModel: LembreteViewModel) {
    val lembretes by viewModel.lembretes.observeAsState(listOf())

    Column {
        lembretes.forEach { lembrete ->
            Text(lembrete.titulo)
            Text(lembrete.data)
            Text(lembrete.hora)
        }

        Button(onClick = {
            // Ação de criar lembrete
        }) {
            Text(text = "Adicionar Lembrete")
        }
    }
}

fun compartilharLembrete(context: Context, lembrete: Lembrete) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Lembrete: ${lembrete.titulo}")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}