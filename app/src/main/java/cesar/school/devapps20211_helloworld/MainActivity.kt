package cesar.school.devapps20211_helloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cesar.school.devapps20211_helloworld.adapter.EstadoAdapter
import cesar.school.devapps20211_helloworld.databinding.ActivityMainBinding
import cesar.school.devapps20211_helloworld.model.Estado


class MainActivity : AppCompatActivity() {

	private lateinit var binding : ActivityMainBinding

	private val listEstados = mutableListOf(
		Estado("Paraíba", 0),
		Estado("Pernambuco", 1),
		Estado("Rio Grande do Norte", 2),
		Estado("Paraíba", 0),
		Estado("Pernambuco", 1),
		Estado("Rio Grande do Norte", 2)
	)
	private val mEstadoAdapter = EstadoAdapter(this,listEstados, this::onEstadoClickListener)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.myToolbar)
		setupRecyclerview()
		setupInsertButton()
	}

	private fun setupRecyclerview() {
		binding.recyclerView.adapter = mEstadoAdapter
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
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
		R.id.action_capitais -> {
			startActivity(Intent(this, CapitaisActivity::class.java))
			true
		}

		R.id.action_filter -> {
			val builder = AlertDialog.Builder(this)
			val view = layoutInflater.inflate(R.layout.custom_dialog, null)
			val switch = view.findViewById<SwitchCompat>(R.id.switchCompat)
			if (mEstadoAdapter.isFiltered()) {
				switch.isChecked = true
			}
			builder.apply {
				setView(view)
				setPositiveButton("ok") { dialog, _ ->
					val checked = switch.isChecked
					if (checked) {
						mEstadoAdapter.filter.filter(REMOVE_DUP)
					} else {
						mEstadoAdapter.filter.filter("")
					}
					dialog.dismiss()
				}
			}
			builder.create().show()
			true
		}

		else -> {
			// If we got here, the user's action was not recognized.
			// Invoke the superclass to handle it.
			super.onOptionsItemSelected(item)
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	companion object {
		val REMOVE_DUP = "REMOVE_DUP"
	}
}
