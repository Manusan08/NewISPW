package it.uniroma2.ispw.model.login.dao;

import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.model.login.LoginModel;

import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDBMS implements LoginDAO {

    public LoginModel auth(LoginModel loginM) throws ItemNotFoundException, SystemException {
        LoginModel loginModel = null;
        ResultSet resultSet = null;
        try (Connection conn = ConnectionDB.getConnection()) {
            String query = "SELECT role, email FROM Utenti WHERE email = ? AND password = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, loginM.getEmail());
                statement.setString(2, loginM.getPassword());


                resultSet = statement.executeQuery();
                if (!resultSet.next()) throw new ItemNotFoundException("\nCredenziali errate!");
                loginModel = setUtenteFromResultSet(resultSet);
            }
        } catch (SystemException | SQLException e) {
            throw new SystemException();
        }
        return loginModel;
    }
    private LoginModel setUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        LoginModel loginModel = new LoginModel();


        loginModel.setRole(Role.valueOf(resultSet.getString("role")));
        loginModel.setEmail(resultSet.getString("email"));

        return loginModel;
    }

}
