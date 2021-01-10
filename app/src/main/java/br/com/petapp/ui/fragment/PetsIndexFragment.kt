package br.com.petapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.petapp.R
import br.com.petapp.databinding.FragmentPetsIndexBinding
import br.com.petapp.ui.adapter.PetAdapter
import br.com.petapp.ui.adapter.PetListener
import br.com.petapp.ui.fragment.PetsIndexFragmentDirections.actionPetsIndexToPetsDetail
import br.com.petapp.viewmodel.PetIndexViewModel
import br.com.petapp.viewmodel.PetIndexViewModelFactory

class PetsIndexFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentPetsIndexBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pets_index, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val petIndexViewModel: PetIndexViewModel = ViewModelProviders.of(this, PetIndexViewModelFactory(application))
            .get(PetIndexViewModel::class.java)

        binding.petIndexViewModel = petIndexViewModel

        binding.petsList.apply {
            setHasFixedSize(true)
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(this.context, R.drawable.list_item_divider)!!)
            addItemDecoration(itemDecoration)
        }

        val adapter = PetAdapter(PetListener {
            petIndexViewModel.displayToDetail(it)
        })

        petIndexViewModel.navigateToDetail.observe(this, {
            if (it != null) {
                this.findNavController().navigate(actionPetsIndexToPetsDetail(it))
                petIndexViewModel.doneNavigatingToDetail()
            }
        })

        binding.petsList.adapter = adapter

        petIndexViewModel.pets.observe(this, { it?.let { adapter.submitList(it) } })

        binding.fab.setOnClickListener {
            this.findNavController().navigate(actionPetsIndexToPetsDetail(0))
        }

        return binding.root
    }
}