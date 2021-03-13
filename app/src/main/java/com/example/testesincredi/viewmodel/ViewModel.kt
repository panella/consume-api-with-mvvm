package com.example.testesincredi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testesincredi.model.Evento

class ViewModel(private val repositorio: Repositorio) : ViewModel() {
    fun eventos() = repositorio.todosEventos()
    fun recebeEventos() = repositorio.recebeEventos()
    fun atualizaEventos() = repositorio.atualizaEventos()
}

