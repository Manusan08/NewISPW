package it.uniroma2.ispw.model.ordine.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;


import java.io.IOException;

public class OrdineDAOFactoryImpl implements OrdineDAOFactory {
    public OrdineDAO getDao() throws IOException {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new OrdineDBMS();
            }
            case FILE_SYSTEM -> {
                return new OrdineFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
