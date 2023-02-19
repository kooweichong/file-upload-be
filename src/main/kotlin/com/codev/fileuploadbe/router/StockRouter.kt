package com.codev.fileuploadbe.router

import com.codev.fileuploadbe.service.StockService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class StockRouter(private val stockService: StockService) {
    @Bean
    fun stockApiRouter() = coRouter {
        "/api".nest {
            GET("/stocks", stockService::getAllStocks)
        }
    }
}