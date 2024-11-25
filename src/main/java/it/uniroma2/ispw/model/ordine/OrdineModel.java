package it.uniroma2.ispw.model.ordine;


import it.uniroma2.ispw.model.prodotto.ProdottoModel;

import java.util.Date;
import java.util.List;

public class OrdineModel {
    private String emailCliente;
    private double prezzotot;
    private String pagamento;
    private String indirizzo;
    private Date dataAcquisto;
    private String ordineID;
    private List<ProdottoModel> prodottiID;
    private String coupon;

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public double getPrezzotot() {
        return prezzotot;
    }

    public void setPrezzotot(double prezzotot) {
        this.prezzotot = prezzotot;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

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

    public List<ProdottoModel> getProdottiID() {
        return prodottiID;
    }

    public void setProdottiID(List<ProdottoModel> prodottiID) {
        this.prodottiID = prodottiID;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
