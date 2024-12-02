package it.uniroma2.ispw.view.cli.cliente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.view.cli.TemplateView;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;







public class ClienteView extends TemplateView {




    MostraProdottiView mostraProdotti;
    CarrelloView carrello;
    ImpostazioniView impostazioni;

    public ClienteView(UserBean usrBean) throws IOException, SystemException, ItemNotFoundException {
        super(usrBean);  // Passa il UserBean alla superclasse TemplateView
        mostraProdotti = new MostraProdottiView(usrBean); // Passa usrBean anche a MostraProdottiView
        carrello = new CarrelloView(usrBean); // Passa usrBean anche a CarrelloView
        impostazioni = new ImpostazioniView(usrBean); // Passa usrBean anche a ImpostazioniView
    }
    @Override
    public void control() throws SystemException, IOException, SQLException, CampiVuotiExeption, LoginException, ItemNotFoundException {
        boolean loopCond = true;  // La variabile deve essere inizializzata a true per avviare il ciclo
        do {
            int choice = userChoice();
            switch (choice) {
                case 1:
                    mostraProdotti.control();
                    break;
                case 2:
                    carrello.control();
                    break;
                case 3:
                    impostazioni.control();
                    break;
                case 4:
                    exit();
                    loopCond = false;  // La variabile viene aggiornata per fermare il ciclo
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        } while (loopCond);  // Il ciclo continua fino a quando loopCond è true
    }


    @Override
    protected List<String> getOptions() {
        return List.of(
                "Visualizza Prodotti",
                "Carrello",
                "Impostazioni Account",
                "Esci"
        );
    }

    @Override
    protected String getHeader() {
        return "Home Cliente";
    }




    // 4. Esci
    private void exit() {
        System.out.println("Arrivederci!");
        System.exit(0);
    }
}
