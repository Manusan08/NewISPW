package it.uniroma2.ispw.model.user.dao;


import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public interface UserDAO {
    List<String> takePagamento(UserModel userM) throws ItemNotFoundException, SystemException;

    List<String> takeIndirizzo(UserModel userM) throws ItemNotFoundException, SystemException;

    UserModel takeDati(UserModel userM) throws ItemNotFoundException, SystemException;

    void salvaPay(String paymentInfo, UserModel userModel) throws SystemException;

    void salvaAddress(String indirizzoInfo, UserModel userM) throws SystemException;
}

