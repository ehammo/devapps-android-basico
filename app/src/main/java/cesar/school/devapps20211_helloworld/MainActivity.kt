package cesar.school.devapps20211_helloworld

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import cesar.school.devapps20211_helloworld.adapter.EstadoAdapter
import cesar.school.devapps20211_helloworld.model.Estado
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
		setSupportActionBar(binding.myToolbar)
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
		val intent = Intent(this, SecondActivity::class.java)
		intent.putExtra(ESTADO_EXTRA, estado.nome)
		startActivity(intent)
	}

	private fun setupInsertButton() {
		binding.buttonInserir.setOnClickListener {
			val name = binding.editTextPersonName.text.toString()
			if (isNameValid(name)) {
				val lastState = listEstados[listEstados.lastIndex]
				listEstados[listEstados.lastIndex] = Estado(name, (0..2).random())
				listEstados.add(lastState)
				mEstadoAdapter.notifyItemRangeChanged(
					listEstados.lastIndex-1, listEstados.lastIndex)
				binding.editTextPersonName.text.clear()
				binding.editTextPersonName.clearFocus()
			}
		}
	}
	private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()

	companion object {
		val ESTADO_EXTRA = "extra_estado"
	}
}
