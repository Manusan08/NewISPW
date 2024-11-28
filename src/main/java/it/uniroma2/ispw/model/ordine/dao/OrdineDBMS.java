package it.uniroma2.ispw.model.ordine.dao;

import it.uniroma2.ispw.model.ordine.OrdineModel;
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

public class OrdineDBMS implements OrdineDAO{
    public void compra(OrdineModel ordineModel) {
        try (Connection conn = ConnectionDB.getConnection()) {
            // Inserisci i dettagli dell'ordine
            String idOrdine = salvaDettagliOrdine(conn, ordineModel);

            // Inserisci i dettagli dei prodotti venduti
            salvaProdottiVenduti(conn, idOrdine, ordineModel.getProdottiID());
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio dell'ordine e dei prodotti venduti", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

    private String salvaDettagliOrdine(Connection conn, OrdineModel ordineModel) throws SQLException {
        String ordineSql = "INSERT INTO dettagliordine (ordineID, emailCliente, pagamento, indirizzo, coupon, prezzo, dataAcquisto) " +
                "VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE)";
        String idOrdine = RandomStringUtils.randomAlphanumeric(8); // Genera un ID ordine casuale

        try (PreparedStatement ordineStatement = conn.prepareStatement(ordineSql)) {
            ordineStatement.setString(1, idOrdine);
            ordineStatement.setString(2, ordineModel.getEmailCliente());
            ordineStatement.setString(3, ordineModel.getPagamento());
            ordineStatement.setString(4, ordineModel.getIndirizzo());
            ordineStatement.setString(5, ordineModel.getCoupon());
            ordineStatement.setDouble(6, ordineModel.getPrezzotot());
            ordineStatement.executeUpdate();
        }

        return idOrdine;
    }

    private void salvaProdottiVenduti(Connection conn, String idOrdine, List<ProdottoModel> prodotti) throws SQLException {
        String prodottiSql = "INSERT INTO ordine_prodotto (dettagliOrdine_ordineID, prodottoID, nome, prezzo) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement prodottiStatement = conn.prepareStatement(prodottiSql)) {
            prodottiStatement.setString(1, idOrdine); // Imposta una volta sola fuori dal ciclo

            for (ProdottoModel prodotto : prodotti) {
                prodottiStatement.setString(2, prodotto.getProdottoID());
                prodottiStatement.setString(3, prodotto.getNomeProdotto());
                prodottiStatement.setDouble(4, prodotto.getPrezzo());
                prodottiStatement.addBatch();
            }

            prodottiStatement.executeBatch();
        }
    }


    public List<OrdineModel> getMyOrder(UserModel userM) {
        List<OrdineModel> ordini = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()) {
            // Query per ottenere gli ordini dell'utente
            String ordineSql = "SELECT ordineID, emailCliente, pagamento, indirizzo, coupon, prezzo, dataAcquisto " +
                    "FROM dettagliordine WHERE emailCliente = ?";

            try (PreparedStatement ordineStatement = conn.prepareStatement(ordineSql)) {
                ordineStatement.setString(1, userM.getEmail());

                try (ResultSet rs = ordineStatement.executeQuery()) {
                    // Estrai ogni ordine dal ResultSet
                    while (rs.next()) {
                        OrdineModel ordine = new OrdineModel();
                        ordine.setOrdineID(rs.getString("ordineID"));
                        ordine.setEmailCliente(rs.getString("emailCliente"));
                        ordine.setPagamento(rs.getString("pagamento"));
                        ordine.setIndirizzo(rs.getString("indirizzo"));
                        ordine.setCoupon(rs.getString("coupon"));
                        ordine.setPrezzotot(rs.getDouble("prezzo"));
                        ordine.setDataAcquisto(rs.getDate("dataAcquisto"));

                        // Aggiungi l'ordine alla lista
                        ordini.add(ordine);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero degli ordini dell'utente", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return ordini;
    }

    @Override
    public List<ProdottoModel> getMyOrderProd(UserModel userM, OrdineModel ordineModel) {
        List<ProdottoModel> prodotti = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()) {
            // Query per ottenere i prodotti associati a un ordine
            String prodottiSql = "SELECT prodottoID, nome, prezzo " +
                    "FROM ordine_prodotto WHERE dettagliOrdine_ordineID = ?";

            try (PreparedStatement prodottiStatement = conn.prepareStatement(prodottiSql)) {
                prodottiStatement.setString(1, ordineModel.getOrdineID()); // Imposta l'ordineID del modello

                try (ResultSet rs = prodottiStatement.executeQuery()) {
                    // Estrai ogni prodotto dal ResultSet
                    while (rs.next()) {
                        ProdottoModel prodotto = new ProdottoModel();
                        prodotto.setProdottoID(rs.getString("prodottoID"));
                        prodotto.setNomeProdotto(rs.getString("nome"));
                        prodotto.setPrezzo(rs.getInt("prezzo"));

                        // Aggiungi il prodotto alla lista
                        prodotti.add(prodotto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dei prodotti per l'ordine", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return prodotti;
    }
}
