package juanluis.cailiu.personajesrickandmorty.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import juanluis.cailiu.personajesrickandmorty.databinding.ActivityMainBinding

import juanluis.cailiu.personajesrickandmorty.presenter.adapter.CharacterAdapter
import  juanluis.cailiu.personajesrickandmorty.presenter.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity
import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,CharacterAdapter.MyclickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter:CharacterAdapter
    private val characterViewModel:CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterAdapter = CharacterAdapter(this@MainActivity)

        binding.recyclerView.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(this@MainActivity,1)
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            characterViewModel.state.collect{
                when{
                    it.loading->{
                        binding.progressBar.visibility =View.VISIBLE
                    }
                    it.fail.isNotBlank()->{
                        binding.progressBar.visibility =View.GONE
                        Toast.makeText(this@MainActivity, it.fail, Toast.LENGTH_SHORT).show()
                    }
                    it.ok.isNotEmpty()->{
                        binding.progressBar.visibility =View.GONE
                        characterAdapter.submitList(it.ok)
                    }
                }
            }
        }

    }



    override fun onClick(position: Int) {
        val clickedItem = characterAdapter.differ.currentList[position]
        println(clickedItem.name)
    }

}