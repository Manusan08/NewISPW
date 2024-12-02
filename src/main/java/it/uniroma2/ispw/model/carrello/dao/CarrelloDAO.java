package it.uniroma2.ispw.model.carrello.dao;


import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface CarrelloDAO {
    public void addToCart(ProdottoModel prodottoModel, UserModel userModel) throws SystemException;
    List<ProdottoModel> getAllMyCar(UserModel cred) throws SystemException;

    void removeToCart(ProdottoModel prodottoM, UserModel userModel) throws SystemException;

    void svuotaCarrello(UserModel userModel) throws SystemException;
}
