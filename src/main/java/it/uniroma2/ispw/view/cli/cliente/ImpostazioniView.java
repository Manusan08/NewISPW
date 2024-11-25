package it.uniroma2.ispw.view.cli.cliente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import it.uniroma2.ispw.view.cli.TemplateView;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class ImpostazioniView extends TemplateView {
    private UserBean userBean;
    private final ClienteFacade clienteFacade;

    public ImpostazioniView(UserBean usrBean) throws IOException {
        this.userBean = usrBean;
        this.clienteFacade = new ClienteFacade();
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Mostra le informazioni dell'utente
        userBean = clienteFacade.takeDati(userBean);
        System.out.println("\u001B[34mImpostazioni Account\u001B[0m");
        System.out.println("Email: " + userBean.getEmail());
        System.out.println("Ruolo: " + userBean.getRuolo());
        System.out.println("Nome: " + userBean.getNome());
        System.out.println("Cognome: " + userBean.getCognome());
        System.out.println("Anno di nascita: " + userBean.getAnnoNascita());
        System.out.println();

        // Mostra le opzioni
        while (true) {
            System.out.println("\u001B[35mScegli un'opzione:\u001B[0m");
            System.out.println("1. Visualizza ordini");
            System.out.println("2. Gestisci indirizzi");
            System.out.println("3. Gestisci metodi di pagamento");
            System.out.println("0. Esci dalle impostazioni");

            String scelta = reader.readLine();

            switch (scelta) {
                case "1":
                    visualizzaOrdini();
                    break;
                case "2":
                    gestisciIndirizzi();
                    break;
                case "3":
                    gestisciPagamenti();
                    break;
                case "0":
                    return;  // Esci dalla vista delle impostazioni
                default:
                    System.out.println("\u001B[31mScelta non valida, riprova.\u001B[0m");
            }
        }
    }

    private void visualizzaOrdini() throws SystemException, ItemNotFoundException, SQLException, IOException, CampiVuotiExeption, LoginException {
        System.out.println("\u001B[34mVisualizzazione Ordini\u001B[0m");
        // Logica per visualizzare gli ordini dell'utente, usando clienteFacade e altre classi correlate.
        MyOrderView myOrderView = new MyOrderView(userBean);
        myOrderView.control();
    }

    private void gestisciIndirizzi() throws SystemException, ItemNotFoundException, SQLException, IOException, CampiVuotiExeption, LoginException {
        System.out.println("\u001B[34mGestione Indirizzi\u001B[0m");
        // Logica per gestire gli indirizzi dell'utente.
        IndirizziView indirizziView = new IndirizziView(userBean);
        indirizziView.control();
    }

    private void gestisciPagamenti() throws SystemException, ItemNotFoundException, SQLException, IOException, CampiVuotiExeption, LoginException {
        System.out.println("\u001B[34mGestione Metodi di Pagamento\u001B[0m");
        // Logica per gestire i metodi di pagamento dell'utente.
        PagamentoView pagamentoView = new PagamentoView(userBean);
        pagamentoView.control();
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Indietro");
    }

    @Override
    protected String getHeader() {
        return "\u001B[34mImpostazioni Account\u001B[0m";
    }
}

