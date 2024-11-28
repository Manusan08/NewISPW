package it.uniroma2.ispw.view.graphicalcontroller.cliente;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;

import java.io.IOException;



public class PaymentClienteController extends ControllerGrafico {
    private UserBean userBean;
        @FXML
        private Button buy1;

        @FXML
        private TextField cardID;

        @FXML
        private ImageView carrelloID;

        @FXML
        private ImageView homeID;

        @FXML
        private TextField nameID;

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
        private ChoiceBox<String> card;
    private final ClienteFacade clienteFacade = new ClienteFacade();

    public PaymentClienteController() throws IOException {
        //da fare
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

        @FXML
        void onSave(ActionEvent event) {

                // Ottieni i valori dai campi di testo
                String name = nameID.getText();
                String cardNumber = cardID.getText();

                // Concatena i valori in una stringa formattata
                String paymentInfo = name + " - " + cardNumber;
                clienteFacade.salvaPay(paymentInfo,userBean);

                try {
                    // Salva le informazioni di pagamento


                    // Ricarica la pagina corrente
                    ChangePage.getChangePage().cambiaPagina("/view/Cliente/PayamentClient.fxml", userBean);
                } catch (SystemException | TexText e) {
                    throw new RuntimeException(e);
                }
            }


    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
        popolaChoiceBoxes(cred); // Popola i choice box con i valori dell'utente
    }
    private void popolaChoiceBoxes(UserBean cred) {

        userBean = clienteFacade.getModalita(cred);


        // Popola il ChoiceBox per i metodi di pagamento
        if (userBean.getPagamento() != null) {
            ObservableList<String> pagamenti = FXCollections.observableArrayList(userBean.getPagamento());
            card.setItems(pagamenti);
        } else {
            card.setItems(FXCollections.observableArrayList()); // Imposta una lista vuota se non ci sono pagamenti
        }
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


