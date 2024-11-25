package it.uniroma2.ispw.model.ordine.dao;

import java.io.IOException;

public interface OrdineDAOFactory {
    public OrdineDAO getDao() throws IOException;
}

