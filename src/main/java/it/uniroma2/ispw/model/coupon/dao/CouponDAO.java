package it.uniroma2.ispw.model.coupon.dao;

import it.uniroma2.ispw.model.coupon.CouponModel;
import it.uniroma2.ispw.model.user.UserModel;

import java.util.List;

public interface CouponDAO {
    List<CouponModel> getCoupon(UserModel cred);
}
