package it.uniroma2.ispw.model.prodotto.dao;


import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface ProdottoDAO {
    public void cambiaStatoOccupato(ProdottoModel prodottoModel);
    public void cambiaStatoEsaurito(ProdottoModel prodottoModel);

    public void cambiaStatoDisponibile(ProdottoModel prodottoModel);

   public List<ProdottoModel> getAllProdDisp() throws SystemException;


}
