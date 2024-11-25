package it.uniroma2.ispw.model.user.dao;


import it.uniroma2.ispw.model.user.UserModel;

import java.util.List;

public interface UserDAO {
    List<String> takePagamento(UserModel userM);

    List<String> takeIndirizzo(UserModel userM);

    UserModel takeDati(UserModel userM);

    void salvaPay(String paymentInfo, UserModel userModel);

    void salvaAddress(String indirizzoInfo, UserModel userM);
}

