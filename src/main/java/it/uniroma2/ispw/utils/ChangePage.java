package it.uniroma2.ispw.utils;

import it.uniroma2.ispw.Main;

import it.uniroma2.ispw.bean.OrdineBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;

import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.TexText;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.List;

public class ChangePage {

    private static ChangePage istanza = null;

    protected ChangePage() {
    }

    public static ChangePage getChangePage() {
        if (ChangePage.istanza == null) {
            ChangePage.istanza = new ChangePage();
        }
        return istanza;
    }

    private Stage stage;
    public void cambiaPagina(String s, UserBean userBean, List<ProdottoBean> selectedProdotto) throws SystemException, TexText {
        cambiaPagina(s, userBean,  selectedProdotto, null, null);
    }
    public void cambiaPagina(String s, UserBean userBean, OrdineBean ordineBean) throws SystemException, TexText {
        cambiaPagina(s, userBean,  null, null, ordineBean);
    }
    public void cambiaPagina(String s, UserBean userBean) throws SystemException, TexText {
        cambiaPagina(s, userBean, null, null, null);
    }

    public void cambiaPagina(String fxml, UserBean cred, List<ProdottoBean> prodottoBeans, ProdottoBean pb, OrdineBean ordineBean) throws SystemException, TexText {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = null;

        try {
            scene = new Scene(loader.load(), 900, 600);
        } catch (IOException e) {
            throw new TexText(e);
        }
        ControllerGrafico controller = loader.getController();    //Uso del polimorfismo, uso una variabile di tipo ControllerGrafico (superclasse)
        controller.inizializza(cred);
        if (prodottoBeans != null) {
            controller.setProdottoBeans(prodottoBeans);
        }
        if (pb != null) {
            controller.setProdottoBean(pb);
        }
        if (ordineBean != null) {
            controller.setOrdineBean(ordineBean);
        }
            this.stage.setScene(scene);                                  //l'operazione inizializza quindi avrà comportamenti diversi in base all'istanza
            this.stage.show();

        }


    public void setStage(Stage stageParam) {
        this.stage = stageParam;
    }

    public Stage getStage() {
        return this.stage;
    }


}

