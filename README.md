# Пирамида тестирования или зачем мы тратим столько времени впустую?

[![Build project](https://github.com/Romanow/product-holder-test-report/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Romanow/product-holder-test-report/actions/workflows/build.yml)

## Задача

Продемонстрировать на живом примере применение Пирамиды Тестирования (unit тесты, сервисные тесты, end-to-end тесты).

## Описание

Написать сервис для загрузки продуктов из excel в формате:

| Название продукта  | Цена  | Валюта |
|--------------------|:-----:|:------:|
| Mercedes GLA 250   | 45000 |  EUR   |
| BMW X3             | 60000 |  EUR   |
| Lego Technic 42260 |  179  |  USD   |

И сделать API для получения стоимости продукта на момент загрузки и на текущий момент:

```http request
POST /api/v1/products
Content-Type: multipart/form-data
```

```http request
GET /api/v1/products/{name}?currency=RUB
Accept: application/json
```

```json
{
  "<product-name>": "Mercedes GLA 250",
  "<price>": 4500000,
  "<actual-price>": 4300000,
  "<currency>": "RUB",
  "<created-date>": "11-09-2023"
}
```

Сначала написать тесты на методы и на сервис, потом добавить реализацию. Курсы хранить в БД и брать актуальные с
сайта [https://www.cbr-xml-daily.ru/latest.js](https://www.cbr-xml-daily.ru/latest.js) раз в 6 часов.

## План

1. Сначала брать курсы RUB, USD, EUR.
2. Потом добавить CNY (китайский юань).
3. Объяснить сколько времени потребовалось бы для повторного ручного тестирования.
4. Объяснить сколько времени тратится на взаимодействие тестировщик – разработчик, почему для тестировщика сервис – это
   черный ящик и как ему задавать тестовые данные.
5. Продемонстрировать end-to-end тесты и автоматизированное покрытие.
