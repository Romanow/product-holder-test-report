package ru.romanow.product.holder

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.multipart
import org.springframework.transaction.annotation.Transactional
import ru.romanow.product.holder.config.DatabaseTestConfiguration
import ru.romanow.product.holder.entity.Currency
import ru.romanow.product.holder.entity.enums.CurrencyName
import ru.romanow.product.holder.entity.enums.CurrencyName.CNY
import ru.romanow.product.holder.entity.enums.CurrencyName.EUR
import ru.romanow.product.holder.entity.enums.CurrencyName.USD
import ru.romanow.product.holder.repository.CurrencyRepository
import java.util.stream.Stream

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Import(DatabaseTestConfiguration::class)
class ProductHolderApplicationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var currencyRepository: CurrencyRepository

    @BeforeEach
    fun init() {
        val currencies = mapOf(USD to USD_VALUE, CNY to CNY_VALUE, EUR to EUR_VALUE)
        currencyRepository
            .saveAllAndFlush(currencies.map { Currency(name = it.key, value = it.value) })

        val content = ClassPathResource("excel/products.xlsx").contentAsByteArray
        mockMvc
            .multipart("/api/v1/products/upload") { file("file", content) }
            .andExpect { status { isAccepted() } }
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    fun test(product: String, currency: CurrencyName, price: Double) {
        mockMvc.get("/api/v1/products/{product}?currency={currency}", product, currency) {
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$.name", equalTo(product))
                    jsonPath("$.price", equalTo(price))
                    jsonPath("$.actualPrice", equalTo(price))
                    jsonPath("$.currency", equalTo(currency.name))
                    jsonPath("$.created", notNullValue())
                }
            }
    }

    companion object {
        private const val USD_VALUE = 2.0
        private const val EUR_VALUE = 3.0
        private const val CNY_VALUE = 1.5

        private const val MERCEDES_PRODUCT = "Mercedes GLA 250"
        private const val BMW_PRODUCT = "BMW X3"
        private const val LEGO_PRODUCT = "Lego Technic 42260"
        private const val LIEBHERR_PRODUCT = "Lego Technic Liebherr LR13000"

        @JvmStatic
        fun argumentProvider(): Stream<Arguments> = Stream.of(
            of(MERCEDES_PRODUCT, CurrencyName.RUB, 22500.0), // 45.000 USD
            of(BMW_PRODUCT, USD, 40000.0), // 60.000 EUR
            of(LEGO_PRODUCT, USD, 39800.0), // 19900 RUB
            of(LIEBHERR_PRODUCT, CNY, 599.25) // 799 USD
        )
    }
}
