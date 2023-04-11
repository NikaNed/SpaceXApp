package com.example.spacexapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.spacexapp.R
import com.example.spacexapp.databinding.FragmentLaunchesDetailBinding
import com.example.spacexapp.presentation.adapter.CrewAdapter
import javax.inject.Inject

class LaunchesDetailFragment : Fragment() {

    private val args by navArgs<LaunchesDetailFragmentArgs>()

    private var _binding: FragmentLaunchesDetailBinding? = null
    private val binding: FragmentLaunchesDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentLaunchesDetailBinding == null")

    private val component by lazy {
        (requireActivity().application as SpaceXApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailViewModel
    private lateinit var adapter: CrewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLaunchesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),
            viewModelFactory)[DetailViewModel::class.java]
        val flightNumber = args.flightNumber
        viewModel.getDetail(flightNumber)

        viewModel.detail.observe(viewLifecycleOwner) {
            with(binding) {
                tvNameLaunch.text = it.find { it.name == it.name }?.name.toString()
                tvDate.text = getString(
                    R.string.date_of_launch_detail,
                    it.find { it.date_utc == it.date_utc }?.date_utc.toString()
                )

                val large = it.find { it.large == it.large }?.large
                if (large != null) {
                    ivLargeImage.load(large)
                } else ivLargeImage.setImageResource(R.drawable.ic_no_image)

                val success = it.find { it.success == it.success }?.success
                if (success == true) {
                    tvStatus.text = getString(R.string.success)
                } else tvStatus.text = getString(R.string.failure)

                tvCountCores.text = getString(
                    R.string.flights_repition,
                    it.find { it.flight == it.flight }?.flight.toString()
                )

                val details = it.find { it.details == it.details }?.details
                if (details ==null){  tvDetails.text = getString(R.string.no_data)}
                else tvDetails.text = details
                val crew = it.find { it.crew == it.crew }?.crew
                if (crew?.size == 0) tvNoCrew.visibility = View.VISIBLE
            }
        }

        val launchesId = args.id
        viewModel.getCrew(launchesId)
        viewModel.crew.observe(viewLifecycleOwner) {
            adapter = CrewAdapter()
            with(binding) {
                rvCrew.adapter = adapter
                rvCrew.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter.submitList(it)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.progressBar.observe(viewLifecycleOwner) {
            binding.progressBarLaunches.isVisible = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

