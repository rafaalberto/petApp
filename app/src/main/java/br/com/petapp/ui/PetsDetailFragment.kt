package br.com.petapp.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.petapp.R
import br.com.petapp.database.entity.Pet
import br.com.petapp.databinding.FragmentPetsDetailBinding
import br.com.petapp.ui.PetsDetailFragmentDirections.actionPetsDetailToPetsIndex
import br.com.petapp.viewmodel.PetDetailViewModel
import br.com.petapp.viewmodel.PetDetailViewModelFactory

class PetsDetailFragment : Fragment() {

    private val petDetailViewModel: PetDetailViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val arguments = PetsDetailFragmentArgs.fromBundle(arguments!!)
        ViewModelProviders.of(this, PetDetailViewModelFactory(application, arguments.petId))
            .get(PetDetailViewModel::class.java)
    }

    private lateinit var binding: FragmentPetsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pets_detail, container, false)
        binding.petDetailViewModel = petDetailViewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        petDetailViewModel.navigateToIndex.observe(this, {
            if (it) this.findNavController().navigate(actionPetsDetailToPetsIndex())
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_pets_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> save()
            R.id.delete -> delete()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        val name = binding.editTextName.text.toString()
        val breed = binding.editTextBreed.text.toString()
        petDetailViewModel.save(Pet(0, name, breed))
    }

    private fun delete() {
        petDetailViewModel.delete()
    }
}