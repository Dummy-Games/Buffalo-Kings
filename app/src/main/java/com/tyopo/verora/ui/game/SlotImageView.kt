package com.tyopo.verora.ui.game

import android.content.Context
import android.util.AttributeSet
import com.tyopo.verora.util.getDrawableId

class SlotImageView : androidx.appcompat.widget.AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var slotValue = 0

    fun setSlot(value: Int) {
        slotValue = value
        setImageResource(getDrawableId(getSlotName(slotValue)))
    }

    fun setFirstSlot() = setSlot(FIRST_SLOT_VALUE)

    companion object {
        private const val SLOT_ID_PREFIX = "s"

        const val FIRST_SLOT_VALUE = 0

        private fun getSlotName(position: Int) = SLOT_ID_PREFIX + (position + 1)
    }
}
