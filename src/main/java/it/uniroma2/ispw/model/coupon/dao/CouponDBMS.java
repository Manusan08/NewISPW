package it.uniroma2.ispw.model.coupon.dao;

import it.uniroma2.ispw.model.coupon.CouponModel;
import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponDBMS implements  CouponDAO{
    @Override
    public List<CouponModel> getCoupon(UserModel cred) {

        List<CouponModel> couponModels = new ArrayList<>(); // Inizializziamo la lista dei coupon
        ResultSet resultSet = null;

        try (Connection conn = ConnectionDB.getConnection()) {
            // Query per selezionare i coupon associati all'utente
            String sql = "SELECT nome, idcoupon FROM coupon WHERE Utenti_email = ?;";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cred.getEmail());

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    // Estrai i dati dal ResultSet
                    String nome = resultSet.getString("nome");
                    int couponID = resultSet.getInt("idcoupon");


                    // Crea un nuovo CouponModel e lo aggiungi alla lista
                    CouponModel couponModel = new CouponModel();
                    couponModel.setNome(nome);
                    couponModel.setCouponID(String.valueOf(couponID));


                    couponModels.add(couponModel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la ricerca dei coupon", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return couponModels;
    }
}
