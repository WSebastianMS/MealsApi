# MealsApi - Aplicación de Recetas Aleatorias

MealsApi es una aplicación de Android moderna que permite a los usuarios descubrir nuevas recetas de cocina de forma aleatoria, obteniendo detalles completos como ingredientes, instrucciones paso a paso y un tutorial en video.

## 🚀 Características

- **Recetas Aleatorias:** Genera una nueva receta con un solo clic.
- **Detalles Completos:** Visualización de imágenes, lista de ingredientes con sus medidas y guía de preparación.
- **Integración con YouTube:** Acceso directo al video tutorial de la receta.
- **Diseño Moderno:** Interfaz construida íntegramente con **Jetpack Compose**.
- **Arquitectura Robusta:** Implementación de **Clean Architecture** para una mejor mantenibilidad y escalabilidad.

## 🛠️ Tecnologías Utilizadas

- **Kotlin:** Lenguaje de programación principal.
- **Jetpack Compose:** Toolkit moderno para la construcción de UI nativa.
- **Dagger Hilt:** Framework para la inyección de dependencias.
- **Retrofit & Gson:** Consumo de APIs REST y serialización de datos.
- **Coil:** Librería para la carga de imágenes asíncronas.
- **Kotlin Coroutines & Flow:** Gestión de tareas asíncronas y flujos de datos reactivos.

## 📂 Estructura del Proyecto

El proyecto sigue una estructura de paquetes organizada por capas:

- `api`: Definición de interfaces para el consumo de servicios web (Retrofit).
- `di`: Módulos de configuración para la inyección de dependencias con Hilt.
- `model`: Clases de datos (POJOs) que representan las entidades del dominio.
- `repository`: Implementación del patrón Repository para el manejo de datos.
- `ui`: Componentes de interfaz de usuario y temas visuales (Compose).
- `viewmodel`: Gestión del estado de la UI y lógica de negocio.
- `utils`: Constantes y utilidades generales.

## ⚙️ Requisitos y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/WSebastianMS/MealsApi.git
   ```
2. Abrir el proyecto en **Android Studio (Ladybug o superior)**.
3. Sincronizar el proyecto con los archivos Gradle.
4. Ejecutar la aplicación en un emulador o dispositivo físico con Android 7.0 (API 24) o superior.

---
Desarrollado como proyecto educativo para la exploración de APIs y arquitecturas modernas en Android.
