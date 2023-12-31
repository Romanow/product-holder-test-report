package ru.romanow.product.holder.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.CurrentDateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
class DatabaseConfiguration {

    @Bean
    fun dateTimeProvider() = CurrentDateTimeProvider.INSTANCE
}
