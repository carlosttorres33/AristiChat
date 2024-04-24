package com.carlostorres.aristichat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlostorres.aristichat.R
import com.carlostorres.aristichat.databinding.FragmentChatBinding
import com.carlostorres.aristichat.domain.model.MessageModel
import com.carlostorres.aristichat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding : FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_back)
        }

        setUpUI()

        binding.btnSendMsg.setOnClickListener {
            viewModel.sendMessage()
        }

        return binding.root

    }

    private fun setUpUI(){

        setUpMessages()

        subscribeToMessage()

    }

    private fun setUpMessages() {

        chatAdapter = ChatAdapter(mutableListOf(), "aris")

        binding.rvMessage.apply {

            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)

        }

    }

    private fun subscribeToMessage(){

        lifecycleScope.launch {

            viewModel.messageList.collect(){

                chatAdapter.updateList(it.toMutableList())
                binding.rvMessage.scrollToPosition(chatAdapter.messageList.size - 1)

            }

        }

    }

}