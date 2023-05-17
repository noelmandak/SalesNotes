package com.example.salesnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.salesnotes.databinding.FragmentOrderBinding

class Order : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = 
        return inflater.inflate(R.layout.fragment_order, container, false)
    }
}