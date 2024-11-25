package it.uniroma2.ispw.model.prodotto.dao;



import java.io.IOException;

public interface ProdottoDAOFactory {
    public ProdottoDAO getDao() throws IOException;
}
