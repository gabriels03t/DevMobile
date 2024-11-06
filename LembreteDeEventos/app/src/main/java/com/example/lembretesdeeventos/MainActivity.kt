package com.example.lembretesdeeventos

import Lembrete
import LembreteViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.lembretesdeeventos.ui.theme.LembretesDeEventosTheme
import com.seuprojeto.network.ApiService
import com.seuprojeto.network.HolidayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val apiKey = "Z797txCja7giQAwJKzRMY6AHGLbgMqHX"
    private val lembreteViewModel: LembreteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LembretesDeEventosTheme {
                LembreteScreen(viewModel = lembreteViewModel)
            }
        }

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
fun LembreteScreen(viewModel: LembreteViewModel) {
    // Usa collectAsState para obter o estado dos lembretes
    val lembretes by viewModel.lembretes.collectAsState()

    Column {
        lembretes.forEach { lembrete ->
            Text(lembrete.titulo)
            Text(lembrete.data)
            Text(lembrete.hora)
        }

        Button(onClick = {
            // Lógica para adicionar lembrete
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


