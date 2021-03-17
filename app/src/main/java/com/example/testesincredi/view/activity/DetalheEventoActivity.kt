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

class DetalheEventoActivity : AppCompatActivity() {

    val evento by lazy { intent.getSerializableExtra("evento") as Evento }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_evento)

        val botaoReturn = findViewById<ImageButton>(R.id.botaoReturn)
        val imagem = findViewById<ImageView>(R.id.imageFundo)
        val titulo = findViewById<TextView>(R.id.textTitulo)
        val local = findViewById<TextView>(R.id.textLocal)
        val data = findViewById<TextView>(R.id.textData)
        val valor = findViewById<TextView>(R.id.textValorNumerico)
        val descricao = findViewById<TextView>(R.id.textDescricao)

        Glide.with(this)
            .load(evento.image)
            .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.placeholder_mini))
            .into(imagem)

        titulo.text = evento.title
        local.text = evento.latitude
        data.text = evento.date.toString()
        valor.text = evento.price
        descricao.text = evento.description


        botaoReturn.setOnClickListener {
            this.onBackPressed()
        }

    }
}