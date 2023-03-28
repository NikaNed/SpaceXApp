package com.example.spacexapp.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexapp.databinding.FragmentLaunchesBinding
import com.example.spacexapp.domain.entities.LaunchesEntity
import com.example.spacexapp.presentation.adapter.LaunchesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


class LaunchesFragment : Fragment(), LaunchesAdapter.LaunchesListener {

    private var _binding: FragmentLaunchesBinding? = null
    private val binding: FragmentLaunchesBinding
        get() = _binding ?: throw RuntimeException("FragmentLaunchesBinding == null")

    private val component by lazy {
        (requireActivity().application as SpaceXApp).component
    }

    private lateinit var adapter: LaunchesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LaunchesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = LaunchesAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLaunchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[LaunchesViewModel::class.java]

        with(binding) {
            rvLaunches.adapter = adapter.withLoadStateFooter(LoadLaunchesStateAdapter())
            Log.d("linear","$adapter")
            rvLaunches.itemAnimator = null
            rvLaunches.layoutManager = LinearLayoutManager(context)

            LinearLayoutManager(
                requireActivity().application,
                LinearLayoutManager.VERTICAL,
                false
            )
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getLaunchesPaging().observe(viewLifecycleOwner) {
                    adapter.submitData(lifecycle, it)
                }
            }
        }

        viewModel.progressBar.observe(viewLifecycleOwner) {
            binding.progressBarLaunches.isVisible = it
        }
    }

    private fun launchDetailFragment(flightNumber: String, id: String) {
        findNavController().navigate(
            LaunchesFragmentDirections.actionLaunchesFragmentToLaunchesDetailFragment(
                flightNumber,
                id
            )
        )
    }

    override fun onClick(item: LaunchesEntity) {
        launchDetailFragment(item.flightNumber, item.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
