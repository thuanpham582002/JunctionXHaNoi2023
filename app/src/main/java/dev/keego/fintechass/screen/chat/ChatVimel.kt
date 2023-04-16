package dev.keego.fintechass.screen.chat

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keego.fintechass.screen.chat.jsonobject.InputData
import dev.keego.fintechass.setup.room.Message
import dev.keego.fintechass.setup.room.RoomChat
import dev.keego.fintechass.setup.room.RoomRepository
import dev.keego.fintechass.state.State
import dev.keego.fintechass.state.VimelStateHolder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ChatVimel @Inject constructor(private val roomRepository: RoomRepository) :
    VimelStateHolder<ChatVimel.ChatState>(ChatState()) {

    data class ChatState(
        var roomChat: RoomChat? = null,
        var currentUserId: Int = 0,
    ) : State

    fun init(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val roomChat = roomRepository.getRoomChat(id)
            update {
                it.copy(roomChat = roomChat)
            }
        }
    }

    fun switchUser(id: Int) {
        update {
            it.copy(currentUserId = id)
        }
    }

    fun insertMessage(message: Message) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        }

        val url = "http://10.0.2.2:5005/model/parse"
        val body =
            "{\"text\":\"${message.message}\"}"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(body.toRequestBody("application/json".toMediaType()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val inputData = Json.decodeFromString<InputData>(responseBody!!)
                Log.i("MainActivity", "onResponse: ${inputData.entities}")
                for (entity in inputData.entities) {
                    Log.i("MainActivity", "onResponse: ${entity.entity}")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity", "onFailure: ", e)
            }
        })


        viewModelScope.launch(Dispatchers.IO) {
            val roomChat = state.value.roomChat
            if (roomChat != null) {
                val messages = roomChat.messages.toMutableList()
                messages.add(message)
                update { it.copy(roomChat = roomChat.copy(messages = messages)) }
                roomRepository.addMessage(roomChat.id, messages)
            }
        }
    }
}