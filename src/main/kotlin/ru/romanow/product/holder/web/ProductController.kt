package ru.romanow.product.holder.web

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.romanow.product.holder.service.ProductService

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/{name}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun productByName(@PathVariable name: String) =
        productService.findByName(name)
}