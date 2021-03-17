package com.example.testesincredi.view.dialog

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.testesincredi.R
import com.example.testesincredi.model.Evento
import com.example.testesincredi.viewmodel.ViewModel
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast

class CheckInDialog(
	val context: Context,
	private val viewGroup: ViewGroup,
	private val viewModel: ViewModel,
	val evento: Evento) {


	val viewCriada = criaLayout()
	val botaoCheck by lazy { viewCriada.findViewById<Button>(R.id.botaoCheckInDialog) }
	val botaoCancela by lazy { viewCriada.findViewById<ImageButton>(R.id.botaoFechar) }
	val textNome by lazy { viewCriada.findViewById<EditText>(R.id.editTextNome) }
	val textEmail by lazy { viewCriada.findViewById<EditText>(R.id.editTextEmail) }
	val progressDialog by lazy { context.indeterminateProgressDialog("Fazendo Check-In") }

	var dialog: DialogInterface? = null


	fun show() {
		iniciaDialog()
		configuraClicaveis()
	}

	private fun iniciaDialog() {
		dialog = context.alert { customView = viewCriada }.show()
	}

	private fun configuraClicaveis() {
		configuraBotaoCheckIn()
		configuraBotaoCancelar()
	}

	private fun configuraBotaoCheckIn() {
		botaoCheck.setOnClickListener {
			progressDialog.show()
			viewModel.fazCheckIn(context, textNome.text.toString(), textEmail.text.toString(), evento.id.toString()) { sucesso ->
				progressDialog.dismiss()
				if (sucesso) {
					context.toast("Check-In feito com Sucesso!")
				}
				else {
					context.toast("Verifique os dados digitados ou conexão com servidor.")
				}
			}
		}
	}

	private fun configuraBotaoCancelar() {
		botaoCancela.setOnClickListener {
			dialog?.dismiss()
		}
	}

	private fun criaLayout(): View {
		return LayoutInflater.from(context).inflate(R.layout.dialog_checkin, viewGroup, false)
	}


}