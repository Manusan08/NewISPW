package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.enums.StatoProdotto;

public class ProdottoBean {
    private String prodottoID;
    private String nomeProdotto;
    private int prezzo;
    private String descrizione;
    private  String categoria;

    private StatoProdotto stato;

    public ProdottoBean(String prodottoID, String nomeProdotto, int prezzo, String categoria, String descrizione) {
        this.categoria = categoria;
        this.prodottoID = prodottoID;
        this.nomeProdotto = nomeProdotto;
        this.prezzo = prezzo;
        this.descrizione = descrizione;

    }

    public ProdottoBean(String prodottoID, String nomeProdotto, int prezzo) {
        this.prodottoID = prodottoID;
        this.nomeProdotto = nomeProdotto;
        this.prezzo = prezzo;
    }

    public ProdottoBean() {

    }

    public StatoProdotto getStato() {
        return stato;
    }

    public void setStato(StatoProdotto stato) {
        this.stato = stato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public String getProdottoID() {
        return prodottoID;
    }

    public void setProdottoID(String prodottoID) {
        this.prodottoID = prodottoID;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
