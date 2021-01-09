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
		val intentAlarme = Intent(AlarmClock.ACTION_SET_ALARM)
		intentAlarme.putExtra(AlarmClock.EXTRA_MESSAGE, mensagem)
		intentAlarme.putExtra(AlarmClock.EXTRA_HOUR, hora)
		intentAlarme.putExtra(AlarmClock.EXTRA_MINUTES, minutos)

		if (intentAlarme.resolveActivity(packageManager) != null) {
			startActivity(intentAlarme)
		} else {
			Toast.makeText(this, "Não foi encontrado uma activity para o " +
					"intent filter escolhido",Toast.LENGTH_SHORT).show()
		}

	}
}
