package juanluis.cailiu.personajesrickandmorty.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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

        binding.btnEditarDatos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                binding.textBoxContainerEditable.visibility=View.VISIBLE
                binding.textBoxContainer.visibility=View.GONE
                binding.edEspecie.text = Editable.Factory.getInstance().newEditable(species)
                binding.edGenero.text = Editable.Factory.getInstance().newEditable(gender)
                binding.edFechaCreacion.text = Editable.Factory.getInstance().newEditable(created)
                binding.edName.text =Editable.Factory.getInstance().newEditable(name)
                binding.btnGuardar.visibility=View.VISIBLE
                binding.btnEditarDatos.visibility=View.GONE
            }
        })
        binding.btnGuardar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                binding.tvSpecieCharacter.text = binding.edEspecie.text
                binding.tvGenderCharacter.text = binding.edGenero.text
                binding.tvCreatedCharacter.text =binding.edFechaCreacion.text
                binding.tvNameCharacter.text = binding.edName.text
                binding.btnGuardar.visibility=View.GONE
                binding.btnEditarDatos.visibility=View.VISIBLE
                binding.textBoxContainerEditable.visibility=View.GONE
                binding.textBoxContainer.visibility=View.VISIBLE
            }
        })
    }

    private fun getDate(created: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(created)
        val outputDate = outputFormat.format(date)
        return outputDate
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}