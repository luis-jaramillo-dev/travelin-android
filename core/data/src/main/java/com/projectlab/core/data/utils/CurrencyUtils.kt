package com.projectlab.core.data.utils

import java.util.Currency
import java.util.Locale

class CurrencyUtils {
    fun getCurrencySymbolByCountryCode(countryCode: String): String {
        return try {
            val locale = Locale("", countryCode)
            val currency = Currency.getInstance(locale)
            currency.symbol
        } catch (e: IllegalArgumentException) {
            return "?"
        }
    }

    fun getCurrencySymbolByCurrencyCode(currencyCode: String): String {
        return try {
            val currency = Currency.getInstance(currencyCode)
            currency.symbol
        } catch (e: IllegalArgumentException) {
            return "?"
        }
    }

    fun getCurrencyCodeByCountryCode(countryCode: String): String {
        return try {
            val locale = Locale("", countryCode)
            val currency = Currency.getInstance(locale)
            currency.currencyCode
        } catch (e: IllegalArgumentException) {
            return "?"
        }
    }
}