package com.example.testesincredi.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testesincredi.R
import com.example.testesincredi.model.Evento
import com.example.testesincredi.utils.InjectorUtils
import com.example.testesincredi.view.adapter.EventoAdapter
import com.example.testesincredi.viewmodel.ViewModel
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


@Suppress("DEPRECATION")
class ListaEventosActivity: AppCompatActivity() {

	val progressDialog by lazy { indeterminateProgressDialog("Checando Credenciais") }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_lista_eventos)

		val recycler = findViewById<RecyclerView>(R.id.eventosRecyclerView)
		val factory = InjectorUtils.provideViewModelFactory(application)
		val viewModel = ViewModelProviders.of(this, factory).get(ViewModel::class.java)

		val layoutManager = LinearLayoutManager(this)
		recycler.layoutManager = layoutManager

		viewModel.eventos().observe(this, Observer { eventos ->
			//atualiza adapter
			val adapter = EventoAdapter(eventos, this, clickListener)
			recycler.adapter = adapter
			//aqui fica o codigo para cada interação com os eventos
			//adicionar
			//retirar
			//atualizar
			//aparentemente, toda vez que os eventos mudarem, ele vai atualizar a UI
			//val stringBuilder = StringBuildar()
			//eventos.forEach{ evento ->
			// 	stringBuildar.append("flau flau")
			//}
			//textview.text = stringBuildar.toString()
			//seria igual a atualizar o adpter/o que é mostrado
			//quando algo muda, ele tem que fazer o codigo para atualizar a UI


		})
		//progressDialog.show()
		if(viewModel.eventos().value!!.isEmpty()) {
			viewModel.atualizaEventos()
		}
		//progressDialog.cancel()
	}

	//    @SuppressLint("SimpleDateFormat")
	//    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
	//        // Create a DateFormatter object for displaying date in specified format.
	//        val formatter = SimpleDateFormat(dateFormat)
	//
	//        // Create a calendar object that will convert the date and time value in milliseconds to date.
	//        val calendar = Calendar.getInstance()
	//        calendar.timeInMillis = milliSeconds
	//        return formatter.format(calendar.time)
	//    }

	val clickListener: (evento: Evento) -> Unit = { evento ->
		IniciaIntentDetalhes(evento)
	}

	private fun IniciaIntentDetalhes(evento: Evento) {
		startActivity<DetalheEventoActivity>("evento" to evento)
	}
}