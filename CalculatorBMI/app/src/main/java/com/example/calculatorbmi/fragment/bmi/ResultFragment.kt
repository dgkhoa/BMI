package com.example.calculatorbmi.fragment.bmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.calculatorbmi.R
import com.example.calculatorbmi.controller.bmicontroller.ResultController
import com.example.calculatorbmi.databinding.FragmentResultBinding
import com.example.calculatorbmi.model.BMIHistory

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var binding: FragmentResultBinding
    private var sex: String? = null
    private var age: String? = null
    private var date: String? = null
    private var bmi: Double? = null
    private var height: Double? = null
    private var weight: Double? = null
    private var minIdealWeight: Double? = null
    private var maxIdealWeight: Double? = null
    private lateinit var controller: ResultController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = ResultController()
        receiveData()

        val cardView = view.findViewById<CardView>(R.id.cardViewStatus)
        val status = view.findViewById<TextView>(R.id.txt_sttBmi)

        //lưu dữ thông tin vào history
        if (bmi != null && date != null) {
            BMIHistory.addHistory(bmi!!, date!!, cardView, status)
        }

        binding.imgBack.setOnClickListener {
            val calculatorBmiFragment = CalculatorBmiFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, calculatorBmiFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.txtHistory.setOnClickListener {
            val historyFragment = HistoryFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, historyFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    fun setData(
        sex: String, age: String, date: String, bmi: Double, height: Double,
        weight: Double, minIdealWeight: Double, maxIdealWeight: Double
    ) {
        this.sex = sex
        this.age = age
        this.date = date
        this.bmi = bmi
        this.height = height
        this.weight = weight
        this.minIdealWeight = minIdealWeight
        this.maxIdealWeight = maxIdealWeight
        receiveData()
    }

    private fun receiveData() {
        if (this@ResultFragment::binding.isInitialized) {
            binding.txtResultBmi.text = bmi.toString()
            binding.txtHeight.text = height.toString()
            binding.txtWeight.text = weight.toString()
            binding.txtMinIdealWeight.text = minIdealWeight.toString()
            binding.txtMaxIdealWeight.text = maxIdealWeight.toString()
            binding.txtDateResult.text = date
            binding.txtSex.text = sex
            binding.txtAge.text = age

            val resultInfo = controller.getColorAndTextForStatus(bmi ?: 0.0)
            binding.cardViewStatus.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    resultInfo.colorResId
                )
            )
            binding.txtSttBmi.text = getString(resultInfo.textResId)

            val notificationText = controller.getNotificationText(bmi ?: 0.0, requireContext())
            binding.txtNotification.text = notificationText

            val visibility = controller.getIdWeightVisibility(bmi ?: 0.0)
            binding.linearLayoutIdeal.visibility = visibility
        }
    }
}
