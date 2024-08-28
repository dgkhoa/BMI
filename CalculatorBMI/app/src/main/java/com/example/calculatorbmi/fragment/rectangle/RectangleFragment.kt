package com.example.calculatorbmi.fragment.rectangle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.rectanglecontroller.AreaRectangleController
import com.example.calculatorbmi.controller.rectanglecontroller.Callback
import com.example.calculatorbmi.databinding.FragmentRectangleBinding
import com.example.calculatorbmi.fragment.MenuCalculatorFragment


class RectangleFragment : Fragment(R.layout.fragment_rectangle) {
    private lateinit var binding: FragmentRectangleBinding
    private lateinit var controller: AreaRectangleController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRectangleBinding.inflate(inflater, container, false)
        binding.root

        binding.txtResult.setOnClickListener {
            calculatorArea()
            binding.crvmasageResult.visibility = View.VISIBLE
        }

        binding.imgBack.setOnClickListener {
            val menuCalculator = MenuCalculatorFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, menuCalculator)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    private fun calculatorArea() {
        val width = binding.edtTxfWidth.text.toString().toDouble()
        val height = binding.edtTxfHeight.text.toString().toDouble()
        controller = AreaRectangleController(width, height)
        controller.setFragmentManager(requireActivity().supportFragmentManager)
        controller.setContext(requireContext())
        controller.areaRectangle(object : Callback {
            override fun onResult(result: Double) {
                binding.txtResultRectangle.text = result.toString()
            }

            override fun onError(message: String) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}