package com.example.testesincredi.viewmodel.retrofit

import com.example.testesincredi.model.Evento
import retrofit2.Call
import retrofit2.http.GET

interface API {
	@GET("api/events")
	fun recebeEventos(): Call<List<Evento>>
}