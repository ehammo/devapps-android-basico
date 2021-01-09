package cesar.school.devapps20211_helloworld

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cesar.school.devapps20211_helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	companion object {
		const val MAIN_ACTIVITY_NAME_EXTRA_ID = "name"
	}

	private lateinit var binding : ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.button.setOnClickListener {
			val mensagem = binding.editTextTextPersonName.text.toString()
			createAlarm(mensagem, 18, 30)
		}
	}

	private fun createAlarm(mensagem: String, hora: Int, minutos: Int) {
		val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
			putExtra(AlarmClock.EXTRA_MESSAGE, mensagem)
			putExtra(AlarmClock.EXTRA_HOUR, hora)
			putExtra(AlarmClock.EXTRA_MINUTES, minutos)
		}
		if (intent.resolveActivity(packageManager) != null) {
			startActivity(intent)
		} else {
			Toast.makeText(this, "Não foi encontrado uma activity para o " +
					"intent filter escolhido",Toast.LENGTH_SHORT).show()
		}

	}
}
