package cesar.school.devapps20211_helloworld.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(val nome: String, val modelo: String, val ano: Int): Parcelable
