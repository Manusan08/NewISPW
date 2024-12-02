package it.uniroma2.ispw.model.ordine.dao;

import it.uniroma2.ispw.model.ordine.OrdineModel;
import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface OrdineDAO {
    void compra(OrdineModel ordineModel);

    List<OrdineModel> getMyOrder(UserModel userM) throws SystemException;

    List<ProdottoModel> getMyOrderProd(UserModel userM, OrdineModel ordineModel) throws SystemException;
}
