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

import javafx.scene.control.Label;



public class AccountAdminController extends ControllerGrafico {
    private  UserBean userBean;

    @FXML
    private Label cognome;

    @FXML
    private Label datanascita;

    @FXML
    private Label nome;

    @FXML
    private Button onButtonBag;
    @FXML
    private Button buttond;



    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    private Label ruolo;

    @FXML
    private Label username;
    @FXML
    void onVendi(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Admin/VendiAdmin.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }
    @FXML
    private Button onButtonBag1;
    @FXML
    void onMessaggi(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/MessaggiPage.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }

    @FXML
    void onSconti(ActionEvent event) {
// da implementare
    }
    @FXML
    void onImpostazioni(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Admin/AccountAdmin.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }
    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;
    }
}
