package com.juan.demomicro;

public class Respuesta {
    private double a;
    private double b;
    private double resultado;
    private String error = "Ningún error";
    

    public Respuesta() {
    }

    public Respuesta(double a, double b, double resultado) {
        this.a = a;
        this.b = b;
        this.resultado = resultado;
    }

    public Respuesta(double a, double b, double resultado, String error) {
        this.a = a;
        this.b = b;
        this.resultado = resultado;
        this.error = error;
    }

    public double getA() {
        return a;
    }
    public void setA(double a) {
        this.a = a;
    }
    public double getB() {
        return b;
    }
    public void setB(double b) {
        this.b = b;
    }
    public double getResultado() {
        return resultado;
    }
    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    
}
