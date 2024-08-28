package com.example.calculatorbmi.controller.bmicontroller

import com.example.calculatorbmi.fragment.bmi.CalculatorBmiFragment

class SuggestController(private val view: CalculatorBmiFragment) {
    fun setDefaultValuesBasedOnAge(genre: String, selectedAge: Int) {
        if (genre == "Male") {
            when {
                selectedAge < 5 -> {
                    view.setSelectedHeight(80)
                    view.setSelectedWeight(20)
                }

                selectedAge in (6..10) -> {
                    view.setSelectedHeight(110)
                    view.setSelectedWeight(30)
                }

                selectedAge in (11..15) -> {
                    view.setSelectedHeight(150)
                    view.setSelectedWeight(40)
                }

                selectedAge in (16..20) -> {
                    view.setSelectedHeight(160)
                    view.setSelectedWeight(50)
                }

                selectedAge > 20 -> {
                    view.setSelectedHeight(165)
                    view.setSelectedWeight(55)
                }
            }
        } else if (genre == "FeMale") {
            when {
                selectedAge < 5 -> {
                    view.setSelectedHeight(70)
                    view.setSelectedWeight(20)
                }

                selectedAge in (6..10) -> {
                    view.setSelectedHeight(110)
                    view.setSelectedWeight(30)
                }

                selectedAge in (11..15) -> {
                    view.setSelectedHeight(145)
                    view.setSelectedWeight(35)
                }

                selectedAge in (16..20) -> {
                    view.setSelectedHeight(150)
                    view.setSelectedWeight(45)
                }

                selectedAge > 20 -> {
                    view.setSelectedHeight(155)
                    view.setSelectedWeight(50)
                }
            }
        }
    }
}