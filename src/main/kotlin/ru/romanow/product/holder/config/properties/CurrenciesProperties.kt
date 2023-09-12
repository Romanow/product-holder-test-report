package ru.romanow.product.holder.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("application.currencies")
data class CurrenciesProperties(
    val enabled: Boolean,
    val initDelay: Int,
    val requestDelay: Int,
    val provider: String
)
