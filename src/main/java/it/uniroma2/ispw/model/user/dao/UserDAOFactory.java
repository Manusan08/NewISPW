package it.uniroma2.ispw.model.user.dao;



import java.io.IOException;

public interface UserDAOFactory {
    public UserDAO getDao() throws IOException;
}
