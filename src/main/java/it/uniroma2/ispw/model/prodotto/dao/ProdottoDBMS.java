package it.uniroma2.ispw.model.prodotto.dao;

import it.uniroma2.ispw.model.prodotto.ProdottoModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDBMS implements ProdottoDAO {
    @Override
    public void cambiaStatoOccupato(ProdottoModel prodottoModel) throws SystemException {

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "update prodotto set stato='OCCUPATO' where prodottoID=?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, prodottoModel.getProdottoID());


                statement.executeUpdate();

            }
        } catch ( SQLException e) {
            throw new SystemException(e.getMessage());        }
    }

    @Override
    public void cambiaStatoEsaurito(ProdottoModel prodottoModel) throws SystemException {
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "DELETE FROM prodotto WHERE prodottoID = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, prodottoModel.getProdottoID());
                statement.executeUpdate();
            }
        } catch (SystemException | SQLException e) {
            throw new SystemException(e.getMessage());        }
    }

    @Override
    public void cambiaStatoDisponibile(ProdottoModel prodottoModel) throws SystemException {

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "update prodotto set stato='DISPONIBILE' where prodottoID=?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, prodottoModel.getProdottoID());


                statement.executeUpdate();

            }
        } catch (SystemException | SQLException e) {
            throw new SystemException(e.getMessage());
        }

    }

    @Override
    public List<ProdottoModel> getAllProdDisp() throws SystemException, ItemNotFoundException {
        ResultSet resultSet = null;
        List<ProdottoModel> prodottoList = new ArrayList<>();

        try(Connection conn = ConnectionDB.getConnection()) {
            String sql = "select  prodottoID, nome, prezzo, descrizione, categoria from prodotto where stato=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "DISPONIBILE");

                resultSet = statement.executeQuery();

                if (!resultSet.next()) return prodottoList;

                do {
                    ProdottoModel prodottoModel = new ProdottoModel();
                    prodottoModel.setProdottoID(resultSet.getString("prodottoID"));
                    prodottoModel.setNomeProdotto(resultSet.getString("nome"));
                    prodottoModel.setCategoria(resultSet.getString("categoria"));
                    prodottoModel.setDescrizione(resultSet.getString("descrizione"));
                    prodottoModel.setPrezzo(resultSet.getInt("prezzo"));

                    prodottoList.add(prodottoModel);
                } while (resultSet.next());
            }
        } catch ( SQLException e) {
            throw new ItemNotFoundException(e.getMessage());
        }
        return prodottoList;
    }
}
