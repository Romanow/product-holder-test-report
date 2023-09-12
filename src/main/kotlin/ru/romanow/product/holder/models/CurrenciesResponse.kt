package ru.romanow.product.holder.models

import ru.romanow.product.holder.entity.enums.CurrencyName
import java.time.Instant
import java.time.LocalDate

data class CurrenciesResponse(
    var date: LocalDate? = null,
    var timestamp: Instant? = null,
    var base: CurrencyName? = null,
    var rates: Map<String, Double>? = null
)
