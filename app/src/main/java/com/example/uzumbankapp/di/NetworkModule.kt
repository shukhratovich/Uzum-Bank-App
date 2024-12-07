package com.example.uzumbankapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.uzumbankapp.data.source.remote.api.auth.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @[Provides Singleton]
    fun provideChucker(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context).build()

    @[Provides Singleton]
    fun provideOkHttp(chuckerInterceptor: ChuckerInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .build()

    @[Provides Singleton]
    fun provideApiClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://195.158.16.140/mobile-bank/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}