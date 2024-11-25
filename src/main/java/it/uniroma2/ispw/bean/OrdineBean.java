package it.uniroma2.ispw.bean;

import java.util.Date;
import java.util.List;

public class OrdineBean {
    private List<ProdottoBean> prodotti;
    private String metodoDiPagamento;
    private String indirizzo;

    private String spedizione;

    private String coupon;

    private double prezzoTOT;
    private String ordineID;
    private Date dataAcquisto;

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public String getOrdineID() {
        return ordineID;
    }

    public void setOrdineID(String ordineID) {
        this.ordineID = ordineID;
    }

    public double getPrezzoTOT() {
        return prezzoTOT;
    }

    public void setPrezzoTOT(double prezzoTOT) {
        this.prezzoTOT = prezzoTOT;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public List<ProdottoBean> getProdotti() {
        return prodotti;
    }

    public String getMetodoDiPagamento() {
        return metodoDiPagamento;
    }

    public void setMetodoDiPagamento(String metodoDiPagamento) {
        this.metodoDiPagamento = metodoDiPagamento;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(String spedizione) {
        this.spedizione = spedizione;
    }

    public void setProdotti(List<ProdottoBean> prodotti) {
        this.prodotti = prodotti;
    }
}