package it.uniroma2.ispw.model.carrello.dao;

import java.io.IOException;

public interface CarrelloDAOFactory {
    public CarrelloDAO getDao() throws IOException;
}

