package it.uniroma2.ispw.model.prodotto;


import it.uniroma2.ispw.enums.StatoProdotto;

public class ProdottoModel {
    private String prodottoID;
    private String nomeProdotto;
    private int prezzo;
    private String descrizione;
    private  String categoria;

    private StatoProdotto stato;

    public ProdottoModel(String prodottoID, String nome, int prezzo) {
        this.prodottoID = prodottoID;
        this.nomeProdotto = nome;
        this.prezzo = prezzo;
    }

    public ProdottoModel() {

    }

    public ProdottoModel(String prodottoID, String nomeProdotto, int prezzo, String categoria, String descrizione) {
        this.prodottoID = prodottoID;
        this.nomeProdotto = nomeProdotto;
        this.prezzo = prezzo;
        this.categoria = categoria;
        this.descrizione = descrizione;
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
