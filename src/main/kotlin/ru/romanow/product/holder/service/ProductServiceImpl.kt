package ru.romanow.product.holder.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.romanow.product.holder.models.ProductResponse
import ru.romanow.product.holder.repository.ProductRepository

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    @Transactional(readOnly = true)
    override fun findByName(name: String): ProductResponse {
        TODO("Not yet implemented")
    }
}