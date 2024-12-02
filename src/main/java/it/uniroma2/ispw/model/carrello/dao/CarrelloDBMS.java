package it.uniroma2.ispw.model.carrello.dao;


import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDBMS implements CarrelloDAO{
    @Override
    public void addToCart(ProdottoModel prodottoModel, UserModel userModel) throws SystemException {
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "INSERT INTO carrello (idcarrello, prodottoid, Utenti_email) " + "VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, RandomStringUtils.randomAlphanumeric(4));
                statement.setString(2, prodottoModel.getProdottoID());
                statement.setString(3, userModel.getEmail());

                statement.executeUpdate();

            }
        }catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }

    }

    @Override
    public List<ProdottoModel> getAllMyCar(UserModel userModel) throws SystemException {
        List<ProdottoModel> prodottiNelCarrello = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql =  "SELECT prodotto.prodottoID, prodotto.nome, prodotto.prezzo " +
                    "FROM carrello JOIN prodotto ON carrello.prodottoid = prodotto.prodottoID " +
                    "WHERE carrello.Utenti_email = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, userModel.getEmail());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ProdottoModel prodottoModel = new ProdottoModel(
                                resultSet.getString("prodottoID"),
                                resultSet.getString("nome"),
                                resultSet.getInt("prezzo")

                        );

                        prodottiNelCarrello.add(prodottoModel);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }

        return prodottiNelCarrello;
    }

    @Override
    public void removeToCart(ProdottoModel prodottoM, UserModel userModel) throws SystemException {try (Connection conn = ConnectionDB.getConnection()) {
        String sql = "DELETE FROM carrello WHERE prodottoid = ? AND Utenti_email = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // Imposta l'ID del prodotto e l'email dell'utente
            statement.setString(1, prodottoM.getProdottoID());
            statement.setString(2, userModel.getEmail());

            statement.executeUpdate();
        }
    } catch (SQLException e) {
        throw new SystemException(e.getMessage());    }


    }

    @Override
    public void svuotaCarrello(UserModel userModel) throws SystemException {
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "DELETE FROM carrello WHERE Utenti_email = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, userModel.getEmail());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }

    }

}
