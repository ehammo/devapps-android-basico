package cesar.school.devapps20211_helloworld.adapter

import android.content.Context
import android.content.res.TypedArray
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cesar.school.devapps20211_helloworld.MainActivity
import cesar.school.devapps20211_helloworld.R
import cesar.school.devapps20211_helloworld.databinding.ItemEstadoBinding
import cesar.school.devapps20211_helloworld.model.Estado
class EstadoAdapter(
	private val context: Context,
	private val originalList: MutableList<Estado>, // {"alou", "oi", "ola", "oligarquia"}
	private val callback: (Estado) -> Unit
) : RecyclerView.Adapter<EstadoAdapter.VH>(), Filterable {

	private val estados: MutableList<Estado> = mutableListOf()

	init {
		estados.addAll(originalList)
	}


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

	fun isFiltered(): Boolean {
		return originalList.size != estados.size
	}

	override fun getFilter(): Filter {
		return object : Filter() {
			override fun publishResults(constraint: CharSequence, results: FilterResults) {
				estados.clear()
				estados.addAll(results.values as List<Estado>)
				notifyDataSetChanged()
			}

			override fun performFiltering(constraint: CharSequence): FilterResults {
				val filteredResults: List<Estado> = if (constraint.isEmpty()) {
					originalList
				} else {
					getFilteredResults(constraint.toString())
				}
				val results = FilterResults()
				results.values = filteredResults
				return results
			}

			private fun getFilteredResults(constraint: String): List<Estado> {
				val results = ArrayList<Estado>()
				if (constraint == MainActivity.REMOVE_DUP) {
					results.addAll(originalList.distinct())
				} else {
					results.addAll(originalList)
				}
				return results
			}
		}
	}


}

