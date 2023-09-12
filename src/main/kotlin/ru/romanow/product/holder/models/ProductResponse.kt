package ru.romanow.product.holder.models

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class ProductResponse(
    var name: BigDecimal? = null,
    var price: BigDecimal? = null,
    var currency: Currency? = null,
    var created: LocalDateTime? = null
)
