package it.uniroma2.ispw.view.graphicalcontroller.cliente;


import it.uniroma2.ispw.bean.OrdineBean;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;

import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.TexText;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;


import java.util.List;


public class MyOrderClienteController extends ControllerGrafico {
    private UserBean userBean;

    @FXML
    private ImageView carrelloID;

    @FXML
    private ImageView homeID;

    @FXML
    private TableColumn<OrdineBean, String> id;

    @FXML
    private ImageView impID;

    @FXML
    private TableColumn<OrdineBean, String> nome;

    @FXML
    private Button onButtonBag;
    @FXML
    private Button info;


    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    private TableColumn<OrdineBean, Double> prezzo;

    @FXML
    private TableColumn<OrdineBean, String> prezzo1;

    @FXML
    private TableColumn<OrdineBean,String> prezzo2;

    @FXML
    private TableColumn<OrdineBean, String> prezzo3;

    @FXML
    private TableColumn<OrdineBean, String> prezzo4;

    @FXML
    private TableView<OrdineBean> tableViewProdotto;
    private final ClienteFacade clienteFacade = new ClienteFacade();

    public MyOrderClienteController() throws IOException {
        //da fare
    }
    @FXML
    void onInfo(ActionEvent event) {
        OrdineBean selectedOrdine = tableViewProdotto.getSelectionModel().getSelectedItem();
        if (selectedOrdine != null) {
            // Passa l'aula selezionata alla prossima pagina
            try {
                // Ottieni l'istanza di ChangePage e cambia la pagina
                ChangePage.getChangePage().cambiaPagina("/view/Cliente/MyOrderDettail.fxml", userBean, selectedOrdine);
            } catch (SystemException | TexText e) {
                throw new RuntimeException(e);
            }
        } else {
            showAlert("Errore","Per favore, seleziona un prodotto prima di procedere.");

        }


    }
    @FXML
    void onCarrello(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/BuyClient.fxml", userBean);
        } catch (SystemException | TexText e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onImpostazioni(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/AccountClient.fxml", userBean);
        } catch (SystemException | TexText e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onPaginaPrincipale(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/HomeCliente.fxml", userBean);
        } catch (SystemException | TexText e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;

        List<OrdineBean> ordineBeans;
        ordineBeans= clienteFacade.getMyOrder(cred);
        popolaTableView(ordineBeans);
    }

    private void popolaTableView(List<OrdineBean> ordineBeans) {
        ObservableList<OrdineBean> data = FXCollections.observableArrayList(ordineBeans);

        // Configura le colonne della TableView con i valori degli oggetti AulaBean

        id.setCellValueFactory(new PropertyValueFactory<>("ordineID"));

        prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzoTOT"));
        prezzo1.setCellValueFactory(new PropertyValueFactory<>("metodoDiPagamento"));
        prezzo2.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
        prezzo3.setCellValueFactory(new PropertyValueFactory<>("dataAcquisto"));
        prezzo4.setCellValueFactory(new PropertyValueFactory<>("coupon"));





        // Imposta i dati nella TableView
        tableViewProdotto.setItems(data);
    }
    @FXML
    private Button onButtonBag1;
    @FXML
    void onMessaggi(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/MessaggiPage.fxml", userBean);
        } catch (SystemException | TexText e) {
            throw new RuntimeException(e);
        }
    }
}
