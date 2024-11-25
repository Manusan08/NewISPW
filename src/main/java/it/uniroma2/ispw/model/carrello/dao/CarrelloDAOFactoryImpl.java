package it.uniroma2.ispw.model.carrello.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;



import java.io.IOException;

public class CarrelloDAOFactoryImpl implements CarrelloDAOFactory{
    public CarrelloDAO getDao() throws IOException {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new CarrelloDBMS();
            }
            case FILE_SYSTEM -> {
                return new CarrelloFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
