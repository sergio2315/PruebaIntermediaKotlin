package com.example.pruebaintermediakotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.text.set
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pruebaintermediakotlin.databinding.FragmentSecondBinding
import com.example.pruebaintermediakotlin.model.ConViewModel
import com.example.pruebaintermediakotlin.model.Consumptions

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ConViewModel by activityViewModels()
    private var idConsumptions: Int=0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.editText1.setText(it.item)
                binding.editText2.setText(it.price.toString())
                binding.editText3.setText(it.quantity.toString())
                binding.textTotal.setText(it.total.toString())
                idConsumptions = it.id
            }
        })
        binding.btnCalculate.setOnClickListener{
            val priceCon = binding.editText2.text.toString().toInt()
            val quantityCon = binding.editText3.text.toString().toInt()
            binding.textTotal.setText(viewModel.multiplicar(priceCon,quantityCon).toString())
        }
        binding.btnSave.setOnClickListener {
            saveData()
            binding.editText1.setText("")
            binding.editText2.setText("")
            binding.editText3.setText("")
            binding.textTotal.setText("0")
            Toast.makeText(context, "Producto guardado con exito!", Toast.LENGTH_SHORT).show()
        }
        binding.btnReturn.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
    fun saveData() {
        val item = binding.editText1.text.toString()
        val price = binding.editText2.text.toString().toInt()
        val quantity = binding.editText3.text.toString().toInt()

        if (item.isEmpty()){
            Toast.makeText(context,"Debes ingresar todos los datos",Toast.LENGTH_LONG).show()
        }else{
            if (idConsumptions == 0){
                val newConsumptions = Consumptions(item = item,
                                                    price = price,
                                                    quantity = quantity,
                                                    total = price*quantity)
                viewModel.insertConsumption(newConsumptions)
            }else{
                val newConsumptions = Consumptions(
                    id = idConsumptions,
                    item = item,
                    price = price,
                    quantity = quantity,
                    total = price*quantity)
                viewModel.insertConsumption(newConsumptions)
            }
        }
    }
}