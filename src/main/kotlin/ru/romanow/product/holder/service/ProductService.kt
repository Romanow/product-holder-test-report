package ru.romanow.product.holder.service

import org.springframework.web.multipart.MultipartFile
import ru.romanow.product.holder.models.ProductResponse

interface ProductService {
    fun findByName(name: String, currency: String): ProductResponse
    fun upload(file: MultipartFile)
}
