package com.example.mealsapi.repository

import com.example.mealsapi.api.MealsApi
import com.example.mealsapi.model.Meals
import javax.inject.Inject

/**
 * Repositorio encargado de mediar entre el origen de datos (API) y el ViewModel.
 * 
 * @property mealsApi Interfaz de servicio de red inyectada.
 */
class MealsRepository @Inject constructor(
    private val mealsApi: MealsApi
) {
    /**
     * Recupera una lista de recetas (normalmente una aleatoria) desde la API.
     */
    suspend fun getMeals(): Meals {
        return mealsApi.getMeals()
    }
}
