package it.uniroma2.ispw.model.prodotto.dao;


import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface ProdottoDAO {
    public void cambiaStatoOccupato(ProdottoModel prodottoModel) throws SystemException;
    public void cambiaStatoEsaurito(ProdottoModel prodottoModel) throws SystemException;

    public void cambiaStatoDisponibile(ProdottoModel prodottoModel) throws SystemException;

   public List<ProdottoModel> getAllProdDisp() throws SystemException, ItemNotFoundException;


}
