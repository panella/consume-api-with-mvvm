package com.example.testesincredi.utils

import android.app.Application
import com.example.testesincredi.viewmodel.Repositorio
import com.example.testesincredi.viewmodel.ViewModelFactory
import com.example.testesincredi.viewmodel.database.BancoDeDados
import com.example.testesincredi.viewmodel.retrofit.EventoWebService

object InjectorUtils{
    fun provideViewModelFactory(application: Application): ViewModelFactory{
        val repositorio = Repositorio.getInstance(BancoDeDados.getInstance().eventoDAO, EventoWebService(application))
        return ViewModelFactory(repositorio)
    }
}