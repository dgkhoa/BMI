package com.example.calculatorbmi.controller.trianglecontroller

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.calculatorbmi.controller.CalculatorArea

class AreaTriangleController(
    val side1: Double,
    val side2: Double,
    val side3: Double
) {
    private lateinit var parentFragmentManager: FragmentManager
    private lateinit var context: Context

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.parentFragmentManager = fragmentManager
    }

    fun setContext(applicationContext: Context) {
        this.context = applicationContext
    }

    fun areaTriangle(callback: Callback) {
        val sideEnter1 = side1
        val sideEnter2 = side2
        val sideEnte3 = side3

        if (sideEnter1 > 0 && sideEnter2 > 0 && sideEnte3 > 0) {
            val controller = CalculatorArea()
            val result = controller.areaTriangle(sideEnter1, sideEnter2, sideEnte3)
            callback.onResult(result)
        } else {
            callback.onError("Kích thước không hợp lệ")
        }
    }
}

interface Callback {
    fun onResult(result: Double)
    fun onError(masage: String)
}