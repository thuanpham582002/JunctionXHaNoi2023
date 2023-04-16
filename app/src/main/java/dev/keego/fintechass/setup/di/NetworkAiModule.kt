package dev.keego.fintechass.setup.di

object NetworkAiModule {
    val baseUrl = "https://10.0.2.2:5005"

    val nluApi = retrofit2.Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build().create(NLUApi::class.java)
}