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
    private var animatedBmiValue = 0f
    private var animator: ValueAnimator? = null

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

        // Draw BMI labels
//        drawBmiLabels(canvas)

        // Draw the text label with animated BMI value
//        drawBmiLabel(canvas!!, bmiValue)
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

    private fun drawBmiLabels(canvas: Canvas?) {
        // Set text color
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.textSize = 50f

        val centerX = width / 2
        val centerY = height / 2
        val radius = centerX.coerceAtMost(centerY) - 50

        drawLabel(canvas!!, "Underweight", centerX - radius.toFloat(), centerY + 50.toFloat())
        drawLabel(canvas, "Normal Weight", centerX - 50.toFloat(), centerY - radius + 30.toFloat())
        drawLabel(
            canvas,
            "Overweight",
            centerX + radius - 90.toFloat(),
            centerY + 50.toFloat()
        )
        drawLabel(canvas, "Obese", centerX - 50.toFloat(), centerY + radius - 10.toFloat())
    }

    private fun drawLabel(canvas: Canvas, text: String, x: Float, y: Float) {
        canvas.drawText(text, x, y, paint)
    }

    private fun drawBmiLabel(canvas: Canvas, bmiValue: Float) {
        val label = bmiValue.toString()

        // Set text color
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL

        // Set text size
        paint.textSize = 60f

        // Calculate position for the label
        val labelX = width / 2f
        val labelY = height / 2f

        // Draw label text
        canvas.drawText(label, labelX - paint.measureText(label) / 2, labelY, paint)
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

    fun setBmiValueWithAnimation(bmiValue: Float) {
        // Cancel any ongoing animation
        animator?.cancel()

        // Start a new animation
        animator = ValueAnimator.ofFloat(animatedBmiValue, bmiValue).apply {
            duration = 1000 // Animation duration in milliseconds
            addUpdateListener { animation ->
                animatedBmiValue = animation.animatedValue as Float
                invalidate()
            }
            start()
        }
    }
}
