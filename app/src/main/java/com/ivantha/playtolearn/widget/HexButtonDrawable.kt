package com.ivantha.playtolearn.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.drawable.Drawable

class HexButtonDrawable(private val context: Context) : Drawable() {

    private val numberOfSides = 6
    private val pLarge = Path()
    private val pSmall = Path()
    private val temporal = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val ashWhiteColor = Color.parseColor("#E6E6E6")
    private val greenColor = Color.parseColor("#004D40")

    private var bmpScaled: Bitmap? = null
    private val bmpWidth = 150
    private val bmpHeight = 150

    init {
        pLarge.fillType = Path.FillType.EVEN_ODD
    }

    override fun draw(canvas: Canvas) {
        paint.color = ashWhiteColor
        canvas.drawPath(pLarge, paint)
        paint.color = greenColor
        canvas.drawPath(pSmall, paint)

        if (bmpScaled != null) {
            canvas.drawBitmap(bmpScaled!!, ((canvas.width - bmpWidth) / 2).toFloat(), ((canvas.height - bmpHeight) / 5).toFloat(), null)
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return paint.alpha
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        computeHex(bounds)
        invalidateSelf()
    }

    fun setIcon(drawable: Int) {
        val bmp_original = BitmapFactory.decodeResource(context.resources, drawable)
        bmpScaled = Bitmap.createScaledBitmap(bmp_original, bmpWidth, bmpHeight, false)
    }

    private fun computeHex(bounds: Rect) {
        val width = bounds.width()
        val height = (bounds.height() * 0.7).toInt()
        val size = Math.min(width, height)
        val centerX = bounds.left + width / 2
        val centerY = bounds.top + height / 2

        pLarge.reset()

        pLarge.addPath(createHexagon(size, centerX, centerY))
        pSmall.addPath(createHexagon((size * 0.9).toInt(), centerX, centerY))
    }

    private fun createHexagon(size: Int, centerX: Int, centerY: Int): Path {
        val section = (2.0 * Math.PI / numberOfSides).toFloat()
        val radius = size / 2
        val polygonPath = temporal
        polygonPath.reset()
        polygonPath.moveTo((centerX + radius * Math.cos(0.0)).toFloat(),
                (centerY + radius * Math.sin(0.0)).toFloat())

        for (i in 1 until numberOfSides) {
            polygonPath.lineTo((centerX + radius * Math.cos((section * i).toDouble())).toFloat(),
                    (centerY + radius * Math.sin((section * i).toDouble())).toFloat())
        }

        polygonPath.close()
        return polygonPath
    }
}
