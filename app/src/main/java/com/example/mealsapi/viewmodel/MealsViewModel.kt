package com.example.mealsapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapi.model.Meal
import com.example.mealsapi.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel que gestiona el estado de la receta actual y las interacciones de la UI.
 * 
 * @property repository Repositorio encargado de la obtención de datos de recetas.
 */
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val repository: MealsRepository
) : ViewModel() {

    // Estado interno de la receta actual
    private val _recipeState = MutableStateFlow<Meal?>(null)
    val recipeState: StateFlow<Meal?> = _recipeState.asStateFlow()

    // Estado para gestionar el indicador de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        // Carga inicial de una receta aleatoria al crear el ViewModel
        fetchRandomRecipe()
    }

    /**
     * Realiza una petición asíncrona para obtener una nueva receta aleatoria.
     */
    fun fetchRandomRecipe() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getMeals()
                _recipeState.value = response.meals.firstOrNull()
            } catch (e: Exception) {
                // Registro simple de errores en consola
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
