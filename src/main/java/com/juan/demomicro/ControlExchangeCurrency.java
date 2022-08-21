package com.juan.demomicro;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ControlExchangeCurrency {
    
    @Value("${app.url_exchange_rate}")
    private String url_exchange_rate;

    @Value("${app.url_exchange_rate_apiKey}")
    private String url_exchange_rate_apiKey;

    /**
    * Funcion que nos permite setear a la cabecera de la peticion
    * nuestro apikey para realizar las conversiones de las tasas
    * de cambio
    *
    *@return una entidad de solicitud o respuesta HTTP, que consta de encabezados y cuerpo
    *@see https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpEntity.html
    */
    public HttpEntity<String> set_headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", url_exchange_rate_apiKey);
        return new HttpEntity<String>(headers);
    }

    /**
     * Funcion que nos permite hacer la peticion al api para hacer la conversion de 
     * monedas del valor.
     * @param to a que moneda queremos convertir según el api se debe de indicar las
     * tres letras del codigo de la moneda
     * @param from que moneda queremos convertir
     * @param amount el valor que queremos convertir
     * @return la respuesta que nos da el api al momento de hacer la conversion
     * @see https://apilayer.com/marketplace/exchangerates_data-api#documentation-tab
     */
    public String sendRequest(String to, String from, String amount){
        final HttpEntity<String> entity = set_headers();
        String url = url_exchange_rate + String.format("to=%1$s&from=%2$s&amount=%3$s", to, from, amount);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }


    /**
     * Funcion que nos permite manipular la informacion que nos responde el api
     * para ser enviada en respuesta como se requiere
     * @param response recibe un objecto de tipo JSONObject que tiene la respuesta en un formato
     * que es mas sencillo de ser manipulado
     * @return retorna un objecto de tipo RespuestaExchangeCurrency
     */
    public RespuestaExchangeCurrency set_reponse_user(JSONObject response){
        double rate = response.getJSONObject("info").getDouble("rate");
        double result = response.getDouble("result");
        JSONObject query = response.getJSONObject("query");
        double amount = query.getDouble("amount");
        String from = query.getString("from");
        String to = query.getString("to");
        return new RespuestaExchangeCurrency(rate, result, amount, from, to);
    }


    @GetMapping("/coptousd/{amount}")
    public RespuestaExchangeCurrency copToUsd(@PathVariable double amount) throws JsonMappingException, JsonProcessingException{
        String response = sendRequest("USD", "COP", String.valueOf(amount));
        JSONObject responseJsonObject = new JSONObject(response);
        return set_reponse_user(responseJsonObject);
    }

    @GetMapping("/usdtocop/{amount}")
    public RespuestaExchangeCurrency usdToCop(@PathVariable double amount) throws JsonMappingException, JsonProcessingException{
        String response = sendRequest("COP", "USD", String.valueOf(amount));
        JSONObject responseJsonObject = new JSONObject(response);
        return set_reponse_user(responseJsonObject);
    }


}
