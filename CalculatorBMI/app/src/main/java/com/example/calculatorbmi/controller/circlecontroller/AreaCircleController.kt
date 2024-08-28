package com.example.calculatorbmi.controller.circlecontroller

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.calculatorbmi.controller.CalculatorArea

class AreaCircleController(private val radius: Double) {
    private lateinit var parentFragmentManager: FragmentManager
    private lateinit var context: Context

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.parentFragmentManager = fragmentManager
    }

    fun setContext(applicationContext: Context) {
        this.context = applicationContext
    }

    fun areaCircle(callback: Callback) {
        val rd = radius
        if (rd > 0) {
            val areaController = CalculatorArea()
            val result = areaController.areaCircle(rd)
            callback.onResult(result)
        } else {
            callback.onEror("Kích thước không hợp lệ ")
        }
    }
}

interface Callback {
    fun onResult(result: Double)
    fun onEror(masage: String)
}