package com.mack.fasttrack.Graph

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DonutChartBmi(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint: Paint = Paint()
    private var bmiValue = 0f

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Set the donut color
        paint.color = Color.GRAY

        // Draw the entire donut circle during the animation
        drawDonut(canvas, 0f, 360f)

        // Draw the colored arc based on the animated BMI value
        val progressColor = getBmiColor(bmiValue)
        paint.color = progressColor
        drawDonut(canvas, 180f, bmiValue * 3.6f)
    }

    private fun drawDonut(canvas: Canvas?, startAngle: Float, sweepAngle: Float) {
        // Draw the entire donut
        val centerX = width / 2
        val centerY = height / 2
        val radius = centerX.coerceAtMost(centerY) - 50

        canvas!!.drawArc(
            (centerX - radius).toFloat(),
            (centerY - radius).toFloat(),
            (centerX + radius).toFloat(),
            (centerY + radius).toFloat(),
            startAngle,
            sweepAngle,
            false,
            paint
        )
    }
    private fun getBmiColor(bmiValue: Float): Int {
        return when {
            bmiValue > 0 && bmiValue < 18.5 -> Color.YELLOW
            bmiValue > 18.5 && bmiValue < 24.9 -> Color.GREEN
            bmiValue > 24.9 && bmiValue < 29.9 -> Color.MAGENTA
            else -> Color.RED
        }
    }

    fun setBmiValue(bmiValue: Float) {
        this.bmiValue = bmiValue
        invalidate()
    }

}
