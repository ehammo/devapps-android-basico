package cesar.school.devapps20211_helloworld

import android.content.Intent
import android.os.Bundle
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
			val name = binding.editTextTextPersonName.text.toString()
			if (isNameValid(name)) {
				val secondActivity = Intent(this, SecondActivity::class.java)
				secondActivity.putExtra(MAIN_ACTIVITY_NAME_EXTRA_ID, name)
				startActivity(secondActivity)
			} else {
				Toast.makeText(this, "Please fill your name", Toast.LENGTH_SHORT).show()
			}
		}
	}
	private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()
}
