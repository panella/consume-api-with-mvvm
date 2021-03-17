package com.example.testesincredi.viewmodel.retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.testesincredi.model.Evento
import com.example.testesincredi.model.ResponseEvento
import com.example.testesincredi.utils.NetworkUtils
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
				if (response.isSuccessful) {
					callback.invoke(response.body())
				}
				else {

				}
			}
		})
	}

	private fun callbackRecebeEventos(): Call<List<Evento>> {
		val api = NetworkUtils.configuraAPI()
		return api.recebeEventos()
	}

	fun respostaEnviaCheckin(
		requestBody: Map<String, @JvmSuppressWildcards Any?>, callback: (Boolean) -> Unit) {

		val resultadoListaEventos = callbackCheckIn(requestBody)

		resultadoListaEventos.enqueue(object: Callback<ResponseEvento> {
			override fun onFailure(call: Call<ResponseEvento>, t: Throwable) {
				t.printStackTrace()
				callback.invoke(false)
			}

			override fun onResponse(
				call: Call<ResponseEvento>, response: Response<ResponseEvento>) {
				response.isSuccessful.let { callback.invoke(it) }
			}
		})
	}

	private fun callbackCheckIn(requestBody: Map<String, @JvmSuppressWildcards Any?>): Call<ResponseEvento> {
		val api = NetworkUtils.configuraAPI()
		return api.fazCheckIn(requestBody)
	}
}