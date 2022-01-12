package com.tyopo.verora.ui.game

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tyopo.verora.R
import com.tyopo.verora.databinding.ItemSlotBinding
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

class SlotView : FrameLayout {

    private val binding by lazy {
        ItemSlotBinding.bind(this)
    }

    constructor(context: Context) : super(context) {
        initSlot(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initSlot(context)
    }

    private fun initSlot(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.item_slot, this)
        binding.currentImage.setFirstSlot()
        binding.nextImage.setSlot(getNextSlotPosition())
    }

    suspend fun rollMultipleTimes(): Int {
        repeat(Configuration.NUMBER_OF_ROLLS) { iteration ->
            if (iteration % 2 == 0) {
                roll(binding.currentImage, binding.nextImage)
            } else {
                roll(binding.nextImage, binding.currentImage)
            }
        }
        return if (Configuration.NUMBER_OF_ROLLS % 2 == 0) binding.currentImage.slotValue else binding.nextImage.slotValue
    }

    private suspend fun roll(centerImage: SlotImageView, bottomImage: SlotImageView) =
        suspendCoroutine<Unit> { continuation ->
            centerImage.animate()
                .translationY(-height.toFloat())
                .setDuration(Configuration.SLOT_ANIM_DURATION).start()

            bottomImage.translationY = bottomImage.height.toFloat()

            bottomImage.animate().apply {
                translationY(0f)
                duration = Configuration.SLOT_ANIM_DURATION
                withEndAction {
                    centerImage.setSlot(getNextSlotPosition())
                    continuation.resume(Unit)
                }
                start()
            }
        }

    private fun getNextSlotPosition() = Random.nextInt(Int.MAX_VALUE) % Configuration.SLOTS_COUNT
}
