package ru.romanow.product.holder.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import ru.romanow.product.holder.entity.enums.CurrencyName
import java.time.LocalDateTime

@Entity
@Table(name = "products", indexes = [Index(name = "udx_products_name", columnList = "name", unique = true)])
@EntityListeners(AuditingEntityListener::class)
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "price", nullable = false)
    var price: Double? = null,

    @Column(name = "currency", nullable = false)
    var currency: CurrencyName? = null,

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    var created: LocalDateTime? = null
) {
    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (name != other.name) return false

        return true
    }

    override fun toString(): String {
        return "Product(" +
            "id=$id, " +
            "name=$name, " +
            "price=$price, " +
            "currency=$currency, " +
            "created=$created" +
            ")"
    }
}
