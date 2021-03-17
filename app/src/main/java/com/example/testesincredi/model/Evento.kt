package com.example.testesincredi.model

import java.io.Serializable

class Evento(
    val people: List<Pessoa>,
    val date: Int,
    val description: String,//
    val image: String,//
    val longitude: String,//
    val latitude: String,//
    val price: String,
    val title: String,//
    val id: Int
) : Serializable