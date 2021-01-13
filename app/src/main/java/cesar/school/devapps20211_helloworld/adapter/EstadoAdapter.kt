package com.pethersilva.devapps20201_helloworld.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cesar.school.devapps20211_helloworld.R
import cesar.school.devapps20211_helloworld.databinding.ItemEstadoBinding
import cesar.school.devapps20211_helloworld.model.Estado

class EstadoAdapter(private val context: Context,
                    private val estados: MutableList<Estado>,
                    private val onClickListener: (Estado) -> Unit ) : BaseAdapter() {


	private val bandeiras: TypedArray by lazy {
		context.resources.obtainTypedArray(R.array.bandeiras)
	}

	override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
		val estado = estados[position]
		val holder: ViewHolder
		val linha: View
		if (view == null) {
			val view = LayoutInflater.from(context).inflate(R.layout.item_estado, parent, false)
			val binding = ItemEstadoBinding.bind(view)
			linha = binding.root
			holder = ViewHolder(binding)
			linha.tag = holder
		} else {
			linha = view
			holder = view.tag as ViewHolder
		}
		holder.txtName.text = estado.nome
		holder.imgBandeira.setImageDrawable(bandeiras.getDrawable(estado.bandeira))
		holder.imgBandeira.setOnClickListener {
			onClickListener(estado)
		}
		holder.button.setOnClickListener {
			estados.removeAt(position)
			notifyDataSetChanged()
		}
		return linha
	}

	override fun getItem(position: Int) = estados[position]

	override fun getItemId(position: Int) = position.toLong()

	override fun getCount() = estados.size

	companion object {
		data class ViewHolder(val view: ItemEstadoBinding) {
			val imgBandeira: ImageView = view.imageViewFlag
			val txtName: TextView = view.textViewEstadoNome
			val button: Button = view.button
		}
	}
}
