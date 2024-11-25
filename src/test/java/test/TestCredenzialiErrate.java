package test;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestCredenzialiErrate {
    @Test
    void testCredenziali(){
        LoginDAO loginDAO=new LoginDBMS();
        LoginModel loginBean=new LoginModel("paolo89@Torverdura.org","torverdura123");
        try {
            Assertions.assertNull(loginDAO.auth(loginBean));
        } catch (ItemNotFoundException | SystemException e) {
            System.out.println(e.getMessage());
        }

    }

}
