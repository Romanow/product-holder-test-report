package ru.romanow.product.holder.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.romanow.product.holder.entity.Currency

interface CurrencyRepository : JpaRepository<Currency, Long>
