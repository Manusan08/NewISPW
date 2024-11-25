package it.uniroma2.ispw.model;

public class Spedizione {
    private final String tipo;
    private final double costo;
    private final String tempoConsegna;

    public Spedizione(String tipo, double costo, String tempoConsegna) {
        this.tipo = tipo;
        this.costo = costo;
        this.tempoConsegna = tempoConsegna;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCosto() {
        return costo;
    }

    public String getTempoConsegna() {
        return tempoConsegna;
    }
}