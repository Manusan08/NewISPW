package it.uniroma2.ispw;

import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.enums.TypesOfUIs;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {
    private static Conf conf = null;
    private TypesOfPersistenceLayer typesOfPersistenceLayer;
    private TypesOfUIs typesOfUIs;
    private Conf() {}

    public static Conf getConf() {
        if (conf == null) {
            conf = new Conf();
        }
        return conf;
    }

    public void setPersistenceLayer(TypesOfPersistenceLayer typesOfPersistenceLayer) {
        this.typesOfPersistenceLayer = typesOfPersistenceLayer;
    }

    public TypesOfPersistenceLayer getTypesOfPersistenceLayer() {
        return typesOfPersistenceLayer;
    }

    public TypesOfUIs getTypesOfUIs() {
        return typesOfUIs;
    }

    public void setTypesOfUIs(TypesOfUIs typesOfUIs) {
        this.typesOfUIs = typesOfUIs;
    }

    public void readConf() throws SystemException {
        try (InputStream input = Conf.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            //persistence layer
            if (properties.getProperty("persistence.layer").equals("FileSystem")) {
                conf.setPersistenceLayer(TypesOfPersistenceLayer.FILE_SYSTEM);
            } else {
                conf.setPersistenceLayer(TypesOfPersistenceLayer.JDBC);
            }

            //user interface
            if (properties.getProperty("ui").equals("javafx")) {
                conf.setTypesOfUIs(TypesOfUIs.JAVAFX);
            } else {
                conf.setTypesOfUIs(TypesOfUIs.CLI);
            }

        } catch (IOException e) {
            throw new SystemException(e.getMessage());
        }
    }


}
