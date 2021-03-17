package com.example.testesincredi.view.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testesincredi.R
import com.example.testesincredi.model.Evento
import com.example.testesincredi.model.formataLocalizacao
import com.example.testesincredi.utils.InjectorUtils
import com.example.testesincredi.utils.formataParaBrasileiro
import com.example.testesincredi.view.dialog.CheckInDialog
import com.example.testesincredi.viewmodel.ViewModel
import com.github.clans.fab.FloatingActionButton
import org.jetbrains.anko.share

class DetalheEventoActivity: AppCompatActivity() {

	val evento by lazy { intent.getSerializableExtra("evento") as Evento }
	val viewDaActivity by lazy { window.decorView }
	val viewGroupDaActivity by lazy { viewDaActivity as ViewGroup }


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detalhe_evento)

		val factory = InjectorUtils.provideViewModelFactory(application)
		val viewModel = ViewModelProviders.of(this, factory).get(ViewModel::class.java)

		configuraLayout()
		configuraClick(viewModel)
	}

	private fun configuraLayout() {
		configuraTitulo()
		configuraLocal()
		configuraData()
		configuraValor()
		configuraDescricao()
		configuraImagem()
	}

	private fun configuraClick(viewModel: ViewModel) {
		configuraBotaoCompartilhar()
		configuraBotaoCheckIn(viewModel)
		configuraBotaoReturn()
	}

	private fun configuraBotaoReturn() {
		val botaoReturn = findViewById<ImageButton>(R.id.botaoReturn)
		botaoReturn.setOnClickListener {
			this.onBackPressed()
		}
	}

	private fun configuraBotaoCheckIn(viewModel: ViewModel) {
		val botaoCheckIn = findViewById<FloatingActionButton>(R.id.botaoCheckIn)
		botaoCheckIn.setOnClickListener {
			CheckInDialog(this, viewGroupDaActivity, viewModel, evento).show()
		}
	}

	private fun configuraBotaoCompartilhar() {
		val botaoCompartilhar = findViewById<FloatingActionButton>(R.id.botaoCompartilhar)
		botaoCompartilhar.setOnClickListener {
			share("Confira o evento \"${evento.title}\" no link: http://5f5a8f24d44d640016169133.mockapi.io/api/events/${evento.id}")
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