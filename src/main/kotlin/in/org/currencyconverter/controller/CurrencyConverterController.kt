package `in`.org.currencyconverter.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import java.math.BigDecimal

data class CurrencyConversionResult(val base: String, val rates: Map<String, BigDecimal>)

@RestController
class CurrencyConverterController(
    private val springTemplateEngine: SpringTemplateEngine
) {

    @GetMapping("/")
    fun index(model: Model): String {
        val context = Context()
        context.setVariable("currencies", listOf("EUR", "USD", "GBP"))
        return springTemplateEngine.process("index", context)
    }

    @GetMapping("/convert")
    fun convert(
        @RequestParam(name = "amount") amount: BigDecimal,
        @RequestParam(name = "from") from: String,
        @RequestParam(name = "to") to : String
    ): String {
        val url = "https://api.apilayer.com/exchangerates_data/latest?base=$from&symbols=$to"
        val headers = HttpHeaders()
        headers.set("apikey", "X2DKZRS0kWixxVCi59925Gd5wUVUVPwu")
        val entity = HttpEntity<String>(headers)
        val result = RestTemplate().exchange(url, HttpMethod.GET, entity, CurrencyConversionResult::class.java).body
        val rate = result?.rates?.get(to)
        val convertedAmount = amount * rate!!
        return "$amount $from = $convertedAmount $to"
    }
}