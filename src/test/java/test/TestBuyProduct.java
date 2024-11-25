package test;

import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.bean.OrdineBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.StatoProdotto;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.ClienteFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

public class TestBuyProduct {
    private ClienteFacade clienteFacade;
    private ProdottoBean prodottoBean;
    private UserBean userBean;

    @BeforeEach
    void setUp() throws SystemException, IOException {
        Conf.getConf().readConf();
        clienteFacade = new ClienteFacade();

        prodottoBean = new ProdottoBean();
        prodottoBean.setNomeProdotto("macbook");
        prodottoBean.setProdottoID("qqwesdsretrdfdfse");
        prodottoBean.setCategoria("computer");
        prodottoBean.setStato(StatoProdotto.OCCUPATO);
        prodottoBean.setPrezzo(1322);
        prodottoBean.setDescrizione("molto bello");

        userBean = new UserBean();
        userBean.setEmail("cliente1");
        userBean.setNome("Giacomini");
    }

    @Test
    void testCompraProdotto() {
        List<ProdottoBean> prodottoBeans = new ArrayList<>();
        prodottoBeans.add(prodottoBean);

        OrdineBean ordineBean = new OrdineBean();
        ordineBean.setCoupon("FirstCoupon");
        ordineBean.setPrezzoTOT(1322);
        ordineBean.setProdotti(prodottoBeans);
        ordineBean.setIndirizzo("remo");
        ordineBean.setMetodoDiPagamento("paypal");

        // Esegui il metodo compra
        clienteFacade.compra(userBean, ordineBean);

  }
}