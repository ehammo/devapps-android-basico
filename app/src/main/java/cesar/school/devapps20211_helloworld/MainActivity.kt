package cesar.school.devapps20211_helloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pethersilva.devapps20201_helloworld.adapter.EstadoAdapter
import cesar.school.devapps20211_helloworld.model.Estado
import cesar.school.devapps20211_helloworld.databinding.ActivityMainListviewBinding

class MainActivity : AppCompatActivity() {

	companion object {
		const val ESTADO_ID = "estado_id"
	}

	private val listEstados = mutableListOf(
		Estado("Paraíba", 0),
		Estado("Pernambuco", 1),
		Estado("Rio Grande do Norte", 2)
	)

	private val mEstadoAdapter by lazy {
		EstadoAdapter(this, listEstados) { estado ->
			Log.d("teste", "I'm clicking here!!")
			val intent = Intent(this, SecondActivity::class.java)
			intent.putExtra(ESTADO_ID, estado)
			Log.d("teste", "intent created with state: "+estado.toString())
			startActivity(intent)
		}
	}
	private lateinit var binding : ActivityMainListviewBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainListviewBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setupListview()
		setupInsertButton()
	}

	private fun setupInsertButton() {
		binding.buttonInserir.setOnClickListener {
			val name = binding.editTextPersonName.text.toString()

			if (isNameValid(name)) {
				listEstados.add(Estado(name, (0..2).random()))
				mEstadoAdapter.notifyDataSetChanged()
			}
		}
	}

	private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()

	private fun setupListview() {
		binding.listView.adapter = mEstadoAdapter
	}
}
