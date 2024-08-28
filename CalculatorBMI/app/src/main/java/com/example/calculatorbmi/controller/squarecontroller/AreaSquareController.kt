package com.example.calculatorbmi.controller.squarecontroller

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.calculatorbmi.controller.CalculatorArea

class AreaSquareController(
    private val edtSide: Double
) {
    private lateinit var parentFragmentManager: FragmentManager
    private lateinit var context: Context

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.parentFragmentManager = fragmentManager
    }

    fun setContext(applicationContext: Context) {
        this.context = applicationContext
    }

    fun areaSquare(callback: CallBack) {
        val side = edtSide
        if (side > 0) {
            val areaSquare = CalculatorArea()
            val result = areaSquare.areaSquare(side)
            callback.onResult(result)
        } else {
            callback.onError("Cân nặng hoặc chiều cao không hợp lệ")
        }
    }
}

interface CallBack {
    fun onResult(result: Double)
    fun onError(message: String)
}