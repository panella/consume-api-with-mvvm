package com.example.testesincredi.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel

class ViewModel(private val repositorio: Repositorio): ViewModel() {
	fun eventos() = repositorio.todosEventos()
	fun recebeEventos(
		context: Context,
		callback: (Unit) -> Unit) = repositorio.recebeEventos(context, callback)

	fun atualizaEventos(
		context: Context,
		callback: (Unit) -> Unit) = repositorio.atualizaEventos(context, callback)
}

