package com.example.testesincredi.viewmodel

import android.content.Context
import com.example.testesincredi.utils.NetworkUtils
import com.example.testesincredi.viewmodel.database.EventoDAO
import com.example.testesincredi.viewmodel.retrofit.EventoWebService

class Repositorio private constructor(
	private val eventoDAO: EventoDAO, private val eventoWebService: EventoWebService) {

	companion object {
		@Volatile
		private var instance: Repositorio? = null

		fun getInstance(eventoDAO: EventoDAO, eventoWebService: EventoWebService) = instance
																					?: synchronized(this) {
																						Repositorio(eventoDAO, eventoWebService).also { instance = it }
																					}
	}

	fun todosEventos() = eventoDAO.todosEventos()

	fun recebeEventos(context: Context, callback: (Unit) -> Unit) {
		val conectado = NetworkUtils.estaConectado(context)
		if (conectado) {
			eventoWebService.respostaRecebeEventos { eventos ->
				if (eventos != null) {
					eventoDAO.adicionaEventos(eventos)
				}
				callback.invoke(Unit)
			}
		}
		else {
			callback.invoke(Unit)
		}
	}

	fun atualizaEventos(context: Context, callback: (Unit) -> Unit) {
		val conectado = NetworkUtils.estaConectado(context)
		if (conectado) {
			eventoWebService.respostaRecebeEventos { eventos ->
				if (eventos != null) {
					eventoDAO.atualizaEventos(eventos)
				}
				callback.invoke(Unit)
			}
		}
		else {
			callback.invoke(Unit)
		}
	}

	fun enviaCheckIn(
		context: Context, nome: String, email: String, id: String, callback: (Boolean) -> Unit) {
		val conectado = NetworkUtils.estaConectado(context)
		if (conectado) {
			val map = mapOf("name" to nome, "email" to email, "eventId" to id)
			eventoWebService.respostaEnviaCheckin(map, callback)
		}
		else {
			callback.invoke(false)
		}
	}
}