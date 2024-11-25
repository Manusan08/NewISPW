package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginDAOFactory;
import it.uniroma2.ispw.model.login.dao.LoginDAOFactoryImpl;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;


public class LoginController {


    private final LoginDAO loginDAO;

    public LoginController() throws IOException {
        LoginDAOFactory daoFactory = new LoginDAOFactoryImpl();
        loginDAO = daoFactory.getDao();
    }
    public UserBean login(LoginBean loginBean) throws ItemNotFoundException, SystemException {
        LoginModel loginM = new LoginModel(loginBean.getEmail(),loginBean.getPassword(),loginBean.getRole());
        LoginModel lm = loginDAO.auth(loginM);

        return new UserBean(lm.getEmail(), lm.getRole());
    }
}
