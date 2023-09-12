package ru.romanow.product.holder.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.romanow.product.holder.entity.Currency
import ru.romanow.product.holder.entity.enums.CurrencyName
import java.time.LocalDateTime

interface CurrencyRepository : JpaRepository<Currency, Long> {

    @Query(
        """select c.value
              from Currency c
              where c.name = :name and c.date between :startDate and :endDate
              order by c.date asc
              limit 1"""
    )
    fun getCurrencyForDate(
        @Param("name") name: CurrencyName,
        @Param("startDate") startDate: LocalDateTime,
        @Param("endDate") endDate: LocalDateTime
    ): Double
}
