package it.uniroma2.ispw.utils.facade;

import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.controller.CompraController;
import it.uniroma2.ispw.controller.GestioneController;
import it.uniroma2.ispw.enums.Coupon;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;

public class ClienteFacade {
    private final CompraController compraController;
    private final GestioneController gestioneController;

    public ClienteFacade() throws IOException {

        this.compraController = new CompraController();
        this.gestioneController = new GestioneController();
    }

    public void addToCart(ProdottoBean selectedProdotto, UserBean userBean) {
         this.compraController.addToCart(selectedProdotto, userBean);
    }

    public UserBean getModalita(UserBean cred) {

        return this.compraController.getModalita(cred);
    }

    public UserBean takeDati(UserBean cred) {

        return this.gestioneController.takeDati(cred);
    }

    public List<ProdottoBean> getAllProdDisp() throws SystemException {
        return  this.compraController.getAllProdDisp();
    }

    public CarrelloBean getAllMyCar(UserBean cred) {
        return this.compraController.getAllMyCar(cred);
    }

    public void removeToCart(ProdottoBean selectedProdotto, UserBean userBean) {
        this.compraController.removeToCart(selectedProdotto, userBean);
    }

    public void salvaPay(String paymentInfo, UserBean userBean) {
         this.gestioneController.salvaPay(paymentInfo, userBean);
    }

    public void salvaAddress(String indirizzoInfo, UserBean userBean) {
        this.gestioneController.salvaAddress( indirizzoInfo, userBean);
    }

    public List<CouponBean> getCoupon(UserBean cred) {
        return this.gestioneController.getCoupon(cred);
    }

    public double returnCoupon(Coupon coupon, int prezzoTOT) {
        return this.compraController.returnCoupon(coupon, prezzoTOT);
    }

    public void compra(UserBean userBean, OrdineBean ordineBean) {
        this.compraController.compra(userBean, ordineBean);
    }

    public List<OrdineBean> getMyOrder(UserBean cred) {
        return this.gestioneController.getMyOrder(cred);
    }

    public List<ProdottoBean> getMyOrderProd(UserBean userBean, OrdineBean ordineBean) {
        return this.gestioneController.getMyOrderProd(userBean,ordineBean);
    }
}
