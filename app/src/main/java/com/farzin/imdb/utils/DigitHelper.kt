package com.farzin.imdb.utils

object DigitHelper {

    fun digitByLang(englishStr: String): String {
        var result = ""
        var fa = '۰'
        for (ch in englishStr) {
            fa = ch
            when (ch) {
                '0' -> fa = '۰'
                '1' -> fa = '۱'
                '2' -> fa = '۲'
                '3' -> fa = '۳'
                '4' -> fa = '۴'
                '5' -> fa = '۵'
                '6' -> fa = '۶'
                '7' -> fa = '۷'
                '8' -> fa = '۸'
                '9' -> fa = '۹'
            }
            result = "${result}$fa"
        }
        return result
    }


    fun digitBySeparator(number: String): String {
        val regex = Regex("\\d{3}")
        val groups = regex.findAll(number).map { it.value }.joinToString(",")
        return groups
    }

    fun digitByLangAndSeparator(price: String): String {
        val persianDigit = digitByLang(price)
        return digitBySeparator(persianDigit)
    }

}