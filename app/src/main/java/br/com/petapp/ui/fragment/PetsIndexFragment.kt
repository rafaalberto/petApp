package br.com.petapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

    private val petIndexViewModel: PetIndexViewModel by lazy {
        val application = requireNotNull(this.activity).application
        ViewModelProviders.of(this, PetIndexViewModelFactory(application))
            .get(PetIndexViewModel::class.java)
    }

    private lateinit var binding: FragmentPetsIndexBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(inflater, container)
        setTitle()
        setFabButton(binding)
        setListDecoration()
        setListAdapter()
        setNavigationObservable(petIndexViewModel)
        return binding.root
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pets_index, container, false)
        binding.lifecycleOwner = this
        binding.petIndexViewModel = petIndexViewModel
    }

    private fun setTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setFabButton(binding: FragmentPetsIndexBinding) {
        binding.fab.setOnClickListener {
            this.findNavController().navigate(actionPetsIndexToPetsDetail(0))
        }
    }

    private fun setListDecoration() {
        binding.petsList.apply {
            setHasFixedSize(true)
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(this.context,
                R.drawable.list_item_divider)!!)
            addItemDecoration(itemDecoration)
        }
    }

    private fun setListAdapter() {
        val adapter = PetAdapter(PetListener {
            petIndexViewModel.displayToDetail(it)
        })

        binding.petsList.adapter = adapter
        petIndexViewModel.pets.observe(this, { it?.let { adapter.submitList(it) } })
    }

    private fun setNavigationObservable(petIndexViewModel: PetIndexViewModel) {
        petIndexViewModel.navigateToDetail.observe(this, {
            if (it != null) {
                this.findNavController().navigate(actionPetsIndexToPetsDetail(it))
                petIndexViewModel.doneNavigatingToDetail()
            }
        })
    }
}