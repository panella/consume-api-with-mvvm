package com.example.testesincredi.viewmodel.retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.testesincredi.model.Evento
import com.example.testesincredi.utils.NetworkUtils
import com.example.testesincredi.viewmodel.database.BancoDeDados
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventoWebService(application: Application): AndroidViewModel(application) {

    fun respostaRecebeEventos(callback: (List<Evento>?) -> Unit) {
        val resultadoListaEventos = callbackRecebeEventos()

        resultadoListaEventos.enqueue(object: Callback<List<Evento>> {
            override fun onFailure(call: Call<List<Evento>>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }

            override fun onResponse(call: Call<List<Evento>>, response: Response<List<Evento>>) {
                callback.invoke(response.body())
            }
        })
    }

    private fun callbackRecebeEventos(): Call<List<Evento>> {
        val api = NetworkUtils.configuraAPI()
        return api.recebeEventos()
    }
}