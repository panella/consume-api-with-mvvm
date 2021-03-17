package com.example.testesincredi.viewmodel.database

class BancoDeDados private constructor() {

	var eventoDAO = EventoDAO()

	companion object {
		@Volatile
		private var instance: BancoDeDados? = null

		fun getInstance() = instance ?: synchronized(this) {
			BancoDeDados().also { instance = it }
		}
	}
}