package com.canalperenozcan.movieassistant.common

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.google.android.material.textfield.TextInputEditText

class CustomTextInputEditText : TextInputEditText {
    private var onLeftDrawableClickListener: (() -> Unit)? = null
    private var onRightDrawableClickListener: (() -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val isRtl = layoutDirection == View.LAYOUT_DIRECTION_RTL
                val drawableLeft = if (isRtl) compoundDrawables[2] else compoundDrawables[0] // Left drawable
                val drawableRight = if (isRtl) compoundDrawables[0] else compoundDrawables[2] // Right drawable

                // Left drawable click check
                if (drawableLeft != null && event.rawX <= paddingLeft + drawableLeft.bounds.width()) {
                    onLeftDrawableClickListener?.invoke()
                    performClick()
                    return@setOnTouchListener true
                }

                // Right drawable click check
                if (drawableRight != null && event.rawX >= right - drawableRight.bounds.width()) {
                    onRightDrawableClickListener?.invoke()
                    performClick()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }

    fun setOnLeftDrawableClickListener(listener: () -> Unit) {
        onLeftDrawableClickListener = listener
    }

    fun setOnRightDrawableClickListener(listener: () -> Unit) {
        onRightDrawableClickListener = listener
    }
}
