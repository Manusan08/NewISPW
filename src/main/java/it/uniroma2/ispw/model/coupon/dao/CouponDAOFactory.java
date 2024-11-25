package it.uniroma2.ispw.model.coupon.dao;



import java.io.IOException;

public interface CouponDAOFactory {
    public CouponDAO getDao() throws IOException;
}
