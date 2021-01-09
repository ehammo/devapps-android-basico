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
	}
}
