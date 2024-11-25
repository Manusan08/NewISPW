package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.bean.CouponBean;
import it.uniroma2.ispw.bean.OrdineBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.model.coupon.CouponModel;
import it.uniroma2.ispw.model.coupon.dao.CouponDAO;
import it.uniroma2.ispw.model.coupon.dao.CouponDAOFactory;
import it.uniroma2.ispw.model.coupon.dao.CouponDAOFactoryImpl;
import it.uniroma2.ispw.model.ordine.OrdineModel;
import it.uniroma2.ispw.model.ordine.dao.OrdineDAO;
import it.uniroma2.ispw.model.ordine.dao.OrdineDAOFactory;
import it.uniroma2.ispw.model.ordine.dao.OrdineDAOFactoryImpl;
import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.model.user.dao.UserDAO;
import it.uniroma2.ispw.model.user.dao.UserDAOFactory;
import it.uniroma2.ispw.model.user.dao.UserDAOFactoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestioneController {
    private final UserDAO userDAO;
    private final CouponDAO couponDAO;
    private final OrdineDAO ordineDAO;
    public GestioneController() throws IOException {

        UserDAOFactory daoFactory2 = new UserDAOFactoryImpl();
        userDAO = daoFactory2.getDao();
        CouponDAOFactory daoFactory = new CouponDAOFactoryImpl();
        couponDAO = daoFactory.getDao();
        OrdineDAOFactory daoFactory3 = new OrdineDAOFactoryImpl();
        ordineDAO = daoFactory3.getDao();
    }
    public UserBean takeDati(UserBean cred){
        UserModel userModel = new UserModel();
        userModel.setEmail(cred.getEmail());
        UserModel um = userDAO.takeDati(userModel);

        return new UserBean(um.getEmail(), um.getRuolo(), um.getNome(), um.getAnnoNascita(), um.getCognome());
    }

    public void salvaPay(String paymentInfo, UserBean userBean) {
        UserModel userM = new UserModel();
        userM.setEmail(userBean.getEmail());
        userM.setRuolo(userBean.getRuolo());

        userDAO.salvaPay(paymentInfo, userM);




    }

    public void salvaAddress(String indirizzoInfo, UserBean userBean) {
        UserModel userM = new UserModel();
        userM.setEmail(userBean.getEmail());
        userM.setRuolo(userBean.getRuolo());

        userDAO.salvaAddress(indirizzoInfo, userM);

    }



    public List<CouponBean> getCoupon(UserBean cred) {
        // Inizializziamo la lista dei modelli di coupon e impostiamo l'utente
        List<CouponModel> couponModels;
        UserModel userM = new UserModel();
        userM.setEmail(cred.getEmail());
        userM.setRuolo(cred.getRuolo());

        // Otteniamo i coupon dall'utente usando il DAO
        couponModels = couponDAO.getCoupon(userM);

        // Inizializziamo la lista di CouponBean per la vista
        List<CouponBean> couponBeans = new ArrayList<>();
        for (CouponModel couponModel : couponModels) {
            // Creiamo un nuovo CouponBean e lo popoliamo con i dati del CouponModel
            CouponBean couponBean = new CouponBean();
            couponBean.setNome(couponModel.getNome());
            couponBean.setCouponID(couponModel.getCouponID());

            // Aggiungiamo il CouponBean alla lista
            couponBeans.add(couponBean);
        }

        // Restituiamo la lista di CouponBean
        return couponBeans;
    }

    public List<OrdineBean> getMyOrder(UserBean cred) {
        List<OrdineModel> ordineModels;
        UserModel userM = new UserModel();
        userM.setEmail(cred.getEmail());
        userM.setRuolo(cred.getRuolo());

        ordineModels = ordineDAO.getMyOrder(userM);


        List<OrdineBean> ordineBeans = new ArrayList<>();
        for (OrdineModel ordineModel : ordineModels) {
            OrdineBean ordineBean = new OrdineBean();
            ordineBean.setOrdineID(ordineModel.getOrdineID());
            ordineBean.setMetodoDiPagamento(ordineModel.getPagamento());
            ordineBean.setIndirizzo(ordineModel.getIndirizzo());
            ordineBean.setPrezzoTOT(ordineModel.getPrezzotot());
            ordineBean.setDataAcquisto(ordineModel.getDataAcquisto());
            ordineBean.setCoupon(ordineModel.getCoupon());


            ordineBeans.add(ordineBean);
        }

        return ordineBeans;
    }

    public List<ProdottoBean> getMyOrderProd(UserBean userBean, OrdineBean ordineBean) {
        List<ProdottoBean> prodottoBeans = new ArrayList<>();
        UserModel userM = new UserModel();
        userM.setEmail(userBean.getEmail());
        OrdineModel ordineModel = new OrdineModel();
        ordineModel.setOrdineID(ordineBean.getOrdineID());
        for (ProdottoModel c : ordineDAO.getMyOrderProd(userM,ordineModel)) {
            ProdottoBean cb = new ProdottoBean(c.getProdottoID(), c.getNomeProdotto(), c.getPrezzo());

            prodottoBeans.add(cb);
        }

        return prodottoBeans;
    }
}
