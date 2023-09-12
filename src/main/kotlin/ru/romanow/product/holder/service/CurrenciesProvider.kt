package ru.romanow.product.holder.service

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import ru.romanow.product.holder.config.properties.CurrenciesProperties
import ru.romanow.product.holder.entity.Currency
import ru.romanow.product.holder.entity.enums.CurrencyName
import ru.romanow.product.holder.models.CurrenciesResponse
import java.util.concurrent.TimeUnit

@Service
@ConditionalOnProperty(value = ["application.currencies.enabled"], havingValue = "true", matchIfMissing = false)
class CurrenciesProvider(
    private val currenciesProperties: CurrenciesProperties,
    private val currencyService: CurrencyService,
    private val restTemplate: RestTemplate
) {

    @Scheduled(
        initialDelayString = "\${application.currencies.init-delay}",
        fixedRateString = "\${application.currencies.request-delay}",
        timeUnit = TimeUnit.SECONDS
    )
    @Transactional
    fun fetchCurrencies() {
        val response = restTemplate
            .getForObject(currenciesProperties.provider, CurrenciesResponse::class.java)!!

        val currencies = CurrencyName
            .values()
            .filter { it != response.base }
            .map { Currency(name = it, value = response.rates?.get(it.name)) }
            .toList()

        currencyService.saveAll(currencies)
    }
}
