package it.uniroma2.ispw.model.prodotto.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;


import java.io.IOException;

public class ProdottoDAOFactoryImpl implements  ProdottoDAOFactory{
    public ProdottoDAO getDao() throws IOException {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new ProdottoDBMS();
            }
            case FILE_SYSTEM -> {
                return new ProdottoFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
