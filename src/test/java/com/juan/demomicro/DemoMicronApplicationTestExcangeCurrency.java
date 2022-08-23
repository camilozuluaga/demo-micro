package com.juan.demomicro;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
class DemoMicronApplicationTestExcangeCurrency {


	@Autowired
	private ControlExchangeCurrency controlExchangeCurrency;

    @Value("${app.url_exchange_get_rate}")
    private String url_exchange_get_rate;

    @Value("${app.url_exchange_rate_apiKey}")
    private String url_exchange_rate_apiKey;


    public String sendRequest(){
        final HttpEntity<String> entity = controlExchangeCurrency.set_headers();
        String url = url_exchange_get_rate + "/latest?symbols=COP&base=USD";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return response.getBody();
    }

    public double get_rate() throws JSONException{
		String responseRequest = sendRequest();
        JSONObject response = new JSONObject(responseRequest);
        double rate = response.getJSONObject("rates").getDouble("COP");
        return rate;
    }

    @Test
	public void copToUsd() throws JsonMappingException, JsonProcessingException, JSONException{
		ResponseExchangeCurrency res = controlExchangeCurrency.copToUsd(50000);
		final double rate = get_rate();
        System.out.println(rate);
        double scale = Math.pow(10, 2);
		assertEquals(Math.round(((50000.0 / rate) * scale)) / scale, res.getResult());
	}

	@Test
	public void usdToCop() throws JsonMappingException, JsonProcessingException, JSONException{
		ResponseExchangeCurrency res = controlExchangeCurrency.usdToCop(50000);
		final double rate = get_rate();
        System.out.println(rate);
		assertEquals(Math.round((50000.0*rate)), res.getResult());
		
	}

}
