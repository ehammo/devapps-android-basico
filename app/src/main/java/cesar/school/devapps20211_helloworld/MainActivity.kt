package cesar.school.devapps20211_helloworld

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.GridLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.pethersilva.devapps20201_helloworld.adapter.EstadoAdapter
import com.pethersilva.devapps20201_helloworld.model.Estado

import cesar.school.devapps20211_helloworld.databinding.ActivityMainListviewBinding


class MainActivity : AppCompatActivity() {

	private lateinit var binding : ActivityMainListviewBinding

	private val listEstados = mutableListOf(
		Estado("Paraíba", 0),
		Estado("Pernambuco", 1),
		Estado("Rio Grande do Norte", 2)
	)
	private val mEstadoAdapter = EstadoAdapter(this,listEstados, this::onEstadoClickListener)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainListviewBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setupRecyclerview()
		setupInsertButton()
	}

	private fun setupRecyclerview() {
		binding.recyclerView.adapter = mEstadoAdapter
		val layoutManager = GridLayoutManager(this, 2)
		layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
			override fun getSpanSize(position: Int): Int {
				return if (position == 0) 2 else 1
			}
		}
		binding.recyclerView.layoutManager = layoutManager
	}

	private fun onEstadoClickListener(estado: Estado) {
		Toast.makeText(this, "Estado: ${estado.nome} Bandeira: ${estado.bandeira}",
			Toast.LENGTH_SHORT).show()
	}

	private fun setupInsertButton() {
		binding.buttonInserir.setOnClickListener {
			val name = binding.editTextPersonName.text.toString()
			if (isNameValid(name)) {
				listEstados.add(Estado(name, (0..2).random()))
				mEstadoAdapter.notifyItemInserted(listEstados.lastIndex)
				binding.editTextPersonName.text.clear()
				binding.editTextPersonName.clearFocus()
			}
		}
	}
	private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()
}
