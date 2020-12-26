package br.com.petapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.petapp.R
import br.com.petapp.databinding.FragmentPetsDetailBinding
import br.com.petapp.viewmodel.PetDetailViewModel
import br.com.petapp.viewmodel.PetDetailViewModelFactory

class PetsDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPetsDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pets_detail, container, false)

        val arguments = PetsDetailFragmentArgs.fromBundle(arguments!!)

        val petDetailViewModelFactory = PetDetailViewModelFactory(arguments.petName)
        val petDetailViewModel: PetDetailViewModel =
            ViewModelProviders.of(this, petDetailViewModelFactory)
                .get(PetDetailViewModel::class.java)

        binding.petDetailViewModel = petDetailViewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_pets_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> save()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        Toast.makeText(context, getText(R.string.pet_saved), Toast.LENGTH_LONG).show()
    }
}