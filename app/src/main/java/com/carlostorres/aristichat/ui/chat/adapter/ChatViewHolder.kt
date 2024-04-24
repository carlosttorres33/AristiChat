package com.carlostorres.aristichat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.carlostorres.aristichat.databinding.ItemCatMeBinding
import com.carlostorres.aristichat.databinding.ItemChatOthersBinding
import com.carlostorres.aristichat.domain.model.MessageModel

class ChatViewHolder(
    private val binding : ViewBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(messageModel: MessageModel, itemViewType: Int) {

        when(itemViewType){

           ChatAdapter.SENT_MSG -> bindSentMessage(messageModel)
           ChatAdapter.RECEIVED_MSG -> bindReceivedMessage(messageModel)

        }

    }

    private fun bindReceivedMessage(messageModel: MessageModel) {

        val currentBinding = binding as ItemChatOthersBinding

        currentBinding.tvDateOther.text = messageModel.date
        currentBinding.tvChatOther.text = messageModel.msg
        currentBinding.tvName.text = messageModel.user.userName
        currentBinding.tvHour.text = messageModel.hour

    }

    private fun bindSentMessage(messageModel: MessageModel) {

        val currentBinding = binding as ItemCatMeBinding

        currentBinding.tvChatMe.text = messageModel.msg
        currentBinding.tvDateMe.text = messageModel.date
        currentBinding.tvHour.text = messageModel.hour

    }

}