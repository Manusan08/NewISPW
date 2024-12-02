package it.uniroma2.ispw.view.graphicalcontroller.cliente;
import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.enums.Coupon;
import it.uniroma2.ispw.utils.ChangePage;

import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.TexText;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class BuyClienteController extends ControllerGrafico {
    private UserBean userBean;
    private List<ProdottoBean> prodottoBeans;
    @FXML
    private Button buy1;

    @FXML
    private ChoiceBox<String> card;

    @FXML
    private ImageView carrelloID;

    @FXML
    private ChoiceBox<String> coupon;

    @FXML
    private ImageView homeID;


    @FXML
    private ImageView impID;

    @FXML
    private ChoiceBox<String> methodShip;


    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button onButtonBag;
    @FXML
    private Button remove;


    @FXML
    private Button onButtonHome;

    @FXML
    private Button onButtonOut;

    @FXML
    private Button onButtonSet;



    @FXML
    private Text prize;

    @FXML
    private Text prize1;

    @FXML
    private Text prize11;

    @FXML
    private Text prize12;

    @FXML
    private Text prize123;

    @FXML
    private Text prize21;

    @FXML
    private ChoiceBox<String> shipping1;

    @FXML
    private TableView<ProdottoBean> tableViewProdotto;

    @FXML
    private TableColumn<ProdottoBean, String > id;
    @FXML
    private TableColumn<ProdottoBean, String > nome;


    @FXML
    private TableColumn<ProdottoBean, Integer > prezzo;

    @FXML
    private TableColumn<ProdottoBean, String  > descrizione;
    private final ClienteFacade clienteFacade = new ClienteFacade();

    public BuyClienteController() throws IOException {
        //da fare
    }

    @FXML
    void buyAllIntoCart(ActionEvent event) {
        // Crea un nuovo oggetto OrdineBean
        OrdineBean ordineBean = new OrdineBean();

        // Imposta la lista di prodotti
        ordineBean.setProdotti(prodottoBeans);

        // Ottieni il metodo di pagamento selezionato
        String metodoDiPagamento = card.getValue();
        if (metodoDiPagamento == null) {
            showAlert("Errores", "Per favore, seleziona un metodo di pagamento.");
            return;
        }
        ordineBean.setMetodoDiPagamento(metodoDiPagamento);

        // Ottieni l'indirizzo selezionato
        String indirizzo = shipping1.getValue();
        if (indirizzo == null) {
            showAlert("Errore", "Per favore, seleziona un indirizzo di spedizione.");
            return;
        }
        ordineBean.setIndirizzo(indirizzo);

        // Ottieni il coupon selezionato (se presente)
        String couponSelezionato = coupon.getValue();
        ordineBean.setCoupon(couponSelezionato != null ? couponSelezionato : "");

        // Imposta il prezzo totale
        ordineBean.setPrezzoTOT(Double.parseDouble(totalPriceLabel.getText().replace("£", "")));

        // Mostra un pop-up di conferma con i dettagli dell'ordine
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Ordine");
        alert.setHeaderText("Confermi l'ordine con le seguenti informazioni?");

        // Riepilogo dei dettagli dell'ordine
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Prodotti:\n");
        for (ProdottoBean prodotto : prodottoBeans) {
            orderDetails.append("- ").append(prodotto.getNomeProdotto()).append(" : £").append(prodotto.getPrezzo()).append("\n");
        }
        orderDetails.append("\nMetodo di Pagamento: ").append(metodoDiPagamento);
        orderDetails.append("\nIndirizzo di Spedizione: ").append(indirizzo);
        orderDetails.append("\nCoupon: ").append(couponSelezionato != null ? couponSelezionato : "Nessuno");
        orderDetails.append("\nPrezzo Totale: £").append(ordineBean.getPrezzoTOT());

        alert.setContentText(orderDetails.toString());
        clienteFacade.compra(userBean, ordineBean);
        // Mostra la finestra e attendi la conferma dell'utente
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                // Se l'utente conferma, cambia pagina su processaOrdine.fxml
                ChangePage.getChangePage().cambiaPagina("/view/HomeCliente.fxml",userBean, ordineBean);
            } catch (SystemException | TexText e) {
                getAlert(e.getMessage());
            }
        }

        }

    @Override
    public void setProdottoBeans(List<ProdottoBean> prodottoBeans) {
        this.prodottoBeans = prodottoBeans;
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
         // Popola i choice box con i valori dell'utente


        CarrelloBean carrelloBean;
        carrelloBean  = clienteFacade.getAllMyCar(cred);
        prodottoBeans=carrelloBean.getProdottiID();
        setProdottoBeans(prodottoBeans);

        totalPriceLabel.setText(carrelloBean.getPrezzoTOT() + "£");
        popolaChoiceBoxes(cred, carrelloBean.getPrezzoTOT());

    }

    private void popolaChoiceBoxes(UserBean cred, int prezzoTOT) {

        userBean = clienteFacade.getModalita(cred);
        // Popola il ChoiceBox per i metodi di pagamento
        if (userBean.getPagamento() != null) {
            ObservableList<String> pagamenti = FXCollections.observableArrayList(userBean.getPagamento());
            card.setItems(pagamenti);
        } else {
            card.setItems(FXCollections.observableArrayList()); // Imposta una lista vuota se non ci sono pagamenti
        }

        // Popola il ChoiceBox per gli indirizzi di spedizione
        if (userBean.getIndirizzo() != null) {
            ObservableList<String> indirizzi = FXCollections.observableArrayList(userBean.getIndirizzo());
            shipping1.setItems(indirizzi);
        } else {
            shipping1.setItems(FXCollections.observableArrayList()); // Imposta una lista vuota se non ci sono indirizzi
        }

        List<CouponBean> couponBeans ;
        List<String> couponNames = new ArrayList<>();
        couponBeans = clienteFacade.getCoupon(cred);
        for (CouponBean couponBean : couponBeans) {
            couponNames.add(couponBean.getNome());
        }

        // Popola il ChoiceBox per i coupon
        ObservableList<String> couponList = FXCollections.observableArrayList(couponNames);
        coupon.setItems(couponList);
// Aggiungi il ChangeListener per rilevare quando viene selezionato un coupon
        coupon.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            CouponBean couponBean;
            couponBean = new CouponBean();
            couponBean.setType(coupon.getValue());

            double prizeCouponvalue1 = 0;
            prizeCouponvalue1 = clienteFacade.returnCoupon(Coupon.fromString(couponBean.getType()),prezzoTOT);


            // Aggiorna l’etichetta del prezzo totale
            totalPriceLabel.setText(prizeCouponvalue1 + "£");

        });
    }




    public void removeItem() {
        ProdottoBean selectedProdotto = tableViewProdotto.getSelectionModel().getSelectedItem();
        if (selectedProdotto != null) {
            // Passa l'aula selezionata alla prossima pagina
            clienteFacade.removeToCart(selectedProdotto, userBean);
            try {
                // Ottieni l'istanza di ChangePage e cambia la pagina
                ChangePage.getChangePage().cambiaPagina("/view/Cliente/BuyClient.fxml", userBean);
            } catch (SystemException | TexText e) {
                getAlert(e.getMessage());
            }
        } else {
            showAlert("Errore","Per favore, seleziona un prodotto prima di procedere.");

        }
    } @FXML
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
