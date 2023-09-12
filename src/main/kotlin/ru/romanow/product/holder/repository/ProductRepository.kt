package ru.romanow.product.holder.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.romanow.product.holder.entity.Product
import java.util.Optional

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByName(name: String): Optional<Product>
}
