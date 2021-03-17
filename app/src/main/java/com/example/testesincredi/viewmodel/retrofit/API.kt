package com.example.testesincredi.viewmodel.retrofit

import com.example.testesincredi.model.Evento
import com.example.testesincredi.model.ResponseEvento
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
	@GET("api/events")
	fun recebeEventos(): Call<List<Evento>>

	@POST("api/checkin")
	fun fazCheckIn(@Body requestBody: Map<String, @JvmSuppressWildcards Any?>): Call<ResponseEvento>
}