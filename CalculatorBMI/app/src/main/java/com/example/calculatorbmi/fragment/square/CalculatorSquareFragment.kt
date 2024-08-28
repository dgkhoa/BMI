package com.example.calculatorbmi.fragment.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.squarecontroller.AreaSquareController
import com.example.calculatorbmi.controller.squarecontroller.CallBack
import com.example.calculatorbmi.databinding.FragmentCalculatorSquareBinding
import com.example.calculatorbmi.fragment.MenuCalculatorFragment

class CalculatorSquareFragment : Fragment(R.layout.fragment_calculator_square) {
    private lateinit var binding: FragmentCalculatorSquareBinding
    private lateinit var controllerArea: AreaSquareController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorSquareBinding.inflate(inflater, container, false)
        binding.root
        binding.txtResult.setOnClickListener {
            calculatorArea()
            binding.crvMesageReusult.visibility = View.VISIBLE
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
        val sideEnter = binding.edtTextField.text.toString().toDouble()
        controllerArea = AreaSquareController(sideEnter)
        controllerArea.setFragmentManager(requireActivity().supportFragmentManager)
        controllerArea.setContext(requireContext())
        controllerArea.areaSquare(object : CallBack {
            override fun onResult(result: Double) {
                binding.txtSide.text = sideEnter.toString()
                binding.txtResultSquare.text = result.toString()
            }

            override fun onError(message: String) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}