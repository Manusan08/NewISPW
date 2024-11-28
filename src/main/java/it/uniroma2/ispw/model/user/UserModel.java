package it.uniroma2.ispw.model.user;

import it.uniroma2.ispw.enums.Role;


import java.util.List;

public class UserModel {
    private String email;

    private Role ruolo;

    private String annoNascita;

    private List<String> indirizzo;
    private List<String> pagamento;
    private String nome;
    private String cognome;

    public UserModel(String email, Role ruolo) {
        this.email = email;
        this.ruolo = ruolo;
    }

    public UserModel() {


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRuolo() {
        return ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    public String getAnnoNascita() {
        return annoNascita;
    }

    public void setAnnoNascita(String annoNascita) {
        this.annoNascita = annoNascita;
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
}
