package it.uniroma2.ispw.view.graphicalcontroller.admin;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.textexpt;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class HomeAdminController extends ControllerGrafico {
    @FXML
    private ImageView impID;

    @FXML
    private Button onButtonBag;

    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    private ImageView scontiID;

    @FXML
    private ImageView vendiD;
    private UserBean userBean;

    @FXML
    void onImpostazioni(MouseEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Admin/AccountAdmin.fxml", userBean);
        } catch (SystemException | textexpt e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Button onButtonBag1;
    @FXML
    void onMessaggi(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/MessaggiPage.fxml", userBean);
        } catch (SystemException | textexpt e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSconti(MouseEvent event) {
        //da impelementare

    }

    @FXML
    void onVendi(MouseEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Admin/VendiAdmin.fxml", userBean);
        } catch (SystemException | textexpt e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;
    }
}
