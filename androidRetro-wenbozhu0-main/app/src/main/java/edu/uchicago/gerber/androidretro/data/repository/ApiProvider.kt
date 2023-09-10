package edu.uchicago.gerber.androidretro.data.repository

import edu.uchicago.gerber.androidretro.common.DrinksConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    fun drinksApi(): DrinksApi {
        return Retrofit.Builder()
            .baseUrl(DrinksConstants.drinksUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DrinksApi::class.java)
    }

    fun mongoApi(): MongoApi {
        return Retrofit.Builder()
            //.baseUrl("http://10.0.2.2:8080/")
            .baseUrl("http://54.147.236.105:8080/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MongoApi::class.java)
    }

    fun emailApi(): EmailApi {
        return Retrofit.Builder()
            //.baseUrl("http://10.0.2.2:3000/")
            .baseUrl("http://xzyk2zj8d3.execute-api.us-east-1.amazonaws.com/Prod/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(EmailApi::class.java)
    }


    private fun getOkHttpClient() = OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build()

    //todo set HttpLoggingInterceptor.Level.BODY to HttpLoggingInterceptor.Level.NONE for production release
    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

}