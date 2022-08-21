package com.juan.demomicro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlCalculadora {
    
    @Value("${app.message.error_division_cero}")
    private String mensajeErrorDivCero;


    @GetMapping("/sumar/{a}/{b}")
    public Respuesta sumar(@PathVariable double a, @PathVariable double b){
        Respuesta res = new Respuesta();
        res.setA(a);
        res.setB(b);
        res.setResultado(a+b);
        return res;
    }

    @GetMapping("/restar/{a}/{b}")
    public Respuesta restar(@PathVariable double a, @PathVariable double b){
        Respuesta res = new Respuesta(a,b,(a-b));
        return res;
    }

    @GetMapping("/multiplicar/{a}/{b}")
    public Respuesta multiplicar(@PathVariable double a, @PathVariable double b){
        return new Respuesta(a,b,(a*b));
    }

    @GetMapping("/dividir/{a}/{b}")
    public Respuesta dividir(@PathVariable double a, @PathVariable double b){
        if(b == 0){
            return new Respuesta(a,b,(a/b), mensajeErrorDivCero);
        }
        return new Respuesta(a,b,(a/b));
    }


}
