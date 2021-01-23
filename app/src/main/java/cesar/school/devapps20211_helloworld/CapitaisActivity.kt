package cesar.school.devapps20211_helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cesar.school.devapps20211_helloworld.adapter.CapitalAdapter
import cesar.school.devapps20211_helloworld.databinding.ActivityCapitalBinding
import cesar.school.devapps20211_helloworld.model.Capital

class CapitaisActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCapitalBinding

    private val listCapital = mutableListOf(
        Capital("Recife"),
        Capital("João Pessoa"),
        Capital("Natal"),
    )
    private val mCapitalAdapter = CapitalAdapter(listCapital)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapitalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = mCapitalAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
