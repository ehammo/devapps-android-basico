package cesar.school.devapps20211_helloworld

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cesar.school.devapps20211_helloworld.databinding.ActivitySecondBinding
import cesar.school.devapps20211_helloworld.model.Estado

class SecondActivity : AppCompatActivity() {

    private val bandeiras: TypedArray by lazy {
        resources.obtainTypedArray(R.array.bandeiras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val estado = intent.getParcelableExtra(MainActivity.ESTADO_ID) ?: Estado("",0)
        binding.textViewEstadoNome.text = estado.nome
        binding.imageViewFlag.setImageDrawable(bandeiras.getDrawable(estado.bandeira))
    }
}