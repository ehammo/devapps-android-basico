package cesar.school.devapps20211_helloworld.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Estado(val nome: String, var bandeira: Int) : Parcelable
