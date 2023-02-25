package juanluis.cailiu.personajesrickandmorty.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import coil.load
import juanluis.cailiu.personajesrickandmorty.R
import juanluis.cailiu.personajesrickandmorty.databinding.ActivityMainBinding
import juanluis.cailiu.personajesrickandmorty.databinding.ActivityPersonajesBinding
import java.text.SimpleDateFormat
import java.util.*

class Personajes : AppCompatActivity() {
    private lateinit var binding: ActivityPersonajesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val species = intent.getStringExtra("species")
        val created = intent.getStringExtra("created")
        val image = intent.getStringExtra("image")
        val gender = intent.getStringExtra("gender")
        val fecha = getDate(created)

        binding.tvSpecieCharacter.text = species
        binding.tvGenderCharacter.text = gender
        binding.tvCreatedCharacter.text = fecha
        binding.tvNameCharacter.text = name
        binding.imgCharacter.load(image)

    }

    private fun getDate(created: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(created)
        val outputDate = outputFormat.format(date)
        return outputDate
    }


}