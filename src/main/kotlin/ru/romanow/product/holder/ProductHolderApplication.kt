package ru.romanow.product.holder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductHolderApplication

fun main(args: Array<String>) {
    runApplication<ProductHolderApplication>(*args)
}
