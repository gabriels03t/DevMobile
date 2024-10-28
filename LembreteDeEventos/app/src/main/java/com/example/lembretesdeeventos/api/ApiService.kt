package com.seuprojeto.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Modelo da resposta da API (ajuste de acordo com a estrutura da resposta da API)
data class HolidayResponse(
    val response: ResponseData
)

data class ResponseData(
    val holidays: List<Holiday>
)

data class Holiday(
    val name: String,
    val description: String,
    val date: HolidayDate
)

data class HolidayDate(
    val iso: String
)

// Definição da interface ApiService
interface ApiService {

    // Método para obter sugestões de feriados
    @GET("holidays")
    fun getFeriados(
        @Query("api_key") apiKey: String,
        @Query("country") country: String,
        @Query("year") year: Int
    ): Call<HolidayResponse>
}
