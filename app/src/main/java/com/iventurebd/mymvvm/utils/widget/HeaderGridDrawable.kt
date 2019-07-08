package com.iventurebd.mymvvm.utils.widget

/**
 * Custom [Drawable] for drawing a grid pattern. We use this rather than tiling a pattern in order
 * to have greater control; specifically we always want a horizontal grid line along the bottom of
 * the drawable.
 */
/*class HeaderGridDrawable(context: Context) : Drawable() {

    private val paint: Paint
    private val gridSize: Int
    private val halfStrokeWidth: Float

    init {
        val attrs = context.obtainStyledAttributes(
            R.style.Widget_IOSched_HeaderGrid,
            R.styleable.HeaderGridDrawable
        )
        paint = Paint().apply {
            color = attrs.getColorOrThrow(R.styleable.HeaderGridDrawable_android_color)
            strokeWidth = attrs.getDimensionOrThrow(R.styleable.HeaderGridDrawable_gridStrokeWidth)
        }
        halfStrokeWidth = paint.strokeWidth / 2f
        gridSize = attrs.getDimensionPixelSizeOrThrow(R.styleable.HeaderGridDrawable_gridSize)
        attrs.recycle()
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        val height = bounds.height().toFloat()
        if (height == 0f) return

        val left = bounds.left.toFloat()
        val top = bounds.top.toFloat()
        val right = bounds.right.toFloat()
        val bottom = bounds.bottom.toFloat()
        val verticalSteps = Math.floor((bounds.height() / gridSize).toDouble()).toInt()
        val horizontalSteps = Math.floor((bounds.width() / gridSize).toDouble()).toInt()

        // Always draw a hz line at the very top.
        val lines = mutableListOf(left, top + halfStrokeWidth, right, top + halfStrokeWidth)

        // Draw hz lines from bottom to top
        for (i in 0..verticalSteps) {
            val y = height - halfStrokeWidth - (gridSize * i).toFloat()
            lines += left
            lines += y
            lines += right
            lines += y
        }

        for (i in 0..horizontalSteps) {
            val x = (gridSize * i).toFloat() - halfStrokeWidth
            lines += x
            lines += top
            lines += x
            lines += bottom
        }

        canvas.drawLines(lines.toFloatArray(), paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity() = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}*/
