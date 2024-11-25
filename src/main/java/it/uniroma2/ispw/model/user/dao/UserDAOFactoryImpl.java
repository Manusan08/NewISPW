package it.uniroma2.ispw.model.user.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;


import java.io.IOException;

public class UserDAOFactoryImpl implements UserDAOFactory {
    public UserDAO getDao() throws IOException {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new UserDBMS();
            }
            case FILE_SYSTEM -> {
                return new UserFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
