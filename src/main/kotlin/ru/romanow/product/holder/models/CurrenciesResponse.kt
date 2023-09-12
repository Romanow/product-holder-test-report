package ru.romanow.product.holder.models

import ru.romanow.product.holder.entity.enums.CurrencyNames
import java.time.Instant
import java.time.LocalDate

data class CurrenciesResponse(
    var date: LocalDate? = null,
    var timestamp: Instant? = null,
    var base: CurrencyNames? = null,
    var rates: Map<String, Double>? = null
)
