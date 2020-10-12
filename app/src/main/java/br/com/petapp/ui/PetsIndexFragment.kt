package br.com.petapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.petapp.R
import br.com.petapp.databinding.FragmentPetsIndexBinding
import br.com.petapp.viewmodel.PetViewModel

class PetsIndexFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPetsIndexBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pets_index, container, false)
        binding.lifecycleOwner = this

        val petViewModel: PetViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)
        binding.petViewModel = petViewModel

        binding.petsList.apply {
            setHasFixedSize(true)
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(this.context, R.drawable.list_item_divider)!!)
            addItemDecoration(itemDecoration)
        }

        val adapter = PetAdapter()
        binding.petsList.adapter = adapter

        petViewModel.pets.observe(this, { it?.let { adapter.submitList(it) } })

        return binding.root
    }
}