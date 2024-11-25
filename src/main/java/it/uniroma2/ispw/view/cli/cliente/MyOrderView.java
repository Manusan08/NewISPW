package it.uniroma2.ispw.view.cli.cliente;

import it.uniroma2.ispw.bean.OrdineBean;
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

public class MyOrderView extends TemplateView {
    private final ClienteFacade clienteFacade;
    private final UserBean userBean;

    public MyOrderView(UserBean usrBean) throws IOException {
        this.userBean = usrBean;
        this.clienteFacade = new ClienteFacade();
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        // Recupero gli ordini del cliente usando ClienteFacade
        List<OrdineBean> ordineBeans = clienteFacade.getMyOrder(userBean);

        // Mostro gli ordini se ce ne sono
        if (ordineBeans.isEmpty()) {
            System.out.println("Non hai nessun ordine.");
        } else {
            showOrders(ordineBeans);
        }

        // Opzioni per l'interazione con l'utente
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scegli un'opzione:");
        System.out.println("1. Visualizza i dettagli di un ordine");
        System.out.println("2. Torna al menu principale");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Permette all'utente di scegliere un ordine per vedere i dettagli
                System.out.print("Inserisci l'ID dell'ordine che desideri visualizzare: ");
                String orderId = scanner.next();
                OrdineBean ordineBean = new OrdineBean();
                ordineBean.setOrdineID(orderId);
                viewOrderDetails(userBean, ordineBean);
                break;
            case 2:
                // Torna al menu principale
                System.out.println("Tornando al menu principale...");
                // Logica per tornare al menu principale
                break;
            default:
                System.out.println("Opzione non valida. Riprova.");
        }
    }

    private void showOrders(List<OrdineBean> ordineBeans) {
        // Visualizza gli ordini nel terminale
        System.out.println("I tuoi ordini:");
        for (OrdineBean ordine : ordineBeans) {
            System.out.println("ID Ordine: " + ordine.getOrdineID());
            System.out.println("Prezzo Totale: " + ordine.getPrezzoTOT());
            System.out.println("Metodo di Pagamento: " + ordine.getMetodoDiPagamento());
            System.out.println("Indirizzo di spedizione: " + ordine.getIndirizzo());
            System.out.println("Data di acquisto: " + ordine.getDataAcquisto());
            System.out.println("Coupon: " + ordine.getCoupon());
            System.out.println("------------------------------");
        }
    }

    private void viewOrderDetails(UserBean userBean, OrdineBean ordine) {




        List<ProdottoBean> prodottoBeans = clienteFacade.getMyOrderProd(userBean, ordine);

                // Popola la TableView con i dettagli dei prodotti
                popolaTableView(prodottoBeans);

        }

// Funzione per popolare la lista di prodotti associati all'ordine
        private void popolaTableView(List<ProdottoBean> prodottoBeans) {
            // Stampa i dettagli dei prodotti
            System.out.println("Prodotti nell'ordine:");

            // Per ogni prodotto, stampiamo le informazioni
            for (ProdottoBean prodotto : prodottoBeans) {
                System.out.println("ID Prodotto: " + prodotto.getProdottoID());
                System.out.println("Nome Prodotto: " + prodotto.getNomeProdotto());
                System.out.println("Prezzo: " + prodotto.getPrezzo());
                System.out.println("------------------------------");
            }
        }




    @Override
    protected List<String> getOptions() {
        return Collections.emptyList(); // Non necessario nel contesto CLI
    }

    @Override
    protected String getHeader() {
        return "I Miei Ordini"; // Intestazione semplice per la visualizzazione CLI
    }
}
