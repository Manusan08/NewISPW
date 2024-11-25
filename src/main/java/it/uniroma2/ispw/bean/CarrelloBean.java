package it.uniroma2.ispw.bean;

import java.util.List;

public class CarrelloBean {
    private String emailCliente;
    private List<ProdottoBean> prodottiID;

    private int prezzoTOT;

    public List<ProdottoBean> getProdottiID() {
        return prodottiID;
    }

    public void setProdottiID(List<ProdottoBean> prodottiID) {
        this.prodottiID = prodottiID;
    }



    public int getPrezzoTOT() {
        return prezzoTOT;
    }

    public void setPrezzoTOT(int prezzoTOT) {
        this.prezzoTOT = prezzoTOT;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }


}
