package it.uniroma2.ispw.view.graphicalcontroller.cliente;



import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.exception.TexText;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.TextField;
        import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddressClienteController extends ControllerGrafico {
    private UserBean userBean;

    @FXML
    private Button buy1;

    @FXML
    private TextField cityId;

    @FXML
    private Button onButtonBag;

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
    private ChoiceBox<String> shipping;

    @FXML
    private ImageView shopID;

    @FXML
    private TextField textName;
    private final ClienteFacade clienteFacade = new ClienteFacade();

    public AddressClienteController() throws IOException {
        //da fare
    }

    @FXML
    void saveAddress(ActionEvent event) throws SystemException {
        // Ottieni i valori dai campi di testo
        String street = textName.getText();
        String city = cityId.getText();

        // Concatena i valori in una stringa formattata
        String indirizzoInfo = street + " - " + city;
        clienteFacade.salvaAddress(indirizzoInfo,userBean);

        try {
            // Salva le informazioni di pagamento


            // Ricarica la pagina corrente
            ChangePage.getChangePage().cambiaPagina("/view/Cliente/AddressClient.fxml", userBean);
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
            getAlert(e.getMessage());
        }
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

    @Override
    public void inizializza(UserBean cred) throws SystemException, ItemNotFoundException {
        this.userBean=cred;
        popolaChoiceBoxes(cred); // Popola i choice box con i valori dell'utente
    }
    private void popolaChoiceBoxes(UserBean cred) throws SystemException, ItemNotFoundException {

        userBean = clienteFacade.getModalita(cred);


        // Popola il ChoiceBox per gli indirizzi di spedizione
        if (userBean.getIndirizzo() != null) {
            ObservableList<String> indirizzi = FXCollections.observableArrayList(userBean.getIndirizzo());
            shipping.setItems(indirizzi);
        } else {
            shipping.setItems(FXCollections.observableArrayList()); // Imposta una lista vuota se non ci sono indirizzi
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
}
