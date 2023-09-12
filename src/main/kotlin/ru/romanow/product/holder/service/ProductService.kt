package ru.romanow.product.holder.service

import ru.romanow.product.holder.models.ProductResponse
import java.io.InputStream

interface ProductService {
    fun findByName(name: String, currency: String): ProductResponse
    fun upload(stream: InputStream)
}
