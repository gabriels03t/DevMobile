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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seuprojeto.network.RetrofitInstance
import com.seuprojeto.network.HolidayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiKey = "Z797txCja7giQAwJKzRMY6AHGLbgMqHX" // Insira sua API Key do Calendarific aqui

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Exemplo: obtendo feriados no Brasil para o ano atual
        RetrofitInstance.api.getFeriados(apiKey, "BR", 2024).enqueue(object : Callback<HolidayResponse> {
            override fun onResponse(call: Call<HolidayResponse>, response: Response<HolidayResponse>) {
                if (response.isSuccessful) {
                    val holidays = response.body()?.response?.holidays
                    holidays?.forEach { holiday ->
                        Log.d("MainActivity", "Feriado: ${holiday.name}, Data: ${holiday.date.iso}")
                    }
                } else {
                    Log.e("MainActivity", "Falha na requisição: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<HolidayResponse>, t: Throwable) {
                Log.e("MainActivity", "Erro na API: ${t.message}")
            }
        })
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

object RetrofitInstance {

    private const val BASE_URL = "https://calendarific.com/api/v2/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}