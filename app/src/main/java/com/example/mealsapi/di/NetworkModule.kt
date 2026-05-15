package com.example.mealsapi.di

import com.example.mealsapi.api.MealsApi
import com.example.mealsapi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt para proveer dependencias relacionadas con la red.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provee una instancia única de Retrofit configurada con la URL base y conversor Gson.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provee la implementación de la interfaz de API generada por Retrofit.
     */
    @Provides
    @Singleton
    fun provideMealsApi(retrofit: Retrofit): MealsApi {
        return retrofit.create(MealsApi::class.java)
    }
}
