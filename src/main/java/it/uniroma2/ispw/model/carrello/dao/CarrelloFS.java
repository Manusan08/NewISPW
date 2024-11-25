package it.uniroma2.ispw.model.carrello.dao;

import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;
import java.util.Collections;

import java.util.List;

public class CarrelloFS implements CarrelloDAO{
    @Override
    public void addToCart(ProdottoModel prodottoModel, UserModel userModel) {
//da fare
    }

    @Override
    public List<ProdottoModel> getAllMyCar(UserModel cred) {

        //da fare
        return Collections.emptyList();
    }

    @Override
    public void removeToCart(ProdottoModel prodottoM, UserModel userModel) {
//da fare

    }

    @Override
    public void svuotaCarrello(UserModel userModel) {
//da fare

    }
}
