package it.uniroma2.ispw.utils.facade;

import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.controller.CompraController;
import it.uniroma2.ispw.controller.GestioneController;
import it.uniroma2.ispw.enums.Coupon;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
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

    public void addToCart(ProdottoBean selectedProdotto, UserBean userBean) throws SystemException {
         this.compraController.addToCart(selectedProdotto, userBean);
    }

    public UserBean getModalita(UserBean cred) throws SystemException, ItemNotFoundException {

        return this.compraController.getModalita(cred);
    }

    public UserBean takeDati(UserBean cred) throws SystemException, ItemNotFoundException {

        return this.gestioneController.takeDati(cred);
    }

    public List<ProdottoBean> getAllProdDisp() throws SystemException, ItemNotFoundException {
        return  this.compraController.getAllProdDisp();
    }

    public CarrelloBean getAllMyCar(UserBean cred) throws SystemException {
        return this.compraController.getAllMyCar(cred);
    }

    public void removeToCart(ProdottoBean selectedProdotto, UserBean userBean) throws SystemException {
        this.compraController.removeToCart(selectedProdotto, userBean);
    }

    public void salvaPay(String paymentInfo, UserBean userBean) throws SystemException {
         this.gestioneController.salvaPay(paymentInfo, userBean);
    }

    public void salvaAddress(String indirizzoInfo, UserBean userBean) throws SystemException {
        this.gestioneController.salvaAddress( indirizzoInfo, userBean);
    }

    public List<CouponBean> getCoupon(UserBean cred) throws SystemException {
        return this.gestioneController.getCoupon(cred);
    }

    public double returnCoupon(Coupon coupon, int prezzoTOT) {
        return this.compraController.returnCoupon(coupon, prezzoTOT);
    }

    public void compra(UserBean userBean, OrdineBean ordineBean) throws SystemException {
        this.compraController.compra(userBean, ordineBean);
    }

    public List<OrdineBean> getMyOrder(UserBean cred) throws SystemException {
        return this.gestioneController.getMyOrder(cred);
    }

    public List<ProdottoBean> getMyOrderProd(UserBean userBean, OrdineBean ordineBean) throws SystemException {
        return this.gestioneController.getMyOrderProd(userBean,ordineBean);
    }
}
