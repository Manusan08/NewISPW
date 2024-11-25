package it.uniroma2.ispw.model.login.dao;



import java.io.IOException;

public interface LoginDAOFactory {
    public LoginDAO getDao() throws IOException;
}
