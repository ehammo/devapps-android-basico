package cesar.school.devapps20211_helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import cesar.school.devapps20211_helloworld.databinding.ActivitySecondBinding
import cesar.school.devapps20211_helloworld.model.Car

class SecondActivity : AppCompatActivity() {

	private lateinit var binding : ActivitySecondBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySecondBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val name = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_NAME_EXTRA_ID)

		val myCar = intent.getParcelableExtra<Car>(MainActivity.MAIN_ACTIVITY_CAR_EXTRA_ID)
		val result = getString(R.string.second_activity_message, name, myCar?.nome)

		binding.textView.text = result
	}
}
