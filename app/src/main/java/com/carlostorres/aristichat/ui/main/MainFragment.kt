package com.carlostorres.aristichat.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.carlostorres.aristichat.R
import com.carlostorres.aristichat.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.btnChat.setOnClickListener {

            if (!binding.tietName.text.isNullOrEmpty()) {

                findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)

            }

        }

        return binding.root

    }

}