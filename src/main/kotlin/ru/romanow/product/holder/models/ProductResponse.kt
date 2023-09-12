package ru.romanow.product.holder.models

import ru.romanow.product.holder.entity.enums.CurrencyName
import java.time.LocalDateTime

data class ProductResponse(
    var name: String? = null,
    var price: Double? = null,
    var actualPrice: Double? = null,
    var currency: CurrencyName? = null,
    var created: LocalDateTime? = null
)
