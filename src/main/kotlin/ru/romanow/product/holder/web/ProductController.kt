package ru.romanow.product.holder.web

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ru.romanow.product.holder.service.ProductService

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/{name}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun productByName(@PathVariable name: String, @RequestParam currency: String) =
        productService.findByName(name, currency)

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(@RequestParam file: MultipartFile) {
        productService.upload(file)
    }
}
