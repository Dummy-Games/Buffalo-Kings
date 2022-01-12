package com.tyopo.verora.ui.game

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tyopo.verora.util.suspendMap
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    private val slots = ArrayList<ArrayList<SlotView>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootLayout = LinearLayoutCompat(requireContext())
        rootLayout.orientation = LinearLayoutCompat.VERTICAL
        rootLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        rootLayout.gravity = Gravity.CENTER
        repeat(Configuration.NUMBER_OF_ROWS) {
            val rowLayout = LinearLayoutCompat(requireContext())
            rowLayout.orientation = LinearLayoutCompat.HORIZONTAL
            rowLayout.layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f).apply {
                gravity = Gravity.CENTER
            }
            val slotsRow = ArrayList<SlotView>()
            repeat(Configuration.NUMBER_OF_COLUMNS) {
                val slotView = SlotView(requireContext())
                slotView.layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f).apply {
                    topMargin = 16
                    bottomMargin = 16
                }
                rowLayout.addView(slotView)
                slotsRow.add(slotView)
            }
            rootLayout.addView(rowLayout)
            slots.add(slotsRow)
        }
        return rootLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            lifecycleScope.launch {
                roll()
            }
        }
    }

    suspend fun roll(): Int {
        return slots.suspendMap { slotsRow ->
            slotsRow.suspendMap {
                it.rollMultipleTimes()
            }.reduce { acc, i -> acc + i }
        }.reduce { acc, i -> acc + i }
    }
}
