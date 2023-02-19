package com.codev.fileuploadbe.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadRequestException(msg: String) : RuntimeException(msg)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    class CsvImportException(msg: String) : RuntimeException(msg)
}