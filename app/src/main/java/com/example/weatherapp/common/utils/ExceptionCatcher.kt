package com.example.weatherapp.common.utils

object Errors {
    const val incorrectCity = "java.lang.IndexOutOfBoundsException: Index: 0, Size: 0"
    const val emptyData = "Index: 0, Size: 0"
    const val connectionTrouble =
        "Unable to resolve host \"api.openweathermap.org\": No address associated with hostname"
    const val timeoutError = "timeout"
}

object Solutions {
    const val inputSolution = "Город не найден. Проверьте правильность ввода города."
    const val connectionSolution = "Проблема с соединением. Проверьте подключение к интернету."
    const val unknownError = "Неизвестная ошибка. Повторите попытку позже."
}

object ExceptionCatcher {

    fun getErrorMessage(exception: Exception): String {
        when {
            exception.message.toString()
                .contains(Errors.incorrectCity) -> {
                return Solutions.inputSolution
            }
            exception.message.toString()
                .contains(Errors.connectionTrouble, ignoreCase = true) -> {
                return Solutions.connectionSolution
            }
            exception.message.toString()
                .contains(Errors.emptyData) -> {
                return Solutions.inputSolution
            }
            exception.message.toString()
                .contains(Errors.timeoutError) -> {
                return Solutions.connectionSolution
            }
            else -> {
                return Solutions.unknownError
            }
        }
    }
}