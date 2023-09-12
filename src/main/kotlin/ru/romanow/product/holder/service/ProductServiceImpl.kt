package ru.romanow.product.holder.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import ru.romanow.product.holder.models.ProductResponse
import ru.romanow.product.holder.repository.ProductRepository

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    @Transactional(readOnly = true)
    override fun findByName(name: String, currency: String): ProductResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun upload(file: MultipartFile) {
        TODO("Not yet implemented")
    }
}
