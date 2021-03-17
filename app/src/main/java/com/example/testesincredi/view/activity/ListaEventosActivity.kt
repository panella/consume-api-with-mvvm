package com.example.testesincredi.view.activity

import android.os.Bundle
import android.view.View
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
class ListaEventosActivity : AppCompatActivity() {

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
            val adapter = EventoAdapter(eventos, this, clickListener)
            recycler.adapter = adapter
        })

        if (viewModel.eventos().value!!.isEmpty()) {
            progressDialog.show()
            viewModel.atualizaEventos(this) {
                progressDialog.cancel()
            }
        }
    }

    val clickListener: (evento: Evento) -> Unit = { evento ->
        startActivity<DetalheEventoActivity>("evento" to evento)
    }
}