package it.uniroma2.ispw.view.graphicalcontroller.cliente;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;

import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.TexText;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import java.io.IOException;


public class AccountClienteController extends ControllerGrafico {
    private UserBean userBean;
    @FXML
    private Button adress;

    @FXML
    private ImageView carreloID;

    @FXML
    private Label cognome;

    @FXML
    private Label datanascita;

    @FXML
    private ImageView homeID;

    @FXML
    private ImageView impID;

    @FXML
    private Label nome;

    @FXML
    private Button onButtonBag;

    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    private Button onPayment;

    @FXML
    private Button onViewOrder;
    @FXML
    private Label ruolo;

    @FXML
    private Label username;
    private final ClienteFacade clienteFacade = new ClienteFacade();

    public AccountClienteController() throws IOException {
        // da fare
    }

    @FXML
    void onViewOrder(ActionEvent event){
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/MyOrder.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }


    }
    @FXML
    void onAdress(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/AddressClient.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }

    }
    @FXML
    void onPayment(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/PayamentClient.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }

    @FXML
    void onCarrello(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/BuyClient.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());
        }
    }

    @FXML
    void onImpostazioni(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/AccountClient.fxml", userBean);
        } catch (SystemException | TexText | ItemNotFoundException e) {
            getAlert(e.getMessage());        }
    }

    @FXML
    void onPaginaPrincipale(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/HomeCliente.fxml", userBean);
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


    @Override
    public void inizializza(UserBean cred) throws SystemException, ItemNotFoundException {
        this.userBean = cred;
        UserBean usrBean;
        usrBean= clienteFacade.takeDati(cred);
        username.setText(usrBean.getEmail());
        ruolo.setText(String.valueOf(usrBean.getRuolo()));
        nome.setText(usrBean.getNome());
        cognome.setText(usrBean.getCognome());
        datanascita.setText(String.valueOf(usrBean.getAnnoNascita()));
    }
}
