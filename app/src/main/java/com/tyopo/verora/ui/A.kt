package com.tyopo.verora.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tyopo.verora.R
import com.tyopo.verora.databinding.FrBinding
import com.tyopo.verora.ui.game.GameFragment
import com.tyopo.verora.util.autoCleanedVariable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class A : Fragment(R.layout.fr) {

    private val binding by autoCleanedVariable { FrBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnRoll.setOnClickListener {
                lifecycleScope.launch(Dispatchers.Main) {
                    val score = (childFragmentManager.findFragmentById(R.id.gameFragment) as GameFragment).roll()
                    tvScore.text = "SCORE: " + score.toString()
                }
            }
        }
    }
}
