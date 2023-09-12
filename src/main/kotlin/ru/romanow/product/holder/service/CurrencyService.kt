package ru.romanow.product.holder.service

import ru.romanow.product.holder.entity.Currency
import ru.romanow.product.holder.entity.enums.CurrencyName
import java.time.LocalDateTime

interface CurrencyService {
    fun getCurrencyForDate(name: CurrencyName, date: LocalDateTime): Double
    fun saveAll(currencies: List<Currency>)
}
