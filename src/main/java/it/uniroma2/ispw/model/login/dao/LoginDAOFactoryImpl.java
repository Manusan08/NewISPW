package it.uniroma2.ispw.model.login.dao;


import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;

import java.io.IOException;


public class LoginDAOFactoryImpl implements LoginDAOFactory {

    public LoginDAO getDao() throws IOException {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new LoginDBMS();
            }
            case FILE_SYSTEM -> {
                return new LoginFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
