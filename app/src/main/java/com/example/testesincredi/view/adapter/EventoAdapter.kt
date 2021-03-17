package com.example.testesincredi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testesincredi.R
import com.example.testesincredi.model.Evento
import com.example.testesincredi.model.formataLocalizacao
import com.example.testesincredi.utils.formataParaBrasileiro

class EventoAdapter(
	private var listaDeEventos: List<Evento>,
	private val context: Context,
	private var clickListener: (evento: Evento) -> Unit): RecyclerView.Adapter<EventoAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.cardview_evento, parent, false)
		return ViewHolder(view, context)
	}

	override fun getItemCount(): Int {
		return listaDeEventos.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val evento = listaDeEventos[position]
		holder.itemView.setOnClickListener { clickListener.invoke(evento) }
		holder.bindView(evento)
	}

	class ViewHolder(
		itemView: View,
		private val context: Context): RecyclerView.ViewHolder(itemView) {
		fun bindView(evento: Evento) {
			configuraTitulo(evento)
			configuraLocalizacao(evento)
			configuraData(evento)
			configuraPreco(evento)
			configuraImagem(evento)
		}

		private fun configuraTitulo(evento: Evento) {
			val textTitulo = itemView.findViewById<TextView>(R.id.textTitulo)
			textTitulo.text = evento.title
		}

		private fun configuraLocalizacao(evento: Evento) {
			val textLocalizacao = itemView.findViewById<TextView>(R.id.textLocalizacao)
			textLocalizacao.text = evento.formataLocalizacao(context)
		}

		private fun configuraData(evento: Evento) {
			val textData = itemView.findViewById<TextView>(R.id.textData)
			textData.text = evento.date.formataParaBrasileiro(context)
		}

		private fun configuraPreco(evento: Evento) {
			val textPreco = itemView.findViewById<TextView>(R.id.textPreco)
			textPreco.text = evento.price.formataParaBrasileiro()
		}

		private fun configuraImagem(evento: Evento) {
			val imagem = itemView.findViewById<ImageView>(R.id.imagem)
			Glide.with(context)
				.load(evento.image)
				.apply(RequestOptions.circleCropTransform()
						   .placeholder(R.drawable.placeholder_mini))
				.into(imagem)
		}
	}
}