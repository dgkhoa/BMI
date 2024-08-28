package com.example.calculatorbmi.controller.rectanglecontroller

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.calculatorbmi.controller.CalculatorArea

class AreaRectangleController(
    private val width: Double,
    private val height: Double
) {
    private lateinit var parentFragmentManager: FragmentManager
    private lateinit var context: Context

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.parentFragmentManager = fragmentManager
    }

    fun setContext(applicationContext: Context) {
        this.context = applicationContext
    }

    fun areaRectangle(callback: Callback) {
        val widthEnter = width
        val heightEnter = height
        if (widthEnter > 0 && heightEnter > 0) {
            val areaRectangle = CalculatorArea()
            val result = areaRectangle.areaRectangle(widthEnter, heightEnter)
            callback.onResult(result)
        } else {
            callback.onError("Kích thước không hợp lệ")
        }
    }
}

interface Callback {
    fun onResult(result: Double)
    fun onError(message: String)
}