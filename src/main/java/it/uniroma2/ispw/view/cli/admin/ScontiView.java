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

public class ScontiView extends TemplateView {
    public ScontiView(UserBean usrBean) {
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException, SQLException, CampiVuotiExeption {
        
    }

    @Override
    protected List<String> getOptions() {
        return null;
    }

    @Override
    protected String getHeader() {
        return null;
    }
}