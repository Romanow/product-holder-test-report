package ru.romanow.product.holder

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import ru.romanow.product.holder.config.DatabaseTestConfiguration

@SpringBootTest
@Import(DatabaseTestConfiguration::class)
class ProductHolderApplicationTest {
    @Test
    fun test() {}
}
