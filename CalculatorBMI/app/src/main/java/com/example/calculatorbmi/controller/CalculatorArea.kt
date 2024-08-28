package com.example.calculatorbmi.controller

import kotlin.math.sqrt

class CalculatorArea {
    fun areaSquare(side: Double): Double {
        val result = side * side
        return kotlin.math.round(result * 10.0) / 10.0
    }

    fun areaRectangle(width: Double, height: Double): Double {
        val result = width * height
        return kotlin.math.round(result * 10.0) / 10.0
    }

    fun areaCircle(radius: Double): Double {
        val result = Math.PI * radius * radius
        return kotlin.math.round(result * 10.0) / 10.0
    }

    fun areaTriangle(side1: Double, side2: Double, side3: Double): Double {
        val s = (side1 + side2 + side3) / 2
        val result = sqrt(s * (s - side1) * (s - side2) * (s - side3))
        return kotlin.math.round(result * 10.0) / 10.0
    }
}