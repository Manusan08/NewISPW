package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.bean.CarrelloBean;

import it.uniroma2.ispw.bean.OrdineBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;

import it.uniroma2.ispw.controller.factory.CouponFactory;
import it.uniroma2.ispw.enums.Coupon;
import it.uniroma2.ispw.model.carrello.CarrelloModel;
import it.uniroma2.ispw.model.carrello.dao.CarrelloDAO;
import it.uniroma2.ispw.model.carrello.dao.CarrelloDAOFactory;
import it.uniroma2.ispw.model.carrello.dao.CarrelloDAOFactoryImpl;
import it.uniroma2.ispw.model.ordine.OrdineModel;
import it.uniroma2.ispw.model.ordine.dao.OrdineDAO;
import it.uniroma2.ispw.model.ordine.dao.OrdineDAOFactory;
import it.uniroma2.ispw.model.ordine.dao.OrdineDAOFactoryImpl;
import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.prodotto.dao.ProdottoDAO;
import it.uniroma2.ispw.model.prodotto.dao.ProdottoDAOFactory;
import it.uniroma2.ispw.model.prodotto.dao.ProdottoDAOFactoryImpl;
import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.model.user.dao.UserDAO;
import it.uniroma2.ispw.model.user.dao.UserDAOFactory;
import it.uniroma2.ispw.model.user.dao.UserDAOFactoryImpl;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompraController {
    private final ProdottoDAO prodottoDAO;
    private final CarrelloDAO carrelloDAO;

    private final UserDAO userDAO;
    private final OrdineDAO ordineDAO;

    public CompraController() throws IOException {
        ProdottoDAOFactory daoFactory = new ProdottoDAOFactoryImpl();
        prodottoDAO = daoFactory.getDao();
        CarrelloDAOFactory daoFactory1 = new CarrelloDAOFactoryImpl();
        carrelloDAO = daoFactory1.getDao();
        UserDAOFactory daoFactory2 = new UserDAOFactoryImpl();
        userDAO = daoFactory2.getDao();
        OrdineDAOFactory daoFactory3 = new OrdineDAOFactoryImpl();
        ordineDAO = daoFactory3.getDao();
    }

    public void addToCart(ProdottoBean selectedProdotto, UserBean userBean) throws SystemException {
        ProdottoModel prodottoM = new ProdottoModel();
        prodottoM.setNomeProdotto(selectedProdotto.getProdottoID());
        prodottoM.setCategoria(selectedProdotto.getCategoria());
        prodottoM.setDescrizione(selectedProdotto.getDescrizione());
        prodottoM.setProdottoID(selectedProdotto.getProdottoID());
        prodottoM.setStato(selectedProdotto.getStato());
        UserModel userModel = new UserModel();
        userModel.setEmail(userBean.getEmail());
        prodottoDAO.cambiaStatoOccupato(prodottoM);
        carrelloDAO.addToCart(prodottoM, userModel);

    }

    public UserBean getModalita(UserBean cred) throws SystemException, ItemNotFoundException {
        UserModel userM = new UserModel();
        userM.setEmail(cred.getEmail());
        userM.setRuolo(cred.getRuolo());
        UserModel um= new UserModel(cred.getEmail(), cred.getRuolo());
        um.setPagamento(userDAO.takePagamento(userM));
        um.setIndirizzo(userDAO.takeIndirizzo(userM));

        return new UserBean(um.getEmail(), um.getRuolo(), um.getIndirizzo() , um.getPagamento());
    }

    public List<ProdottoBean> getAllProdDisp() throws SystemException, ItemNotFoundException {
        List<ProdottoBean> prodottoBeans = new ArrayList<>();
        for (ProdottoModel c : prodottoDAO.getAllProdDisp()) {
            ProdottoBean cb = new ProdottoBean(c.getProdottoID(), c.getNomeProdotto(), c.getPrezzo(), c.getCategoria(), c.getDescrizione());

            prodottoBeans.add(cb);
        }

        return prodottoBeans;
    }

    public CarrelloBean getAllMyCar(UserBean cred) throws SystemException {

        // Inizializziamo l'istanza di UserModel e CarrelloModel
        UserModel userModel = new UserModel();
        userModel.setEmail(cred.getEmail());

        CarrelloModel carrelloModel;
        carrelloModel = new CarrelloModel();
        carrelloModel.setEmailCliente(cred.getEmail());

        // Otteniamo l'elenco dei prodotti nel carrello dell'utente
        List<ProdottoModel> prodottiCarrello = carrelloDAO.getAllMyCar(userModel);
        carrelloModel.setProdottiID(prodottiCarrello);

        // Calcoliamo il prezzo totale sommando il prezzo di ogni prodotto, arrotondato come intero
        int prezzoTotale = prodottiCarrello.stream()
                .mapToInt(ProdottoModel::getPrezzo)
                .sum();
        carrelloModel.setPrezzoToT(prezzoTotale);

        // Creiamo la lista di ProdottoBean per il CarrelloBean
        List<ProdottoBean> prodottoBeans = new ArrayList<>();
        for (ProdottoModel prodotto : prodottiCarrello) {
            ProdottoBean prodottoBean = new ProdottoBean(
                    prodotto.getProdottoID(),
                    prodotto.getNomeProdotto(),
                    prodotto.getPrezzo(),
                    prodotto.getCategoria(),
                    prodotto.getDescrizione()
            );
            prodottoBeans.add(prodottoBean);
        }

        // Creiamo e popoliamo il CarrelloBean
        CarrelloBean carrelloBean = new CarrelloBean();
        carrelloBean.setEmailCliente(cred.getEmail());
        carrelloBean.setProdottiID(prodottoBeans);
        carrelloBean.setPrezzoTOT(prezzoTotale);

        // Restituiamo il CarrelloBean completo
        return carrelloBean;
    }


    public void removeToCart(ProdottoBean selectedProdotto, UserBean userBean) throws SystemException {
        ProdottoModel prodottoM = new ProdottoModel();
        prodottoM.setNomeProdotto(selectedProdotto.getProdottoID());
        prodottoM.setCategoria(selectedProdotto.getCategoria());
        prodottoM.setDescrizione(selectedProdotto.getDescrizione());
        prodottoM.setProdottoID(selectedProdotto.getProdottoID());
        prodottoM.setStato(selectedProdotto.getStato());
        UserModel userModel = new UserModel();
        userModel.setEmail(userBean.getEmail());
        prodottoDAO.cambiaStatoDisponibile(prodottoM);
        carrelloDAO.removeToCart(prodottoM, userModel);
    }


    public double returnCoupon(Coupon coupon, int prezzoTOT) {
        CouponFactory factoryCoupon = new CouponFactory();

        return prezzoTOT - factoryCoupon.createCoupon(coupon, prezzoTOT).couponprize();
    }

    public void compra(UserBean userBean, OrdineBean ordineBean) throws SystemException {
        // Inizializziamo l'istanza di UserModel e CarrelloModel
        UserModel userModel = new UserModel();
        userModel.setEmail(userBean.getEmail());

        // Creiamo una lista di ProdottoModel mappando i ProdottoBean di ordineBean
        List<ProdottoModel> prodottiModel = new ArrayList<>();
        for (ProdottoBean prodottoBean : ordineBean.getProdotti()) {
            ProdottoModel prodottoModel = new ProdottoModel(
                    prodottoBean.getProdottoID(),
                    prodottoBean.getNomeProdotto(),
                    prodottoBean.getPrezzo()

            );
            prodottiModel.add(prodottoModel);
        }

        OrdineModel ordineModel = new OrdineModel();
        ordineModel.setPagamento(ordineBean.getMetodoDiPagamento());
        ordineModel.setIndirizzo(ordineBean.getIndirizzo());
        ordineModel.setProdottiID(prodottiModel);  // Usa la lista di ProdottoModel creata sopra
        ordineModel.setEmailCliente(userBean.getEmail());
        ordineModel.setCoupon(ordineBean.getCoupon());
        ordineModel.setPrezzotot(ordineBean.getPrezzoTOT());

        // Eseguiamo l'operazione di acquisto
        ordineDAO.compra(ordineModel);
        carrelloDAO.svuotaCarrello(userModel);

        // Applichiamo cambiaStatoEsaurito a ogni prodotto nella lista
        for (ProdottoModel prodotto : prodottiModel) {
            prodottoDAO.cambiaStatoEsaurito(prodotto);
        }
    }

}

