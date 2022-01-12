package com.tyopo.verora.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tyopo.verora.R
import com.tyopo.verora.databinding.GhBinding
import com.tyopo.verora.util.autoCleanedVariable

class Base : Fragment(R.layout.gh) {

    private val binding by autoCleanedVariable { GhBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnAbout.setOnClickListener {
                findNavController().navigate(R.id.aboutFragment)
            }

            btnPlay.setOnClickListener {
                findNavController().navigate(R.id.gameFragment)
            }
        }
    }
}
