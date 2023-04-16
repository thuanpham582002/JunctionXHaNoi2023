package dev.keego.fintechass.setup.di

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NLUApi {
    @POST("/model/parse")
    suspend fun getNLUResponse(
        @Query("text") text: String = "",
    ): Call<ResponseBody>
}