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

public class PagamentoView extends TemplateView {
    private final ClienteFacade clienteFacade;
    private UserBean userBean;

    public PagamentoView(UserBean usrBean) throws IOException {
        this.userBean = usrBean;
        clienteFacade = new ClienteFacade();
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\u001B[34mGestione Pagamenti\u001B[0m");

        popolaMetodiPagamento();  // Mostra le modalità di pagamento disponibili

        while (true) {
            System.out.println("\n\u001B[35mScegli un'opzione:\u001B[0m");
            System.out.println("1. Aggiungi nuovo metodo di pagamento");

            System.out.println("0. Esci");

            String scelta = reader.readLine();

            switch (scelta) {
                case "1":
                    aggiungiMetodoPagamento(reader);
                    break;

                case "0":
                    return;
                default:
                    System.out.println("\u001B[31mScelta non valida, riprova.\u001B[0m");
            }
        }
    }

    private void popolaMetodiPagamento() {
        // Carica le modalità di pagamento disponibili per l'utente
        userBean = clienteFacade.getModalita(userBean);

        if (userBean.getPagamento() != null) {
            System.out.println("\nMetodi di pagamento disponibili:");
            for (String pagamento : userBean.getPagamento()) {
                System.out.println("- " + pagamento);
            }
        } else {
            System.out.println("Nessun metodo di pagamento disponibile.");
        }
    }

    private void aggiungiMetodoPagamento(BufferedReader reader) throws IOException {
        System.out.print("Inserisci il nome della carta: ");
        String nome = reader.readLine();

        System.out.print("Inserisci il numero della carta: ");
        String numeroCarta = reader.readLine();

        String infoPagamento = nome + " - " + numeroCarta;
        clienteFacade.salvaPay(infoPagamento, userBean);

        System.out.println("\u001B[32mMetodo di pagamento salvato con successo!\u001B[0m");
    }



    @Override
    protected List<String> getOptions() {
        return List.of("Indietro");
    }

    @Override
    protected String getHeader() {
        return "\u001B[34mGestione Pagamenti\u001B[0m";
    }
}
