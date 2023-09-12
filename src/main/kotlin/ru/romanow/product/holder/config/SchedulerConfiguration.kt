package ru.romanow.product.holder.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.parseMediaType
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

@Configuration
@EnableScheduling
@ConditionalOnProperty(value = ["application.currencies.enabled"], havingValue = "true", matchIfMissing = false)
class SchedulerConfiguration {

    @Bean
    fun restTemplate(objectMapper: ObjectMapper): RestTemplate =
        RestTemplateBuilder()
            .additionalMessageConverters(JavascriptMessageConverter(objectMapper))
            .build()

    class JavascriptMessageConverter(objectMapper: ObjectMapper) :
        AbstractJackson2HttpMessageConverter(objectMapper, parseMediaType("application/javascript"))
}
