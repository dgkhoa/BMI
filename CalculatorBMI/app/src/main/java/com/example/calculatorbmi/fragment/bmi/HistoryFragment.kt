package com.example.calculatorbmi.fragment.bmi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculatorbmi.R
import com.example.calculatorbmi.adapter.BmiHistoryAdapter
import com.example.calculatorbmi.controller.bmicontroller.ResultController
import com.example.calculatorbmi.databinding.FragmentHistoryBinding
import com.example.calculatorbmi.model.BMIHistory

class HistoryFragment : Fragment(R.layout.fragment_history) {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyAdapter: BmiHistoryAdapter
    private lateinit var resultController: ResultController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)
        resultController = ResultController()

        // Lấy danh sách lịch sử từ BMIHistory
        val historyList = BMIHistory.getHistory()

        // Khởi tạo và thiết lập RecyclerView
        historyAdapter = BmiHistoryAdapter(historyList, resultController)
        binding.rvcHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvcHistory.adapter = historyAdapter

        binding.imgBack.setOnClickListener {
            val resultFragment = ResultFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, resultFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}