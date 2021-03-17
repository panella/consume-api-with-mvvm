package com.example.testesincredi.view.activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testesincredi.R
import com.example.testesincredi.model.Evento
import com.example.testesincredi.model.formataLocalizacao
import com.example.testesincredi.utils.formataParaBrasileiro

class DetalheEventoActivity: AppCompatActivity() {

	val evento by lazy { intent.getSerializableExtra("evento") as Evento }


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detalhe_evento)

		configuraLayout()
		configuraClick()
	}

	private fun configuraLayout() {
		configuraTitulo()
		configuraLocal()
		configuraData()
		configuraValor()
		configuraDescricao()
		configuraImagem()
	}

	private fun configuraClick() {
		val botaoReturn = findViewById<ImageButton>(R.id.botaoReturn)
		botaoReturn.setOnClickListener {
			this.onBackPressed()
		}
	}

	private fun configuraImagem() {
		val imagem = findViewById<ImageView>(R.id.imageFundo)
		Glide.with(this)
			.load(evento.image)
			.apply(RequestOptions.centerCropTransform().placeholder(R.drawable.placeholder_mini))
			.into(imagem)
	}

	private fun configuraTitulo() {
		val titulo = findViewById<TextView>(R.id.textTitulo)
		titulo.text = evento.title
	}

	private fun configuraLocal() {
		val local = findViewById<TextView>(R.id.textLocal)
		local.text = evento.formataLocalizacao(this)
	}

	private fun configuraData() {
		val data = findViewById<TextView>(R.id.textData)
		data.text = evento.date.formataParaBrasileiro(this)
	}

	private fun configuraValor() {
		val valor = findViewById<TextView>(R.id.textValorNumerico)
		valor.text = evento.price.formataParaBrasileiro()
	}

	private fun configuraDescricao() {
		val descricao = findViewById<TextView>(R.id.textDescricao)
		descricao.text = evento.description
	}
}