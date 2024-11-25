package it.uniroma2.ispw.model.ordine.dao;

import it.uniroma2.ispw.model.ordine.OrdineModel;
import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;
import java.util.Collections;
import java.util.Collections;

import java.util.List;

public class OrdineFS implements OrdineDAO{
    @Override
    public void compra(OrdineModel ordineModel) {
        //da fare


    }

    @Override
    public List<OrdineModel> getMyOrder(UserModel userM) {
        return Collections.emptyList();
    }

    @Override
    public List<ProdottoModel> getMyOrderProd(UserModel userM, OrdineModel ordineModel) {
        return Collections.emptyList();
    }
}
