package com.example.mealsapi.api

import com.example.mealsapi.model.Meals
import retrofit2.http.GET

/**
 * Interfaz de Retrofit que define los endpoints disponibles en la API de TheMealDB.
 */
interface MealsApi {
    /**
     * Obtiene una receta aleatoria desde el endpoint random.php.
     */
    @GET("api/json/v1/1/random.php")
    suspend fun getMeals(): Meals
}
