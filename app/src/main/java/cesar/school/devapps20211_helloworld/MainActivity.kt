package cesar.school.devapps20211_helloworld

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cesar.school.devapps20211_helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	companion object {
		const val MAIN_ACTIVITY_VALUE01_EXTRA_ID = "value01"
		const val MAIN_ACTIVITY_VALUE02_EXTRA_ID = "value02"
		const val MAIN_ACTIVITY_SUM_REQUEST_CODE = 1
		const val MAIN_ACTIVITY_SUM_RESULT_EXTRA_ID = "sum_result_extra"
	}

	private lateinit var binding : ActivityMainBinding
	private var savedValue = 0
	private var currentResult = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.buttonSet.setOnClickListener {
			val value01 = binding.editTextValue01.text.toString()
			if (isValid(value01)) {
				savedValue = value01.toInt()
			}
		}
		binding.buttonSum.setOnClickListener {
			val value02 = binding.editTextValue02.text.toString()
			if (isValid(value02)) {
				currentResult += value02.toInt() + savedValue
				binding.result.text = "Result: $currentResult"
			}
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == Activity.RESULT_OK) {
			if (MAIN_ACTIVITY_SUM_REQUEST_CODE == requestCode) {
				val result = data?.getStringExtra(MAIN_ACTIVITY_SUM_RESULT_EXTRA_ID)
				Toast.makeText(this, "Result: $result", Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun isValid(name: String): Boolean = !name.isNullOrEmpty()
}

