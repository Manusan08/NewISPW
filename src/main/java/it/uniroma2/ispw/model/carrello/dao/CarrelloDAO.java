package it.uniroma2.ispw.model.carrello.dao;


import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;

import java.util.List;

public interface CarrelloDAO {
    public void addToCart(ProdottoModel prodottoModel, UserModel userModel);
    List<ProdottoModel> getAllMyCar(UserModel cred);

    void removeToCart(ProdottoModel prodottoM, UserModel userModel);

    void svuotaCarrello(UserModel userModel);
}
