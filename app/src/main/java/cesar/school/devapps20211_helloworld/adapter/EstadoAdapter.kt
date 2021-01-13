package cesar.school.devapps20211_helloworld.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cesar.school.devapps20211_helloworld.R
import cesar.school.devapps20211_helloworld.databinding.ItemEstadoBinding
import cesar.school.devapps20211_helloworld.model.Estado

class EstadoAdapter(private val context: Context,
					private val estados: List<Estado>,
					private val callback: (Estado) -> Unit) : RecyclerView.Adapter<EstadoAdapter.VH>() {

	private val bandeiras: TypedArray by lazy {
		context.resources.obtainTypedArray(R.array.bandeiras)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estado, parent, false)
		val binding = ItemEstadoBinding.bind(view)
		val vh = VH(binding)
		vh.itemView.setOnClickListener {
			val estado = estados[vh.adapterPosition]
			callback(estado)
		}
		return vh
	}

	override fun getItemCount() = estados.size

	override fun onBindViewHolder(holder: VH, position: Int) {
		val (name, bandeira) = estados[position]
		holder.imgBandeira.setImageDrawable(bandeiras.getDrawable(bandeira))
		holder.txtName.text = name
	}

	class VH(itemView: ItemEstadoBinding): RecyclerView.ViewHolder(itemView.root) {
		val imgBandeira : ImageView = itemView.imageViewFlag
		val txtName: TextView = itemView.textViewEstadoNome
	}
}
