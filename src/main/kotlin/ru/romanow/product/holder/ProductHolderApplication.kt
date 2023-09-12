package ru.romanow.product.holder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.romanow.product.holder.config.properties.CurrenciesProperties

@SpringBootApplication
@EnableConfigurationProperties(CurrenciesProperties::class)
class ProductHolderApplication

fun main(args: Array<String>) {
    runApplication<ProductHolderApplication>(*args)
}
