package com.example.testesincredi.viewmodel.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testesincredi.model.Evento

class EventoDAO {
    private val listaDeEventos = mutableListOf<Evento>()
    private val eventos = MutableLiveData<List<Evento>>()
    init {
        eventos.value = listaDeEventos
    }
    fun atualizaEventos(eventos:List<Evento>){
        listaDeEventos.clear()
        adicionaEventos(eventos)
    }
    fun adicionaEventos(eventosa:List<Evento>){
        listaDeEventos.addAll(eventosa)
        eventos.value = listaDeEventos
    }
    fun todosEventos() = eventos as LiveData<List<Evento>>
}