package com.winqoo.wikia.di.modules

import android.content.Context
import com.google.gson.Gson
import com.winqoo.wikia.BuildConfig
import com.winqoo.wikia.di.qualifier.NoInterceptor
import com.winqoo.wikia.service.api.WikiaApi
import com.winqoo.wikia.service.common.NetworkConnectionInterceptor
import com.winqoo.wikia.service.repository.WikiaRepository
import com.winqoo.wikia.service.repository.WikiaRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module
class NetworkModule {

    companion object {
        fun provideOkHttpBuilder(): OkHttpClient.Builder {
            return OkHttpClient.Builder()
        }
    }

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    @NoInterceptor
    fun provideNoInterceptorLogInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    @Provides
    @Singleton
    fun okHttpClient(logInterceptor: HttpLoggingInterceptor, networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        val client = provideOkHttpBuilder()
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)
        client.addInterceptor(logInterceptor)
        client.addInterceptor(networkConnectionInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideLogInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionInterceptor(context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }


    @Singleton
    @Provides
    fun provideWikiaApi(retrofit: Retrofit): WikiaApi {
        return retrofit.create<WikiaApi>(WikiaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWikiaRepository(retrofit : Retrofit): WikiaRepository {
        return WikiaRepositoryImpl(retrofit.create<WikiaApi>(WikiaApi::class.java))
    }

}