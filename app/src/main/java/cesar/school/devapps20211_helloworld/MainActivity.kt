package cesar.school.devapps20211_helloworld

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import cesar.school.devapps20211_helloworld.adapter.EstadoAdapter
import cesar.school.devapps20211_helloworld.databinding.ActivityMainBinding
import cesar.school.devapps20211_helloworld.model.Estado
import java.util.stream.Collectors


class MainActivity : AppCompatActivity() {

	private lateinit var binding : ActivityMainBinding

	private val listEstados = mutableListOf(
		Estado("Paraíba", 0),
		Estado("Pernambuco", 1),
		Estado("Rio Grande do Norte", 2)
	)
	private val mEstadoAdapter = EstadoAdapter(this,listEstados, this::onEstadoClickListener)
	private lateinit var mAvisoAlertDialog : AlertDialog

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.myToolbar)
		setupRecyclerview()
		setupInsertButton()
		setupAlertDialog()
	}

	private fun setupAlertDialog() {
		val builder: AlertDialog.Builder = AlertDialog.Builder(this)
		builder.apply {
			setTitle("Aviso!")
			setMessage("Estado duplicado")
			setCancelable(true)
			setPositiveButton("Ok") { dialog, _ ->
				dialog.dismiss()
			}
		}
		mAvisoAlertDialog = builder.create()
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
				if (isDuplicateState(name)) {
					mAvisoAlertDialog.show()
				} else {
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
	}

	private fun isDuplicateState(name: String): Boolean {
		return listEstados.find { it.nome == name } != null
	}

	private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()

	override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
		R.id.action_capitais -> {
			val onTimeListener = TimePickerDialog.OnTimeSetListener {
					_: TimePicker, hour: Int, min: Int ->
					Toast.makeText(this@MainActivity,
						"Hora escolhida $hour, Minuto $min", Toast.LENGTH_SHORT).show()
			}
			val timeDialog = TimePickerDialog(this, onTimeListener, 0, 0, false)
//			timeDialog.show()

			val onDateListener = DatePickerDialog.OnDateSetListener {
					view, year, month, dayOfMonth ->
				val actualMonth = if (month < 9) {
					"0${month + 1}"
				} else {
					"${month + 1}"
				}
				Toast.makeText(this@MainActivity,
					"Data escolhida $dayOfMonth/$actualMonth/$year", Toast.LENGTH_SHORT).show()
			}
			// Mes vai de 0-11
			val datePickerDialog = DatePickerDialog(this, onDateListener, 2021, 0, 23)
//			datePickerDialog.show()

			val builder: AlertDialog.Builder = AlertDialog.Builder(this)
			builder.apply {
				setTitle("Aviso!")
				setMessage("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
				setCancelable(false)
				setPositiveButton("Ok") { _, _ ->
					Toast.makeText(this@MainActivity, "Dialog ok", Toast.LENGTH_SHORT).show()
				}
				setNegativeButton("close") { dialog, _ ->
					dialog.cancel()
				}
				setNeutralButton("E se o nome do botao for grande?") {dialog, _ ->
					dialog.dismiss()
				}
			}


			val alertDialog = builder.create()
//			alertDialog.show()

//			startActivity(Intent(this, CapitaisActivity::class.java))
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
}
