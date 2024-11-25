package it.uniroma2.ispw.model.user.dao;

import it.uniroma2.ispw.model.user.UserModel;
import java.util.Collections;



import java.util.List;

public class UserFS implements UserDAO{
    @Override
    public List<String> takePagamento(UserModel userM) {
        return Collections.emptyList();
    }

    @Override
    public List<String> takeIndirizzo(UserModel userM) {
        return Collections.emptyList();
    }

    @Override
    public UserModel takeDati(UserModel userM) {
        return null;
    }

    @Override
    public void salvaPay(String paymentInfo, UserModel userModel) {
//da fare

    }

    @Override
    public void salvaAddress(String indirizzoInfo, UserModel userM) {
//da fare

    }
}
