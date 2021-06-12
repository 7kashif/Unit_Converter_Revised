package com.example.unitconverterrevised.converter_fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.unitconverterrevised.R
import com.example.unitconverterrevised.databinding.FragmentConverterBinding


class ConverterFragment : Fragment(R.layout.fragment_converter),AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentConverterBinding
    private lateinit var arrayAdapter:ArrayAdapter<String>
    private lateinit var viewModel: ConverterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentConverterBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=viewLifecycleOwner

        val title by navArgs<ConverterFragmentArgs>()
        val viewModelFactory=ConverterViewModelFactory(title.labelText)
        viewModel=ViewModelProvider(this,viewModelFactory).get(ConverterViewModel::class.java)

        binding.viewModel=viewModel

        viewModel.spinnerList.observe(this,{
            arrayAdapter= this.context?.let { it1 -> ArrayAdapter(it1,R.layout.support_simple_spinner_dropdown_item,it) }!!
            binding.fromSpinner.adapter=arrayAdapter
            binding.toSpinner.adapter=arrayAdapter
        })
        binding.fromSpinner.onItemSelectedListener=this
        binding.toSpinner.onItemSelectedListener=this

        binding.baseValue.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()) {
                    val baseUnit=binding.fromSpinner.selectedItem.toString()
                    val convertingUnit=binding.toSpinner.selectedItem.toString()
                    viewModel.convertToMinimumValue(s.toString(),baseUnit,convertingUnit)
                } else
                    binding.convertedValue.text=" "
            }
            override fun afterTextChanged(s: Editable?) =Unit
        })
        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val baseUnit=binding.fromSpinner.selectedItem.toString()
        val convertingUnit=binding.toSpinner.selectedItem.toString()
        val baseValue=binding.baseValue.text.toString()
        if(baseValue.isNotEmpty())
            viewModel.convertToMinimumValue(baseValue,baseUnit,convertingUnit)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) =Unit
}


