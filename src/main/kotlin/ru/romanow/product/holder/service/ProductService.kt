package ru.romanow.product.holder.service

import ru.romanow.product.holder.models.ProductResponse

interface ProductService {
    fun findByName(name: String): ProductResponse
}
