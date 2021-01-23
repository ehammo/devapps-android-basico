package cesar.school.devapps20211_helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cesar.school.devapps20211_helloworld.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

	private lateinit var binding : ActivitySecondBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySecondBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.myToolbar)
		val nomeEstado = intent.getStringExtra(MainActivity.ESTADO_EXTRA) ?: ""
		supportActionBar?.title = nomeEstado
		binding.textView.text = getString(R.string.second_activity_message, nomeEstado)

		when (nomeEstado) {
			"Pernambuco" -> { setPernambucoColors() }
			"Paraíba" -> { setParaibaColors() }
			"Rio Grande do Norte" -> { setRNColors() }
		}
	}

	private fun setPernambucoColors() {
		binding.myToolbar.setTitleTextColor(getColor(R.color.white))
		binding.myToolbar.setBackgroundColor(getColor(R.color.dark_blue))
	}

	private fun setParaibaColors() {
		binding.myToolbar.setTitleTextColor(getColor(R.color.black))
		binding.myToolbar.setBackgroundColor(getColor(R.color.red))
	}

	private fun setRNColors() {
		binding.myToolbar.setTitleTextColor(getColor(R.color.white))
		binding.myToolbar.setBackgroundColor(getColor(R.color.dark_green))
	}
}
