package com.example.testesincredi.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.testesincredi.R
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long.formataParaBrasileiro(context: Context): String {
	val formatter = SimpleDateFormat(context.getString(R.string.date_format))
	return formatter.format(Date(this * 1000 + 28800000))
}