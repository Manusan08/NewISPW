package it.uniroma2.ispw.controller.factory.concrete;

import it.uniroma2.ispw.controller.factory.CouponInterfaceFactory;

public class Coupon50 implements CouponInterfaceFactory {

    public Coupon50(int prezzoTOT) {  // Costruttore che accetta prezzoTOT
    }
    @Override
    public double couponprize() {
        return 0;
    }
}
