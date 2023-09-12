package ru.romanow.product.holder.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import ru.romanow.product.holder.entity.enums.CurrencyNames
import java.time.LocalDateTime

@Entity
@Table(
    name = "currencies",
    indexes = [Index(name = "udx_currencies_date_name", columnList = "date, name", unique = true)]
)
@EntityListeners(AuditingEntityListener::class)
data class Currency(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    var name: CurrencyNames? = null,

    @Column(name = "value", nullable = false)
    var value: Double? = null,

    @CreatedDate
    @Column(name = "date", nullable = false, updatable = false)
    var date: LocalDateTime? = null
) {

    override fun toString(): String {
        return "Currency(id=$id, name=$name, value=$value, date=$date)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Currency

        if (name != other.name) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (date?.hashCode() ?: 0)
        return result
    }
}
