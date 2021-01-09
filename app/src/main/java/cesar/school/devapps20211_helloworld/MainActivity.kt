package cesar.school.devapps20211_helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pethersilva.devapps20201_helloworld.adapter.EstadoAdapter
import cesar.school.devapps20211_helloworld.model.Estado
import cesar.school.devapps20211_helloworld.databinding.ActivityMainListviewBinding

class MainActivity : AppCompatActivity() {

	private val listEstados = mutableListOf(
		Estado("Paraíba", 0),
		Estado("Pernambuco", 1),
		Estado("Rio Grande do Norte", 2)
	)

	private val mEstadoAdapter by lazy { EstadoAdapter(this, listEstados) }
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
		binding.listView.setOnItemClickListener { parent, view
										  , position, id ->
			val (nome, bandeira) = listEstados[position]
			Toast.makeText(this, "click: $nome $bandeira", Toast.LENGTH_SHORT).show()
		}
		binding.listView.adapter = mEstadoAdapter
	}
}
