package it.uniroma2.ispw.model.coupon.dao;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;




import java.io.IOException;

public class CouponDAOFactoryImpl implements CouponDAOFactory{
    public CouponDAO getDao() throws IOException {
        TypesOfPersistenceLayer typesOfPersistenceLayer = Conf.getConf().getTypesOfPersistenceLayer();
        switch (typesOfPersistenceLayer) {
            case JDBC -> {
                return new CouponDBMS();
            }
            case FILE_SYSTEM -> {
                return new CouponFS();
            }
            case null, default -> throw new IllegalArgumentException("typeOfPersistence Invalid, got " + typesOfPersistenceLayer);
        }
    }
}
