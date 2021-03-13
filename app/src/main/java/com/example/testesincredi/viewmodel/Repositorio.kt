package com.example.testesincredi.viewmodel

import com.example.testesincredi.model.Evento
import com.example.testesincredi.viewmodel.database.EventoDAO
import com.example.testesincredi.viewmodel.retrofit.EventoWebService

class Repositorio private constructor(private val eventoDAO: EventoDAO,private val eventoWebService:EventoWebService){

    companion object {
        @Volatile
        private var instance: Repositorio? = null

        fun getInstance(eventoDAO: EventoDAO,eventoWebService:EventoWebService) =
            instance ?: synchronized(this) {
                Repositorio(eventoDAO,eventoWebService).also { instance = it }
        }
    }

    fun todosEventos() = eventoDAO.todosEventos()

    fun recebeEventos() {
        eventoWebService.respostaRecebeEventos{eventos->
            if (eventos != null) {
                eventoDAO.adicionaEventos(eventos)
            }
        }
    }
    fun atualizaEventos() {
        eventoWebService.respostaRecebeEventos{eventos->
            if (eventos != null) {
                eventoDAO.atualizaEventos(eventos)
            }
        }
    }
}