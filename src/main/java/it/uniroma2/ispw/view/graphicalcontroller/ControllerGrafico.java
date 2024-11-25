package it.uniroma2.ispw.view.graphicalcontroller;


import it.uniroma2.ispw.bean.OrdineBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.AlertUtil;

import it.uniroma2.ispw.utils.exception.SystemException;


import java.util.List;


public abstract class ControllerGrafico extends AlertUtil {


    public abstract void inizializza(UserBean cred) throws SystemException;
    public void setProdottoBeans(List<ProdottoBean> prodottoBeans) {

    }


    public void setProdottoBean(ProdottoBean pb) {
    }

    public void setOrdineBean(OrdineBean ordineBean) {
    }
}
/*Uso del polimorfismo per istanziare i vari controller grafici, in particolare tutti i controller grafici
  che devono essere aggiornati in base all'utente estendono tale classe implementando il metodo inizializza
 */