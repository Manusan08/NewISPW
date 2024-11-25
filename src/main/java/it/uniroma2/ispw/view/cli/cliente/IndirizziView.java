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

public class IndirizziView extends TemplateView {
    private final ClienteFacade clienteFacade;
    private UserBean userBean;

    public IndirizziView(UserBean usrBean) throws IOException {
        this.userBean = usrBean;
        clienteFacade = new ClienteFacade();
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\u001B[36m=== Gestione Indirizzi ===\u001B[0m");

        popolaIndirizzi(); // Mostra gli indirizzi disponibili

        while (true) {
            System.out.println("\n\u001B[33mScegli un'opzione:\u001B[0m");
            System.out.println("1. \u001B[32mAggiungi nuovo indirizzo\u001B[0m");
            System.out.println("2. \u001B[34mTorna al carrello\u001B[0m");
            System.out.println("3. \u001B[34mTorna alle impostazioni\u001B[0m");
            System.out.println("0. \u001B[31mEsci\u001B[0m");

            String scelta = reader.readLine();

            switch (scelta) {
                case "1":
                    aggiungiIndirizzo(reader);
                    break;

                case "0":
                    System.out.println("\u001B[31mUscita...\u001B[0m");
                    return;
                default:
                    System.out.println("\u001B[31mScelta non valida, riprova.\u001B[0m");
            }
        }
    }

    private void popolaIndirizzi() {
        userBean = clienteFacade.getModalita(userBean);

        if (userBean.getIndirizzo() != null) {
            System.out.println("\n\u001B[36mIndirizzi disponibili:\u001B[0m");
            for (String indirizzo : userBean.getIndirizzo()) {
                System.out.println("- " + indirizzo);
            }
        } else {
            System.out.println("\u001B[31mNessun indirizzo disponibile.\u001B[0m");
        }
    }

    private void aggiungiIndirizzo(BufferedReader reader) throws IOException {
        System.out.print("\u001B[33mInserisci il nome della via: \u001B[0m");
        String via = reader.readLine();

        System.out.print("\u001B[33mInserisci la città: \u001B[0m");
        String citta = reader.readLine();

        String infoIndirizzo = via + " - " + citta;
        clienteFacade.salvaAddress(infoIndirizzo, userBean);

        System.out.println("\u001B[32mIndirizzo salvato con successo!\u001B[0m");
    }


    @Override
    protected List<String> getOptions() {
        return List.of("Indietro");
    }

    @Override
    protected String getHeader() {
        return "\u001B[36m=== Gestione Indirizzi ===\u001B[0m";
    }
}
