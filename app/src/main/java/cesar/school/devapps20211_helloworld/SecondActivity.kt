package cesar.school.devapps20211_helloworld

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cesar.school.devapps20211_helloworld.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

	private lateinit var binding : ActivitySecondBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySecondBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val value01 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_VALUE01_EXTRA_ID)
		val value02 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_VALUE02_EXTRA_ID)

		if (value01 != null && value02 != null) {
			binding.textView.text = sumValues(value01.toDouble(), value02.toDouble()).toString()
		}

		binding.buttonResult.setOnClickListener {
			val returnIntent = Intent()
			returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_SUM_RESULT_EXTRA_ID,
				binding.textView.text)
			setResult(Activity.RESULT_OK, returnIntent)
			finish()
		}

	}

	private fun sumValues(value01: Double, value02: Double) = value01 + value02
}

