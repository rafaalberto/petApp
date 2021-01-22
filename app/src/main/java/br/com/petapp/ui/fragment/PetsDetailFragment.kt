package br.com.petapp.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.petapp.R
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.databinding.FragmentPetsDetailBinding
import br.com.petapp.ui.fragment.PetsDetailFragmentDirections.actionPetsDetailToPetsIndex
import br.com.petapp.utils.KeyboardHider
import br.com.petapp.viewmodel.PetDetailViewModel
import br.com.petapp.viewmodel.PetDetailViewModelFactory
import com.google.android.material.snackbar.Snackbar


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

        ArrayAdapter.createFromResource(context!!, R.array.genders, R.layout.spinner_gender)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                binding.spinnerGender.adapter = adapter
            }

        setHasOptionsMenu(true)

        petDetailViewModel.navigateToIndex.observe(this, {
            if (it == true) {
                this.findNavController().navigate(actionPetsDetailToPetsIndex())
                petDetailViewModel.doneNavigatingToIndex()
            }
        })

        petDetailViewModel.showSnackBar.observe(this, {
            if (it != null) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
                petDetailViewModel.doneShowingSnackBar()
            }
        })

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.delete).isVisible =
            PetsDetailFragmentArgs.fromBundle(arguments!!).petId != 0L
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
        KeyboardHider.hideKeyboard(view!!, context!!)
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        val name = binding.editTextName.text.toString()
        val breed = binding.editTextBreed.text.toString()
        petDetailViewModel.save(PetEntity(0, name, breed))
    }

    private fun delete() {
        petDetailViewModel.delete()
    }
}