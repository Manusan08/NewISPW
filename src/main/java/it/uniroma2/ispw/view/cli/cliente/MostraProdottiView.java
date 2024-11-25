package it.uniroma2.ispw.view.cli.cliente;

import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.ClienteFacade;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class MostraProdottiView {

    private List<ProdottoBean> prodottoBeans;
    private final ClienteFacade clienteFacade;
    private boolean loopCond = true;  // Controllo del ciclo
    private final UserBean usrBean;

    public MostraProdottiView(UserBean usrBean) throws IOException, SystemException {
        this.usrBean = usrBean;  // UserBean passato al costruttore
        clienteFacade = new ClienteFacade();
        prodottoBeans = clienteFacade.getAllProdDisp(); // Carica inizialmente i prodotti disponibili
    }

    public void control() throws SystemException, IOException, ItemNotFoundException, SQLException, CampiVuotiExeption, LoginException {
        do {
            // Mostra i prodotti
            printHeader("Elenco Prodotti Disponibili");
            printTable(prodottoBeans);

            // Aggiungi l'opzione per tornare alla ClienteView
            System.out.println("Seleziona un prodotto per aggiungerlo al carrello oppure premi '0' per tornare alla ClienteView.");

            // Chiedi la scelta dell'utente
            int choice = userChoice();

            if (choice == 0) {
                // Se l'utente seleziona 0, torna alla ClienteView
                System.out.println("Tornando alla ClienteView...");
                loopCond = false;  // Uscire dal ciclo
                new ClienteView(usrBean).control();  // Interrompe il ciclo e ritorna al controllo principale
            } else if (choice > 0 && choice <= prodottoBeans.size()) {
                // Se l'utente seleziona un prodotto valido
                ProdottoBean selectedProduct = prodottoBeans.get(choice - 1);
                // Aggiungi il prodotto al carrello
                addToCart(selectedProduct);

                // Aggiorna la lista dei prodotti disponibili
                prodottoBeans = clienteFacade.getAllProdDisp();
            } else {
                System.out.println("Selezione non valida. Riprova.");
            }

        } while (loopCond);  // Continua finché non si seleziona '0'
    }

    private void printHeader(String headerText) {
        int width = 50;
        String border = new String(new char[width]).replace("\0", "-");

        System.out.println();
        System.out.println(border);
        int leftPadding = (width - headerText.length()) / 2;
        for (int i = 0; i < leftPadding; i++) {
            System.out.print(" ");
        }
        System.out.println(headerText);
        System.out.println(border);
    }

    private int userChoice() {
        // Stampa il menu delle opzioni
        System.out.println("\n--- Come vuoi procedere? ---");
        System.out.println("1) Aggiungi al carrello");

        int choice = -1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("Scegli un prodotto (1-" + prodottoBeans.size() + ") o premi '0' per tornare: ");
            try {
                choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > prodottoBeans.size()) {
                    System.out.println("Selezione non valida. Riprova.");
                    choice = -1;  // Imposta un valore non valido
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Errore nell'input. Riprova.");
                choice = -1;
            }
        } while (choice == -1);  // Continua a chiedere finché non viene fatta una scelta valida

        return choice;
    }

    private void printTable(List<ProdottoBean> list) {
        if (list.isEmpty()) {
            System.out.println("\033[31mNessun prodotto disponibile.\033[0m");
            return;
        }

        // Stampa l'intestazione della tabella
        System.out.printf("\033[36m%-5s %-10s %-10s %-10s %-20s %-20s\033[0m\n", "ID", "Stato", "Prezzo", "ProdottoID", "Categoria", "NomeProdotto");

        // Stampa i prodotti numerati con il loro ID
        for (int i = 0; i < list.size(); i++) {
            ProdottoBean prodotto = list.get(i);
            System.out.printf("\033[32m%-5d %-10s %-10s %-10s %-20s %-20s\033[0m\n",
                    i + 1,  // Numerazione dei prodotti
                    prodotto.getStato(),
                    prodotto.getPrezzo(),
                    prodotto.getProdottoID(),
                    prodotto.getCategoria(),
                    prodotto.getNomeProdotto());
        }
    }

    private void addToCart(ProdottoBean prodotto) {
        // Aggiungi il prodotto al carrello
        clienteFacade.addToCart(prodotto, usrBean);
        System.out.println("Prodotto aggiunto al carrello: " + prodotto.getNomeProdotto());
    }
}