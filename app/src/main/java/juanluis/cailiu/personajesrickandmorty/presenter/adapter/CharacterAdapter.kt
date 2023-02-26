package juanluis.cailiu.personajesrickandmorty.presenter.adapter

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import juanluis.cailiu.personajesrickandmorty.R
import juanluis.cailiu.personajesrickandmorty.databinding.ItemCardBinding

import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel
import juanluis.cailiu.personajesrickandmorty.presenter.view.Personajes


class CharacterAdapter (val listener:MyclickListener): RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
            oldItem == newItem
    }

     val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<CharacterModel>) = differ.submitList(list)

    inner class MyViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener{
                val position=bindingAdapterPosition
                listener.onClick(position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemCardBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            tvName.text = item.name
            tvType.text = "${item.status}"
            when {
                item.status.equals("Alive") -> linearLayout.setBackgroundColor(0xFF00FF00.toInt())
                item.status.equals("Dead") -> linearLayout.setBackgroundColor(0xFFFF0000.toInt())
                item.status.equals("unknown") -> linearLayout.setBackgroundColor(0xFF808080.toInt())
                else -> println("No tiene estatus")
            }
            ivCharacter.load(item.image)
            {
                placeholder(R.drawable.ic_launcher_foreground)
                crossfade(1000)
                build()
            }


        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface  MyclickListener{
        fun onClick(position: Int){}
    }
}