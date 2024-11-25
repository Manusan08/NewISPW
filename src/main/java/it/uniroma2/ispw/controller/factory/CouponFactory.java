package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.controller.factory.concrete.Coupon50;
import it.uniroma2.ispw.controller.factory.concrete.CouponFirst;
import it.uniroma2.ispw.controller.factory.concrete.NoCoupon;
import it.uniroma2.ispw.enums.Coupon;

public class CouponFactory {



    public CouponInterfaceFactory createCoupon(Coupon coupon, int prezzoTOT) {

        return switch (coupon) {
            case NO_COUPON -> new NoCoupon();
            case FIRST_COUPON -> new CouponFirst(prezzoTOT);
            case COUPON_5 -> new Coupon50(prezzoTOT);
            default -> null;
        };

    }


}