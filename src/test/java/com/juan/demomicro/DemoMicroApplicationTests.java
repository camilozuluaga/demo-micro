package com.juan.demomicro;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoMicroApplicationTests {


    @Value("${app.message.error_division_cero}")
    private String mensajeErrrorDivCero;

	@Autowired
	private ControlCalculadora controlCalculadora;

	@Test
	public void sumar(){
		Respuesta res = controlCalculadora.sumar(1, 10);
		assertEquals(11, res.getResultado());
		
	}

	@Test
	public void dividirCero(){
		Respuesta res = controlCalculadora.dividir(1, 0);
		assertEquals(mensajeErrrorDivCero, res.getError());
	}

}
