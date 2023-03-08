package com.fastcampus.r2dbcexample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/books")
class BookController(
    val bookRepository: BookRepository
) {

    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String): Mono<Book> =
        bookRepository.findByName(name)

    @PostMapping
    fun create(@RequestBody request: Map<String, Any>): Mono<Book> =
        bookRepository.save(Book(name = request.get("name").toString(), price = request.get("price") as Int))

}