package ru.romanow.product.holder.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import ru.romanow.product.holder.entity.enums.CurrencyName
import ru.romanow.product.holder.repository.ProductRepository
import java.time.LocalDateTime

class ProductServiceTest {

    private lateinit var productService: ProductServiceImpl
    private lateinit var productRepository: ProductRepository
    private lateinit var currencyService: CurrencyService

    @BeforeEach
    fun init() {
        productRepository = mock(ProductRepository::class.java)
        currencyService = mock(CurrencyService::class.java)
        productService = ProductServiceImpl(productRepository, currencyService)
    }

    @Test
    fun testCurrencyConverter() {
        // given
        val now = LocalDateTime.now()
        `when`(currencyService.getCurrencyForDate(CurrencyName.USD, now)).thenReturn(2.0)
        `when`(currencyService.getCurrencyForDate(CurrencyName.EUR, now)).thenReturn(10.0)

        // when
        val price = productService.convert(100.0, CurrencyName.USD, CurrencyName.EUR, now)

        // then
        assertThat(price).isEqualTo(500.0)
    }
}
