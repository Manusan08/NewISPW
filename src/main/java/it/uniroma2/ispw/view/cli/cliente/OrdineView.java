package it.uniroma2.ispw.view.cli.cliente;

import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.enums.Coupon;
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
import java.util.ArrayList;
import java.util.List;

public class OrdineView extends TemplateView {
    private final ClienteFacade clienteFacade;
    private UserBean userBean;
    private List<ProdottoBean> prodotti;

    public OrdineView(UserBean usrBean, List<ProdottoBean> prodotti) throws IOException {
        this.userBean = usrBean;
        this.prodotti = prodotti;
        clienteFacade = new ClienteFacade();
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OrdineBean ordineBean = new OrdineBean();

        // Imposta i prodotti nell'ordine
        ordineBean.setProdotti(prodotti);

        // Seleziona metodo di pagamento
        userBean = clienteFacade.getModalita(userBean);
        List<String> metodiPagamento = userBean.getPagamento();
        System.out.println("\u001B[35mScegli un metodo di pagamento (0 per tornare alla ClienteView):\u001B[0m");
        for (int i = 0; i < metodiPagamento.size(); i++) {
            System.out.println(  (i + 1) + ". " + metodiPagamento.get(i) );
        }
        int sceltaPagamento = Integer.parseInt(reader.readLine());
        if (sceltaPagamento == 0) {
            return; // Ritorno alla ClienteView
        }
        ordineBean.setMetodoDiPagamento(metodiPagamento.get(sceltaPagamento - 1));

        // Seleziona indirizzo di spedizione
        List<String> indirizzi = userBean.getIndirizzo();
        System.out.println("\u001B[35mScegli un indirizzo di spedizione (0 per tornare alla ClienteView):\u001B[0m");
        for (int i = 0; i < indirizzi.size(); i++) {
            System.out.println("\u001B[33m" + (i + 1) + ". " + indirizzi.get(i)  );
        }
        int sceltaIndirizzo = Integer.parseInt(reader.readLine());
        if (sceltaIndirizzo == 0) {
            return; // Ritorno alla ClienteView
        }
        ordineBean.setIndirizzo(indirizzi.get(sceltaIndirizzo - 1));

        // Seleziona coupon
        List<CouponBean> couponBeans = clienteFacade.getCoupon(userBean);
        System.out.println("\u001B[35mScegli un coupon (0 per tornare alla ClienteView, lascia vuoto per non usarne uno):\u001B[0m");
        List<String> couponNames = new ArrayList<>();
        for (CouponBean couponBean : couponBeans) {
            couponNames.add(couponBean.getNome());
            System.out.println("\u001B[33m" + (couponNames.size()) + ". " + couponBean.getNome() );
        }
        String inputCoupon = reader.readLine();
        if (inputCoupon.equals("0")) {
            return; // Ritorno alla ClienteView
        }
        if (!inputCoupon.isEmpty()) {
            int sceltaCoupon = Integer.parseInt(inputCoupon) - 1;
            String couponSelezionato = couponNames.get(sceltaCoupon);
            ordineBean.setCoupon(couponSelezionato);
        }

        // Calcola prezzo totale con eventuale coupon
        int prezzoTOT = clienteFacade.getAllMyCar(userBean).getPrezzoTOT();
        if (ordineBean.getCoupon() != null && !ordineBean.getCoupon().isEmpty()) {
            double prezzoScontato = clienteFacade.returnCoupon(Coupon.fromString(ordineBean.getCoupon()), prezzoTOT);
            ordineBean.setPrezzoTOT(prezzoScontato);
        } else {
            ordineBean.setPrezzoTOT(prezzoTOT);
        }

        // Conferma dell'ordine
        System.out.println("\u001B[34mConfermi l'ordine con le seguenti informazioni?\u001B[0m");
        System.out.println("\u001B[36mProdotti:\u001B[0m");
        for (ProdottoBean prodotto : prodotti) {
            System.out.println("\u001B[32m- " + prodotto.getNomeProdotto() + ": £" + prodotto.getPrezzo() + "\u001B[0m");
        }
        System.out.println("\u001B[36mMetodo di Pagamento: \u001B[0m" + ordineBean.getMetodoDiPagamento());
        System.out.println("\u001B[36mIndirizzo di Spedizione: \u001B[0m" + ordineBean.getIndirizzo());
        System.out.println("\u001B[36mCoupon: \u001B[0m" + (ordineBean.getCoupon().isEmpty() ? "Nessuno" : ordineBean.getCoupon()));
        System.out.println("\u001B[33mPrezzo Totale: £" + ordineBean.getPrezzoTOT() + "\u001B[0m");

        System.out.print("\u001B[35mConfermi l'ordine? (S per confermare, 0 per tornare alla ClienteView): \u001B[0m");
        String conferma = reader.readLine();
        if (conferma.equals("0")) {
            return; // Ritorno alla ClienteView
        }
        if (conferma.equalsIgnoreCase("S")) {
            clienteFacade.compra(userBean, ordineBean);
            System.out.println("\u001B[32mOrdine completato con successo!\u001B[0m");

        } else {
            System.out.println("\u001B[31mOrdine annullato.\u001B[0m");
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Indietro");
    }

    @Override
    protected String getHeader() {
        return "\u001B[34mAcquisto Ordine\u001B[0m";
    }
}
