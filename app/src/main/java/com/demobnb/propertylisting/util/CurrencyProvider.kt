package com.demobnb.propertylisting.util

import java.util.Currency
import java.util.Locale

object CurrencyProvider {
    val locale: Locale = Locale.getDefault()
    private val currency: Currency = Currency.getInstance(locale)

    val symbol: String = currency.symbol
    val code: String = currency.currencyCode
}