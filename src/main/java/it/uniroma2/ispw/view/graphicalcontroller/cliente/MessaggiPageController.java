package it.uniroma2.ispw.view.graphicalcontroller.cliente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.textexpt;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;


public class MessaggiPageController extends ControllerGrafico {
    private UserBean userBean;

    @FXML
    private ImageView carrelloID;

    @FXML
    private Button cercaID;

    @FXML
    private TableColumn<?, String> descrizione;

    @FXML
    private ImageView homeID;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private ImageView impID;

    @FXML
    private Button nextID;

    @FXML
    private Button onButtonBag;

    @FXML
    private Button onButtonBag1;

    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    private TableView<?> tableViewProdotto;

    @FXML
    void onCarrello(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/BuyClient.fxml", userBean);
        } catch (SystemException | textexpt e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onCercaProdotto(ActionEvent event) {
        //da fare

    }

    @FXML
    void onImpostazioni(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/AccountClient.fxml", userBean);
        } catch (SystemException | textexpt e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onMessaggi(ActionEvent event) {
        //dfa fare

    }

    @FXML
    void onPaginaPrincipale(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/HomeCliente.fxml", userBean);
        } catch (SystemException | textexpt e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onRemove(ActionEvent event) {
        //da fare

    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;

    }
}