package com.route.newsapplicationc32.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    companion object {
        val API_KEY = "5909ae28122a471d8b0c237d5989cb73"
        var LANGUAGE = "en"
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.e("api", message)
                    }
                })
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(AuthInterceptor())
                    .addInterceptor(LanguageInterceptor())
                    .build()

                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit ?: Retrofit.Builder().build()
        }

        fun webServices(): WebServices {
            return getInstance().create(WebServices::class.java)
        }
    }
}

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        /*  val url = chain.request()
              .url.newBuilder().addQueryParameter("apiKey", ApiManager.API_KEY).build()
          val request = chain.request().newBuilder().url(url).build();
          return chain.proceed(request);*/


        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer " + ApiManager.API_KEY)
            .build()


        return chain.proceed(request)
    }
}

class LanguageInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
            .url.newBuilder().addQueryParameter("language", ApiManager.LANGUAGE).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)


/*
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept-Language","en")
            .build()
*/


        return chain.proceed(request)
    }
}