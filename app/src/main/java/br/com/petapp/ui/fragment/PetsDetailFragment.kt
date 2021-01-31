package br.com.petapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.petapp.R
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.databinding.FragmentPetsDetailBinding
import br.com.petapp.model.GenderConverters
import br.com.petapp.ui.fragment.PetsDetailFragmentDirections.actionPetsDetailToPetsIndex
import br.com.petapp.utils.KeyboardHider
import br.com.petapp.viewmodel.PetDetailViewModel
import br.com.petapp.viewmodel.PetDetailViewModelFactory
import com.google.android.material.snackbar.Snackbar

class PetsDetailFragment : Fragment() {

    private val petDetailViewModel: PetDetailViewModel by lazy {
        val application = requireNotNull(this.activity).application
        ViewModelProviders.of(this, PetDetailViewModelFactory(application, getPetIdArgument()))
            .get(PetDetailViewModel::class.java)
    }

    private lateinit var binding: FragmentPetsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pets_detail, container, false)
        binding.petDetailViewModel = petDetailViewModel
        binding.lifecycleOwner = this

        setTitle()

        setHasOptionsMenu(true)

        petDetailViewModel.navigateToIndex.observe(this, {
            if (it == true) {
                this.findNavController().navigate(actionPetsDetailToPetsIndex())
                petDetailViewModel.doneNavigatingToIndex()
            }
        })

        petDetailViewModel.showSnackBar.observe(this, {
            if (it != null) {
                Snackbar.make(activity!!.findViewById(android.R.id.content),
                    it,
                    Snackbar.LENGTH_LONG).show()
                petDetailViewModel.doneShowingSnackBar()
            }
        })

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.delete).isVisible =
            getPetIdArgument() != 0L
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

    private fun getPetIdArgument() = PetsDetailFragmentArgs.fromBundle(arguments!!).petId

    private fun setTitle() {
        (activity as AppCompatActivity).supportActionBar?.title =
            if (getPetIdArgument() == 0L) getString(R.string.add_new_pet) else getString(R.string.edit_pet)
    }

    private fun save() {
        val name = binding.editTextName.text.toString()
        val breed = binding.editTextBreed.text.toString()
        val gender = binding.spinnerGender.selectedItemPosition
        petDetailViewModel.save(PetEntity(0, name, breed, GenderConverters().toGender(gender)))
    }

    private fun delete() {
        petDetailViewModel.delete()
    }
}