package it.uniroma2.ispw.bean;

public class CouponBean {
    private String nome;
    private String scadenza;
    private String couponID;
    private String type = "NoCoupon";

    public String getNome() {
        return nome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getCouponID() {
        return couponID;
    }

    public void setCouponID(String couponID) {
        this.couponID = couponID;
    }
}
