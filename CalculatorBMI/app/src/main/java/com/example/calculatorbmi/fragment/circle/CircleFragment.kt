package com.example.calculatorbmi.fragment.circle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.circlecontroller.AreaCircleController
import com.example.calculatorbmi.controller.circlecontroller.Callback
import com.example.calculatorbmi.databinding.FragmentCircleBinding
import com.example.calculatorbmi.fragment.MenuCalculatorFragment

class CircleFragment : Fragment(R.layout.fragment_circle) {
    private lateinit var binding: FragmentCircleBinding
    private lateinit var controller: AreaCircleController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCircleBinding.inflate(inflater, container, false)
        binding.root

        binding.txtResult.setOnClickListener {
            areaCircle()
            binding.crvMasageResult.visibility = View.VISIBLE
        }

        binding.imgBack.setOnClickListener {
            val menuCalculatorFragment = MenuCalculatorFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, menuCalculatorFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    private fun areaCircle() {
        val radius = binding.edtTxfRadius.text.toString().toDouble()
        controller = AreaCircleController(radius)
        controller.setFragmentManager(requireActivity().supportFragmentManager)
        controller.setContext(requireContext())
        controller.areaCircle(object : Callback {
            override fun onResult(result: Double) {
                binding.txtResultCircle.text = result.toString()
            }

            override fun onEror(masage: String) {
                Toast.makeText(context, masage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}