package it.uniroma2.ispw.view.graphicalcontroller.admin;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;

import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.TexText;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class SellAdminController extends ControllerGrafico {
    private  UserBean userBean;

    @FXML
    private Button expose;

    @FXML
    private Button expose1;

    @FXML
    private Button onButtonBag;

    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    void onImpostazioni(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Admin/AccountAdmin.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }

    @FXML
    void expose(ActionEvent event) {
 //da impelementare
    }

    @FXML
    void expose2(ActionEvent event) {
        //da impelementare

    }

    @FXML
    void onSconti(ActionEvent event) {
        //da impelementare

    }

    @FXML
    void onVendi(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Admin/VendiAdmin.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
    }
}
