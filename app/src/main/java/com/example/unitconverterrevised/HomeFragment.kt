package com.example.unitconverterrevised

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unitconverterrevised.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)

        binding.timeConverterBtn.setOnClickListener {
            navigate("Time")
        }
        binding.lengthConverterBtn.setOnClickListener {
            navigate("Length")
        }
        binding.tempConverterBtn.setOnClickListener {
            navigate("Temperature")
        }
        binding.weightConverterBtn.setOnClickListener {
            navigate("Weight")
        }

        return binding.root
    }

    private fun navigate(title:String) {
        this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToConverterFragment(title))
    }
}

