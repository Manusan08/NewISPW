package it.uniroma2.ispw.model.user.dao;



import it.uniroma2.ispw.enums.Role;


import it.uniroma2.ispw.model.user.UserModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

public class UserDBMS implements  UserDAO{
    @Override
    public List<String> takePagamento(UserModel userM) throws ItemNotFoundException, SystemException {
        List<String> pagamenti = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection conn = ConnectionDB.getConnection()){
            String sql = "select idpagamento, metodo, Utenti_email from pagamento where Utenti_email=? ;";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {



                statement.setString(1, userM.getEmail());

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String metodoPagamento = resultSet.getString("metodo");
                    pagamenti.add(metodoPagamento);
                }

            }} catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        return pagamenti;
    }

    @Override
    public List<String> takeIndirizzo(UserModel userM) throws ItemNotFoundException, SystemException {
        List<String> indirizzi = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection conn = ConnectionDB.getConnection()){
            String sql = "select idindirizzo, via , Utenti_email from indirizzo where Utenti_email=? ;";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {



                statement.setString(1, userM.getEmail());

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String indirizzo = resultSet.getString("via");
                    indirizzi.add(indirizzo);
                }

            }} catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        return indirizzi;
    }


    @Override
    public UserModel takeDati(UserModel userM) throws ItemNotFoundException, SystemException {
        String sql = "SELECT nascita, nome, cognome, role, email FROM Utenti WHERE email = ?;";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, userM.getEmail());

            return eseguiQueryEImpostaUtente(statement);

        } catch (SQLException  e ) {
            throw new SystemException(e.getMessage());
        }
    }

    private UserModel eseguiQueryEImpostaUtente(PreparedStatement statement) throws SQLException, ItemNotFoundException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                throw new ItemNotFoundException("\nCredenziali errate!");
            }
            return setUtenteFromResultSet(resultSet);
        }
    }


    @Override
    public void salvaPay(String paymentInfo, UserModel userModel) throws SystemException {
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "INSERT INTO pagamento (idpagamento, metodo, Utenti_email) " + "VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, RandomStringUtils.randomAlphanumeric(4));
                statement.setString(2, paymentInfo);
                statement.setString(3, userModel.getEmail());

                statement.executeUpdate();

            }
        }catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
    }
    @Override
    public void salvaAddress(String indirizzoInfo, UserModel userModel) throws SystemException {
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "INSERT INTO indirizzo (idindirizzo, via, Utenti_email) " + "VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, RandomStringUtils.randomAlphanumeric(4));
                statement.setString(2, indirizzoInfo);
                statement.setString(3, userModel.getEmail());

                statement.executeUpdate();

            }
        }catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
    }

    private UserModel setUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        UserModel userModel = new UserModel();


        userModel.setRuolo(Role.valueOf(resultSet.getString("role")));
        userModel.setEmail(resultSet.getString("email"));
        userModel.setNome(resultSet.getString("nome"));
        userModel.setCognome(resultSet.getString("cognome"));
        // Recupera la data di nascita
        java.sql.Date sqlDate = resultSet.getDate("nascita");
        // Converte java.sql.Date in java.util.Date
        if (sqlDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(sqlDate);
            userModel.setAnnoNascita(formattedDate); // Supponendo che AnnoNascita sia una stringa
        } else {
            userModel.setAnnoNascita(null); // Gestisci il caso in cui la data sia null
        }


        return userModel;
    }

}
