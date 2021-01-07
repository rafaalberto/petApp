package br.com.petapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.petapp.database.entity.Pet
import br.com.petapp.databinding.ListItemPetsBinding

class PetAdapter(private val clickListener: PetListener) : ListAdapter<Pet, PetAdapter.ViewHolder>(PetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: ListItemPetsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pet, clickListener: PetListener) {
            binding.pet = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPetsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class PetDiffCallback : DiffUtil.ItemCallback<Pet>() {

    override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem == newItem
    }
}

class PetListener(val clickListener: (id: Long) -> Unit) {
    fun onClick(pet: Pet) = clickListener(pet.id)
}