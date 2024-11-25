package it.uniroma2.ispw.view.cli.admin;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.view.cli.TemplateView;


import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminView extends TemplateView {

    VendiView vendi;
    ScontiView sconti;
    ImpostazioniadminView impostazioni;

    public AdminView(UserBean usrBean) {
        super(usrBean);
        vendi = new VendiView(usrBean); // Passa usrBean anche a MostraProdottiView
        sconti = new ScontiView(usrBean); // Passa usrBean anche a CarrelloView
        impostazioni = new ImpostazioniadminView(usrBean);
    }
    @Override
    public void control() throws SystemException, IOException, SQLException, CampiVuotiExeption, LoginException, ItemNotFoundException {
        boolean loopCond = true;  // La variabile deve essere inizializzata a true per avviare il ciclo
        do {
            int choice = userChoice();
            switch (choice) {
                case 1:
                    vendi.control();
                    break;
                case 2:
                    sconti.control();
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
                "Vendi Prodotti",
                "Applica Sconti",
                "Impostazioni Account",
                "Esci"
        );
    }

    @Override
    protected String getHeader() {
        return "Home Admin";
    }




    // 4. Esci
    private void exit() {
        System.out.println("Arrivederci!");
        System.exit(0);
    }

}
