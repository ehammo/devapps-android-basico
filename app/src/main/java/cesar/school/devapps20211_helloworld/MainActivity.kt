package cesar.school.devapps20211_helloworld

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cesar.school.devapps20211_helloworld.databinding.ActivityMainBinding
import cesar.school.devapps20211_helloworld.model.Car

class MainActivity : AppCompatActivity() {
	companion object {
		const val MAIN_ACTIVITY_NAME_EXTRA_ID = "name"
		const val MAIN_ACTIVITY_CAR_EXTRA_ID = "car"
	}

	private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
		binding.button.setOnClickListener {
			val name = binding.editTextTextPersonName.text.toString()
			if (isNameValid(name)) {
				val secondActivity = Intent(this, SecondActivity::class.java)
				secondActivity.putExtra(MAIN_ACTIVITY_NAME_EXTRA_ID, name)

				//Creating a car instance
				val myCar = Car("Ferrari", "Ferrari", 2020)
				secondActivity.putExtra(MAIN_ACTIVITY_CAR_EXTRA_ID, myCar)
				startActivity(secondActivity)
			} else {
				Toast.makeText(this, "Please fill your name", Toast.LENGTH_SHORT).show()
			}
		}
	}
	private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()
}
