package ru.romanow.product.holder.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int ?= null,

    @Column(name = "price", nullable = false)
    var name: BigDecimal ?= null,

    @Column(name = "price", nullable = false)
    var price: BigDecimal ?= null,

    @Column(name = "currency", nullable = false)
    var currency: Currency ?= null,

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    var created: LocalDateTime ?= null
) {


}