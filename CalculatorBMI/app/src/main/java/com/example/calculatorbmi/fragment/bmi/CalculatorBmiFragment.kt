package com.example.calculatorbmi.fragment.bmi

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.bmicontroller.CalculatorBmiController
import com.example.calculatorbmi.controller.bmicontroller.CallBack
import com.example.calculatorbmi.controller.bmicontroller.SuggestController
import com.example.calculatorbmi.databinding.FragmentCalculatorBmiBinding
import com.example.calculatorbmi.fragment.MenuCalculatorFragment
import java.util.Calendar

class CalculatorBmiFragment : Fragment(R.layout.fragment_calculator_bmi) {
    private lateinit var binding: FragmentCalculatorBmiBinding
    private lateinit var controller: CalculatorBmiController
    private lateinit var suggest: SuggestController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBmiBinding.inflate(inflater, container, false)
        val view = binding.root

        setupSpinners()
        setupDatePicker()

        binding.btnCalculator.setOnClickListener {
            confirm()
        }
        binding.imgBack.setOnClickListener {
            val menuCalculator = MenuCalculatorFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout,menuCalculator)
                .addToBackStack(null)
                .commit()
        }
        return view
    }

    private fun confirm() {
        val dialog = AlertDialog.Builder(context)
        dialog.apply {
            setTitle("CONFIRM")
            setMessage("Are you sure?")
            setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                transferResultFragment()
            }
        }
        dialog.show()
    }

    private fun transferResultFragment() {
        val selectedHeight = binding.spinnerHeight.selectedItem.toString().toDoubleOrNull() ?: 0.0
        val selectedWeight = binding.spinnerWeight.selectedItem.toString().toDoubleOrNull() ?: 0.0

        controller = CalculatorBmiController(selectedHeight, selectedWeight)
        controller.setFragmentManager(requireActivity().supportFragmentManager)
        controller.setContext(requireContext())
        controller.calculatorBMI(object : CallBack {
            override fun onResult(bmi: Double, minIdealWeight: Double, maxIdealWeight: Double) {
                val resultFragment = ResultFragment()
                resultFragment.setData(
                    binding.spinnerSex.selectedItem.toString(),
                    binding.spinnerAge.selectedItem.toString(),
                    binding.txtDate.text.toString(),
                    bmi,
                    selectedHeight,
                    selectedWeight,
                    minIdealWeight,
                    maxIdealWeight
                )
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, resultFragment)
                    .addToBackStack(null)
                    .commit()
            }

            override fun onError(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupDatePicker() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        binding.txtDate.text = "$currentDay/$currentMonth/$currentYear"
        binding.imgCalendar.setOnClickListener {
            DatePickerDialog(requireContext(), { _, i, i2, i3 ->
                binding.txtDate.text = "$i3/${i2 + 1}/$i"
            }, currentYear, currentMonth, currentDay).show()
        }
    }

    private val weightList = (2..300).toList()
    private val heightList = (2..300).toList()
    private fun setupSpinners() {
        //setup cân nặng
        val weightAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            weightList
        )
        binding.spinnerWeight.adapter = weightAdapter

        //setup chiều cao
        val heightList = (2..300).toList()
        val heightAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            heightList
        )
        binding.spinnerHeight.adapter = heightAdapter

        //setup giới tính
        val sexList = resources.getStringArray(R.array.sex)
        val sexAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sexList)
        binding.spinnerSex.adapter = sexAdapter

        //setup tuổi
        val ageList = (2..100).toList()
        val ageAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, ageList)
        binding.spinnerAge.adapter = ageAdapter

        val defaultAge = 18
        val initialAge = ageList.indexOf(defaultAge)
        binding.spinnerAge.setSelection(initialAge)

        val suggestController = SuggestController(this)
        suggest = suggestController
        binding.spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedAge = ageList[position]
                val selectedGender = binding.spinnerSex.selectedItem.toString()
                suggest.setDefaultValuesBasedOnAge(selectedGender, selectedAge)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun setSelectedHeight(height: Int) {
        val heightIndex = heightList.indexOf(height)
        binding.spinnerHeight.setSelection(heightIndex)
    }

    fun setSelectedWeight(weight: Int) {
        val weightIndex = weightList.indexOf(weight)
        binding.spinnerWeight.setSelection(weightIndex)
    }
}
