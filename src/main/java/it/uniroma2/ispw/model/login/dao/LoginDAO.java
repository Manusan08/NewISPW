package it.uniroma2.ispw.model.login.dao;

import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;


public interface LoginDAO {

    public LoginModel auth(LoginModel loginM) throws ItemNotFoundException, SystemException;
}
