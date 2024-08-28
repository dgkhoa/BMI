package com.example.calculatorbmi.controller.bmicontroller

import android.content.Context
import android.view.View
import com.example.calculatorbmi.R

enum class BMIStatus(val colorResId: Int, val textResId: Int) {
    UNDERWEIGHT_I(R.color.lightblue, R.string.underWeight),
    UNDERWEIGHT_II(R.color.darkblue, R.string.underWeight1),
    UNDERWEIGHT_III(R.color.purple, R.string.underWeight2),
    NORMAL(R.color.green, R.string.normal),
    PRE_OBESE(R.color.yellow, R.string.preObese),
    OBESE_CLASS_I(R.color.orange, R.string.obese1),
    OBESE_CLASS_II(R.color.orangeBrown, R.string.obese2),
    OBESE_CLASS_III(R.color.red, R.string.obese3)
}

class ResultInfo(val colorResId: Int, val textResId: Int)

class ResultController {
    fun getColorAndTextForStatus(bmi: Double): ResultInfo {
        val bmiStatus = getBMIStatus(bmi)
        val colorResId = bmiStatus.colorResId
        val textResId = bmiStatus.textResId
        return ResultInfo(colorResId, textResId)
    }

    fun getNotificationText(bmi: Double, context: Context): String {
        val bmiStatus = getBMIStatus(bmi)
        if (bmiStatus == BMIStatus.NORMAL) {
            return context.resources.getString(R.string.notification)
        }
        return context.resources.getString(R.string.notification1)
    }

    fun getIdWeightVisibility(bmi: Double): Int {
        val bmiStatus = getBMIStatus(bmi)
        return if (bmiStatus == BMIStatus.NORMAL) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    //set color theo kết quả trả về của bmi
    private fun getBMIStatus(bmi: Double): BMIStatus {
        return when {
            bmi < 16 -> BMIStatus.UNDERWEIGHT_III
            bmi in 16.0..16.9 -> BMIStatus.UNDERWEIGHT_II
            bmi in 17.0..18.4 -> BMIStatus.UNDERWEIGHT_I
            bmi in 18.5..24.9 -> BMIStatus.NORMAL
            bmi in 25.0..29.9 -> BMIStatus.PRE_OBESE
            bmi in 30.0..34.9 -> BMIStatus.OBESE_CLASS_I
            bmi in 30.0..34.9 -> BMIStatus.OBESE_CLASS_I
            bmi in 35.0..39.9 -> BMIStatus.OBESE_CLASS_II
            else -> BMIStatus.OBESE_CLASS_III
        }
    }
}