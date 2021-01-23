package cesar.school.devapps20211_helloworld

import android.os.Bundle
import android.view.MenuItem
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
		Toast.makeText(this, "Estado: ${estado.nome} Bandeira: ${estado.bandeira}",
			Toast.LENGTH_SHORT).show()
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

	override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
		R.id.action_settings -> {
			// User chose the "Settings" item, show the app settings UI...
			true
		}

		R.id.action_favorite -> {
			// User chose the "Favorite" action, mark the current item
			// as a favorite...
			true
		}

		else -> {
			// If we got here, the user's action was not recognized.
			// Invoke the superclass to handle it.
			super.onOptionsItemSelected(item)
		}
	}

}
