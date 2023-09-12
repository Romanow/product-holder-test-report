package ru.romanow.product.holder.service

import jakarta.persistence.EntityNotFoundException
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.romanow.product.holder.entity.Product
import ru.romanow.product.holder.entity.enums.CurrencyName
import ru.romanow.product.holder.models.ProductResponse
import ru.romanow.product.holder.repository.ProductRepository
import java.io.InputStream
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val currencyService: CurrencyService
) : ProductService {

    @Transactional(readOnly = true)
    override fun findByName(name: String, currency: String): ProductResponse {
        val currencyName: CurrencyName = findCurrency(currency)
            ?: throw EntityNotFoundException("Currency '$currency' not found")

        return productRepository
            .findByName(name)
            .map {
                ProductResponse(
                    name = it.name,
                    currency = currencyName,
                    price = convert(it.price!!, it.currency!!, currencyName, it.created!!),
                    actualPrice = convert(it.price!!, it.currency!!, currencyName, now()),
                    created = it.created
                )
            }
            .orElseThrow { EntityNotFoundException("Product '$name' not found") }
    }

    @Transactional
    override fun upload(stream: InputStream) {
        val workbook = XSSFWorkbook(stream)
        val sheet = workbook.getSheetAt(0)

        for ((r, row) in sheet.withIndex()) {
            if (r == 0) continue // header

            var product = Product()
            row.forEachIndexed { c, cell ->
                structure[c]?.invoke(product, extractValue(cell))
            }

            product = productRepository
                .findByName(name = product.name!!)
                .map {
                    it.price = product.price
                    it.currency = product.currency
                    return@map it
                }
                .orElse(product)

            productRepository.save(product)
        }
    }

    private fun convert(
        price: Double,
        baseCurrency: CurrencyName,
        targetCurrency: CurrencyName,
        date: LocalDateTime
    ): Double {
        return if (baseCurrency != targetCurrency) {
            var basePriceInRub = price
            if (baseCurrency != CurrencyName.RUB) {
                val exchangeRateForDate = currencyService.getCurrencyForDate(baseCurrency, date)
                basePriceInRub = price / exchangeRateForDate
            }
            var targetExchangeRate = 1.0
            if (targetCurrency != CurrencyName.RUB) {
                targetExchangeRate = currencyService.getCurrencyForDate(targetCurrency, date)
            }

            basePriceInRub * targetExchangeRate
        } else {
            price
        }
    }

    private fun findCurrency(currency: String) =
        CurrencyName.values().find { it.name == currency }

    private fun extractValue(cell: Cell) =
        when (cell.cellType) {
            CellType.NUMERIC -> cell.numericCellValue.toString()
            CellType.STRING -> cell.stringCellValue
            else -> throw IllegalArgumentException("Cell type '${cell.cellType}' not supported")
        }

    companion object {
        private val structure = mapOf<Int, (Product, String) -> Unit>(
            0 to { a, v -> a.name = v },
            1 to { a, v -> a.price = v.toDouble() },
            2 to { a, v -> a.currency = CurrencyName.valueOf(v) }
        )
    }
}
