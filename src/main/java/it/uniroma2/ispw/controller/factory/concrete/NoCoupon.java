package it.uniroma2.ispw.controller.factory.concrete;

import it.uniroma2.ispw.controller.factory.CouponInterfaceFactory;

public class NoCoupon implements CouponInterfaceFactory {
    @Override
    public double couponprize() {
        return 0.0;
    }
}
