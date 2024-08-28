package com.example.calculatorbmi.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.databinding.FragmentMenuCalculatorBinding
import com.example.calculatorbmi.fragment.bmi.CalculatorBmiFragment
import com.example.calculatorbmi.fragment.circle.CircleFragment
import com.example.calculatorbmi.fragment.rectangle.RectangleFragment
import com.example.calculatorbmi.fragment.square.CalculatorSquareFragment
import com.example.calculatorbmi.fragment.triangle.TriangleFragment

class MenuCalculatorFragment : Fragment(R.layout.fragment_menu_calculator) {
    private lateinit var binding: FragmentMenuCalculatorBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuCalculatorBinding.bind(view)

        eventClickMenu()

    }

    private fun eventClickMenu() {
        binding.cardViewBmi.setOnClickListener {
            val calculatorBmiFragment = CalculatorBmiFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, calculatorBmiFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewSquare.setOnClickListener {
            val squareFragment = CalculatorSquareFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, squareFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewRectangle.setOnClickListener {
            val rectangleFragment = RectangleFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, rectangleFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewCircle.setOnClickListener {
            val circleFragemnt = CircleFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, circleFragemnt)
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewTriangle.setOnClickListener {
            val triangleFragment = TriangleFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, triangleFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}


