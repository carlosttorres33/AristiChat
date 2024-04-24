package com.carlostorres.aristichat.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.aristichat.databinding.ItemCatMeBinding
import com.carlostorres.aristichat.databinding.ItemChatOthersBinding
import com.carlostorres.aristichat.domain.model.MessageModel

class ChatAdapter (
    var messageList : MutableList<MessageModel>,
    private var userName : String = ""
) : RecyclerView.Adapter<ChatViewHolder>() {

    companion object{
        const val SENT_MSG = 0
        const val RECEIVED_MSG = 1
    }

    override fun getItemCount(): Int = messageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val binding = when(viewType){

            SENT_MSG ->{
                ItemCatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            RECEIVED_MSG -> {
                ItemChatOthersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            else -> {
                ItemCatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

        }

        return ChatViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        holder.bind(messageList[position], getItemViewType(position))

    }

    override fun getItemViewType(position: Int): Int{

        return if (messageList[position].user.userName == userName){
            SENT_MSG
        }else{
            RECEIVED_MSG
        }

    }

    fun updateList(list: MutableList<MessageModel>, name: String){
        userName = name
        messageList.clear()
        messageList.addAll(list)
        notifyItemInserted(messageList.size - 1)

    }

}