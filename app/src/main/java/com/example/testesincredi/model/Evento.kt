package com.example.testesincredi.model

import android.content.Context
import android.location.Geocoder
import java.io.Serializable
import java.math.BigDecimal

class Evento(
	val people: List<Pessoa>,
	val date: Long,
	val description: String,
	val image: String,
	val longitude: Double,
	val latitude: Double,
	val price: BigDecimal,
	val title: String,
	val id: Int): Serializable

fun Evento.formataLocalizacao(context: Context): String {
	val endereco = Geocoder(context).getFromLocation(this.latitude, this.longitude, 1)
	return endereco[0].adminArea + " - " + endereco[0].countryName
}

