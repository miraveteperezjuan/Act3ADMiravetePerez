package dao;

import database.DBConnection;
import database.DBSchema;
import model.PasajeroCoche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajeroCocheDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroCocheDAO() {
        connection = new DBConnection().getConnection();
    }

    public void addPasajeroToCoche(PasajeroCoche pasajeroCoche) throws SQLException {

        String query = String.format("INSERT INTO %s (%s,%s) VALUES (?, ?)", DBSchema.TAB_PASAJEROCOCHE,
            DBSchema.COL_PASAJEROCOCHE_CO, DBSchema.COL_PASAJEROCOCHE_PA);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, pasajeroCoche.getId_coche());
        preparedStatement.setInt(2, pasajeroCoche.getId_paj());
        preparedStatement.execute();
    }

    public void borrarPasajeroCoche(int idCoche, int idPasajero) throws SQLException  {

        String query = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                DBSchema.TAB_PASAJEROCOCHE, DBSchema.COL_PASAJEROCOCHE_CO, DBSchema.COL_PASAJEROCOCHE_PA);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        preparedStatement.setInt(2, idPasajero);
        preparedStatement.execute();
    }

    public void listarPasajerosDeCoche(int idCoche) throws SQLException {
        String query = String.format(
                "SELECT p.%s, p.%s FROM %s pc " +
                        "JOIN %s p ON pc.%s = p.%s WHERE pc.%s = ?",
                DBSchema.COL_ID, DBSchema.COL_PASAJEROS_NOM,
                DBSchema.TAB_PASAJEROCOCHE, DBSchema.TAB_PASAJEROS,
                DBSchema.COL_PASAJEROCOCHE_PA, DBSchema.COL_ID,
                DBSchema.COL_PASAJEROCOCHE_CO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        resultSet = preparedStatement.executeQuery();
        System.out.println("Pasajeros asociados al coche");
        System.out.println("------------------------------");

        while (resultSet.next()) {
            System.out.println("- " + resultSet.getString(DBSchema.COL_PASAJEROS_NOM));
        }
    }
}