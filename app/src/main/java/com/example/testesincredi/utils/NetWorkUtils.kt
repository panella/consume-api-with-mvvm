package com.example.testesincredi.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.testesincredi.viewmodel.retrofit.API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {

    companion object {

		fun getRetrofitInstance(path: String): Retrofit {
			val okHttp = configuraOkHttp()
			return Retrofit.Builder()
				.baseUrl(path)
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttp.build())
				.build()
		}

		private fun configuraOkHttp(): OkHttpClient.Builder {
			val okHttp = OkHttpClient.Builder()
			val logging = HttpLoggingInterceptor()
			logging.level = HttpLoggingInterceptor.Level.BODY
			okHttp.addInterceptor(logging)
			return okHttp
		}

		fun configuraAPI(): API {
			val retrofit = getRetrofitInstance("http://5f5a8f24d44d640016169133.mockapi.io/")
			return retrofit.create(API::class.java)
		}

        @Suppress("DEPRECATION")
		fun estaConectado(context: Context): Boolean {
			val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
			return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
		}
    }
}
