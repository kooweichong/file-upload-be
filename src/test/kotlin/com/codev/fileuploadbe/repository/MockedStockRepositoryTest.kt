package com.codev.fileuploadbe.repository

import com.codev.fileuploadbe.controller.StockController
import com.codev.fileuploadbe.model.Stock
import com.codev.fileuploadbe.service.StockService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.every
import io.mockk.slot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList

@WebFluxTest
@Import(StockController::class, StockService::class)
class MockedStockRepositoryTest(@Autowired val client: WebTestClient) {

    @MockkBean
    private lateinit var stockRepository: StockRepository

    private fun firstStock(
        invoiceno: String = "536365",
        stockcode: String = "85123A",
        description: String = "WHITE HANGING HEART T-LIGHT HOLDER",
        quantity: Int = 2,
        invoicedate: String = "12/1/2010  8:26:00 am",
        unitprice: Float = 2.55f,
        customerid: String = "17850",
        country: String = "United Kingdom"
    ) = Stock(
        id = 1,
        invoiceno = invoiceno,
        stockcode = stockcode,
        description = description,
        quantity = quantity,
        invoicedate = invoicedate,
        unitprice = unitprice,
        customerid = customerid,
        country = country
    )

    private fun secondStock(
        invoiceno: String = "536365",
        stockcode: String = "85123A",
        description: String = "WHITE HANGING HEART T-LIGHT HOLDER",
        quantity: Int = 2,
        invoicedate: String = "12/1/2010  8:26:00 am",
        unitprice: Float = 2.55f,
        customerid: String = "17850",
        country: String = "United Kingdom"
    ) = Stock(
        id = 2,
        invoiceno = invoiceno,
        stockcode = stockcode,
        description = description,
        quantity = quantity,
        invoicedate = invoicedate,
        unitprice = unitprice,
        customerid = customerid,
        country = country
    )

    @Test
    fun `Test Retrieve all stocks`() {
        every {
            stockRepository.findAll()
        } returns flow {
            emit(firstStock())
            emit(secondStock())
        }

        client
            .get()
            .uri("/stocks")
            .exchange()
            .expectStatus()
            .isOk.expectBodyList<Stock>()
            .hasSize(2)
            .contains(firstStock(), secondStock())
    }

    @Test
    fun `Test Empty Request to Upload Csv`() {
        val savedStock = slot<Flow<Stock>>()
        coEvery {
            stockRepository.saveAll(capture(savedStock))
        } coAnswers {
            savedStock.captured
        }

        client
            .post()
            .uri("/uploads")
            .exchange()
            .expectStatus()
            .isBadRequest
    }
}