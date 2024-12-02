package it.uniroma2.ispw.view.graphicalcontroller.cliente;

import it.uniroma2.ispw.bean.OrdineBean;
import it.uniroma2.ispw.bean.ProdottoBean;
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

public class MyOrderDetClienteController extends ControllerGrafico {
    private UserBean userBean;
    private List<ProdottoBean> prodottoBeans;

    @FXML
    private ImageView carrelloID;

    @FXML
    private ImageView homeID;

    @FXML
    private TableColumn<ProdottoBean, String> id;

    @FXML
    private ImageView impID;

    @FXML
    private TableColumn<ProdottoBean, String> nome;

    @FXML
    private Button onButtonBag;

    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;

    @FXML
    private TableColumn<?, ?> ordineID;

    @FXML
    private TableColumn<ProdottoBean, Integer> prezzo;

    @FXML
    private TableView<ProdottoBean> tableViewProdotto;
    private final ClienteFacade clienteFacade = new ClienteFacade();

    public MyOrderDetClienteController() throws IOException {
        // da fare
    }

    @FXML
    void onCarrello(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/BuyClient.fxml", userBean);
        } catch (SystemException | TexText e) {
            getAlert(e.getMessage());
        }
    }

    @FXML
    void onImpostazioni(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/AccountClient.fxml", userBean);
        } catch (SystemException | TexText e) {
            getAlert(e.getMessage());        }
    }

    @FXML
    void onPaginaPrincipale(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/HomeCliente.fxml", userBean);
        } catch (SystemException | TexText e) {
            getAlert(e.getMessage());
        }
    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;


    }

    @Override
    public void setOrdineBean(OrdineBean ordineBean){


        prodottoBeans=clienteFacade.getMyOrderProd(userBean, ordineBean);

        popolaTableView();
    }
    private void popolaTableView() {
        ObservableList<ProdottoBean> data = FXCollections.observableArrayList(prodottoBeans);

        // Configura le colonne della TableView con i valori degli oggetti AulaBean

        id.setCellValueFactory(new PropertyValueFactory<>("prodottoID"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nomeProdotto"));
        prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));




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
            getAlert(e.getMessage());
        }
    }

}
