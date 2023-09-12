package ru.romanow.product.holder.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.romanow.product.holder.config.properties.CurrenciesProperties
import ru.romanow.product.holder.entity.Currency
import ru.romanow.product.holder.entity.enums.CurrencyName
import ru.romanow.product.holder.repository.CurrencyRepository
import java.time.LocalDateTime

@Service
class CurrencyServiceImpl(
    private val currencyRepository: CurrencyRepository,
    private val currenciesProperties: CurrenciesProperties
) : CurrencyService {

    @Transactional(readOnly = true)
    override fun getCurrencyForDate(name: CurrencyName, date: LocalDateTime) =
        currencyRepository
            .getCurrencyForDate(name, date.minusSeconds(currenciesProperties.requestDelay.toLong()), date)

    @Transactional
    override fun saveAll(currencies: List<Currency>) {
        currencyRepository.saveAll(currencies)
    }
}
