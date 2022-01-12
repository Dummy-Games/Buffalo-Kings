package com.tyopo.verora.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tyopo.verora.R
import com.tyopo.verora.databinding.BgBinding
import com.tyopo.verora.util.autoCleanedVariable

class B : Fragment(R.layout.bg) {

    private val binding by autoCleanedVariable { BgBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
