package it.uniroma2.ispw.model.carrello;

import it.uniroma2.ispw.model.prodotto.ProdottoModel;

import java.util.List;

public class CarrelloModel {
    private String emailCliente;
    private List<ProdottoModel> prodottiID;

    private int prezzoToT;

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }


    public List<ProdottoModel> getProdottiID() {
        return prodottiID;
    }

    public void setProdottiID(List<ProdottoModel> prodottiID) {
        this.prodottiID = prodottiID;
    }

    public int getPrezzoToT() {
        return prezzoToT;
    }

    public void setPrezzoToT(int prezzoToT) {
        this.prezzoToT = prezzoToT;
    }
}
