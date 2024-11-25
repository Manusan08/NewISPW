package it.uniroma2.ispw.bean;

import it.uniroma2.ispw.enums.Role;


import java.util.List;

public class UserBean {
    private Role ruolo;
    private String email;
    private List<String> indirizzo;
    private List<String> pagamento;
    private String annoNascita;
    private String nome;
    private String cognome;
    public UserBean(String email, Role ruolo) {
        this.email = email;
        this.ruolo = ruolo;
    }

    public UserBean(String email, Role ruolo, List<String> indirizzo, List<String> pagamento) {
        this.email = email;
        this.ruolo = ruolo;
        this.indirizzo = indirizzo;
        this.pagamento =pagamento;
    }

    public UserBean(String email, Role ruolo, String nome, String annoNascita, String cognome) {
        this.email = email;
        this.ruolo = ruolo;
        this.cognome = cognome;
        this.nome =nome;
        this.annoNascita= annoNascita;
    }

    public UserBean() {

    }


    public String getAnnoNascita() {
        return annoNascita;
    }

    public void setAnnoNascita(String annoNascita) {
        this.annoNascita = annoNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public List<String> getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(List<String> indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<String> getPagamento() {
        return pagamento;
    }

    public void setPagamento(List<String> pagamento) {
        this.pagamento = pagamento;
    }

    public Role getRuolo() {
        return ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
