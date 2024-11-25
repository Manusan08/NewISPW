package it.uniroma2.ispw.controller.factory.concrete;

import it.uniroma2.ispw.controller.factory.CouponInterfaceFactory;

public class CouponFirst implements CouponInterfaceFactory {
    private final int prezzoTOT;

    public CouponFirst(int prezzoTOT) {  // Costruttore che accetta prezzoTOT
        this.prezzoTOT = prezzoTOT;
    }
    @Override
    public double couponprize() {

        double sale;
        sale = (5* prezzoTOT)/100.0;
        if(sale > prezzoTOT){
            sale = 0;
        }
        return sale;
    }


}