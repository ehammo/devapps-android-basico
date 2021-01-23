package cesar.school.devapps20211_helloworld.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cesar.school.devapps20211_helloworld.R
import cesar.school.devapps20211_helloworld.databinding.ItemCapitalBinding
import cesar.school.devapps20211_helloworld.model.Capital

class CapitalAdapter(private val capitais: List<Capital>) : RecyclerView.Adapter<CapitalAdapter.VH>() {


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_capital, parent, false)
		val binding = ItemCapitalBinding.bind(view)
		return VH(binding)
	}

	override fun getItemCount() = capitais.size

	override fun onBindViewHolder(holder: VH, position: Int) {
		holder.txtName.text = capitais[position].nome
	}

	class VH(itemView: ItemCapitalBinding): RecyclerView.ViewHolder(itemView.root) {
		val txtName: TextView = itemView.textViewCapitalNome
	}
}
