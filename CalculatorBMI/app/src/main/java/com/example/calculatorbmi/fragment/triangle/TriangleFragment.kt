package com.example.calculatorbmi.fragment.triangle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.trianglecontroller.AreaTriangleController
import com.example.calculatorbmi.controller.trianglecontroller.Callback
import com.example.calculatorbmi.databinding.FragmentTriangleBinding
import com.example.calculatorbmi.fragment.MenuCalculatorFragment

class TriangleFragment : Fragment(R.layout.fragment_triangle) {
    private lateinit var binding: FragmentTriangleBinding
    private lateinit var controller: AreaTriangleController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTriangleBinding.inflate(inflater, container, false)

        binding.txtResult.setOnClickListener {
            areaTriangle()
            binding.crvMasageResult.visibility = View.VISIBLE
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

    private fun areaTriangle() {
        val side1 = binding.edtTxfSide1.text.toString().toDouble()
        val side2 = binding.edtTxfSide2.text.toString().toDouble()
        val side3 = binding.edtTxfSide3.text.toString().toDouble()

        controller = AreaTriangleController(side1, side2, side3)
        controller.setFragmentManager(requireActivity().supportFragmentManager)
        controller.setContext(requireContext())
        controller.areaTriangle(object : Callback {
            override fun onResult(result: Double) {
                binding.txtResultTriangle.text = result.toString()
            }

            override fun onError(masage: String) {
                Toast.makeText(context, masage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}