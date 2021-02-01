package com.example.pruebaintermediakotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaintermediakotlin.databinding.FragmentFirstBinding
import com.example.pruebaintermediakotlin.model.ConAdapter
import com.example.pruebaintermediakotlin.model.ConViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ConViewModel by activityViewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= ConAdapter()
        binding.rvConsumption.adapter = adapter
        binding.rvConsumption.layoutManager = LinearLayoutManager(context)
        binding.fab.setOnClickListener {
            viewModel.selected(consumptions = null)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewModel.allConsumptions.observe(viewLifecycleOwner, Observer{
            it.let {
                adapter.update(it)
            }
        })
        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            viewModel.selected(it)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        })
        binding.btnDeleteAll.setOnClickListener{
            viewModel.deleAllConsumption()
        }
    }
}