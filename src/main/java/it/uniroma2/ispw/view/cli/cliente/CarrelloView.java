package it.uniroma2.ispw.view.cli.cliente;

import it.uniroma2.ispw.bean.CarrelloBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import it.uniroma2.ispw.view.cli.TemplateView;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CarrelloView extends TemplateView {
    private UserBean userBean;
    private List<ProdottoBean> prodotti;
    private final ClienteFacade clienteFacade;
    private CarrelloBean carrelloBean;
    private Scanner scanner = new Scanner(System.in);

    public CarrelloView(UserBean usrBean) throws IOException {
        this.userBean = usrBean;
        clienteFacade = new ClienteFacade();
    }

    @Override
    public void control() throws SystemException, IOException, ItemNotFoundException, SQLException, CampiVuotiExeption, LoginException {
        // Carica i dati del carrello
        carrelloBean = clienteFacade.getAllMyCar(userBean);
        prodotti = carrelloBean.getProdottiID();

        // Stampa i prodotti nel carrello
        printCarrello(prodotti);

        // Menu di opzioni
        int scelta = -1;
        while (scelta != 0) {
            System.out.println("\n\u001B[35mScegli un'opzione:\u001B[0m"); // Viola per il titolo
            System.out.println("\u001B[33m0 - Torna alla ClienteView\u001B[0m"); // Giallo per opzioni
            System.out.println("\u001B[33m1 - Acquista\u001B[0m");
            System.out.println("\u001B[33m2 - Modifica carrello\u001B[0m");
            System.out.print("\u001B[36mScelta: \u001B[0m"); // Ciano per richiesta di input
            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 0:
                    // Torna alla ClienteView
                    cambiaVistaCliente();
                    break;

                case 1:
                    // Acquista il carrello
                    acquistoCarrello(prodotti);

                    break;

                case 2:
                    // Modifica il carrello
                    modificaCarrello();
                    break;

                default:
                    System.out.println("\u001B[31mOpzione non valida. Riprova.\u001B[0m"); // Rosso per errore
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return Collections.emptyList(); // Non sono necessarie opzioni extra, tutte gestite tramite `control()`
    }

    @Override
    protected String getHeader() {
        return "Carrello Cliente"; // Titolo della view
    }

    private void printCarrello(List<ProdottoBean> prodotti) {
        if (prodotti.isEmpty()) {
            System.out.println("\u001B[31mIl tuo carrello è vuoto.\u001B[0m"); // Rosso per carrello vuoto
        } else {
            System.out.println("\u001B[34mProdotti nel tuo carrello:\u001B[0m"); // Blu per il titolo
            for (int i = 0; i < prodotti.size(); i++) {
                ProdottoBean prodotto = prodotti.get(i);
                // Colore verde per il nome del prodotto e blu per il prezzo
                System.out.println("\u001B[32m" + (i + 1) + ". " + prodotto.getNomeProdotto() + " - \u001B[34m" + prodotto.getPrezzo());
            }
            // Colore giallo per il totale
            System.out.println("\u001B[33mTotale: " + carrelloBean.getPrezzoTOT() + "£\u001B[0m");
        }
    }

    private void cambiaVistaCliente() throws SystemException, IOException, SQLException, CampiVuotiExeption, LoginException, ItemNotFoundException {
        // Implementa la logica per tornare alla ClienteView
        System.out.println("\u001B[36mTornando alla ClienteView...\u001B[0m");
        new ClienteView(userBean).control();
    }

    private void acquistoCarrello(List<ProdottoBean> prodotti) throws SystemException, SQLException, CampiVuotiExeption, LoginException, IOException, ItemNotFoundException {
        // Passa la lista dei prodotti al costruttore di OrdineView
        OrdineView ordineView = new OrdineView(userBean, prodotti);
        ordineView.control();
        System.out.println("\u001B[32mCarrello acquistato con successo!\u001B[0m");

    }

    private void modificaCarrello() {
        if (prodotti.isEmpty()) {
            System.out.println("\u001B[31mNon puoi modificare un carrello vuoto.\u001B[0m");
        } else {
            // Stampa la lista dei prodotti nel carrello
            System.out.println("\u001B[34mSeleziona il prodotto da rimuovere:\u001B[0m"); // Blu per il titolo
            for (int i = 0; i < prodotti.size(); i++) {
                ProdottoBean prodotto = prodotti.get(i);
                System.out.println("\u001B[32m" + (i + 1) + ". " + prodotto.getNomeProdotto() + " - \u001B[34mID: " + prodotto.getProdottoID() + " - Prezzo: " + prodotto.getPrezzo() + "£\u001B[0m");
            }
            System.out.println("\u001B[33m0 - Torna alla gestione carrello\u001B[0m");

            // Legge la scelta dell'utente
            int scelta = -1;
            try {
                System.out.print("\u001B[36mInserisci l'ID del prodotto da eliminare: \u001B[0m"); // Ciano per richiesta di input
                scelta = Integer.parseInt(scanner.nextLine());

                if (scelta == 0) {
                    // Torna alla gestione del carrello
                    control(); // Questo chiama il metodo di controllo per ristampare il carrello
                } else if (scelta > 0 && scelta <= prodotti.size()) {
                    // Elimina il prodotto selezionato
                    ProdottoBean prodottoDaRimuovere = prodotti.get(scelta - 1);
                    clienteFacade.removeToCart(prodottoDaRimuovere, userBean);
                    System.out.println("\u001B[32mProdotto rimosso: " + prodottoDaRimuovere.getNomeProdotto() + "\u001B[0m");
                    // Ricarica il carrello aggiornato
                    carrelloBean = clienteFacade.getAllMyCar(userBean);
                    prodotti = carrelloBean.getProdottiID();
                    modificaCarrello(); // Ristampa il carrello aggiornato
                } else {
                    System.out.println("\u001B[31mID prodotto non valido. Riprova.\u001B[0m");
                    modificaCarrello(); // Riprova
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInput non valido. Per favore, inserisci un numero.\u001B[0m");
                modificaCarrello(); // Riprova
            } catch (SQLException | ItemNotFoundException | SystemException e) {
                System.out.println("\u001B[31mErrore durante la modifica del carrello: " + e.getMessage() + "\u001B[0m");
            } catch (CampiVuotiExeption | IOException | LoginException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
