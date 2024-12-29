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
        preparedStatement.setInt(1, idCoche );
        preparedStatement.setInt(2, idPasajero);
        preparedStatement.execute();
    }

    public void listarPasajerosDeCoche(int idCoche) throws SQLException {
        String query = String.format("SELECT p.%s, p.%s " +
                        "FROM %s pc " +
                        "JOIN %s p ON pc.%s = p.%s " +
                        "WHERE pc.%s = ?",
                DBSchema.COL_PASAJEROS_NOM, DBSchema.COL_PASAJEROS_NOM, // Columnas de pasajeros
                DBSchema.TAB_PASAJEROCOCHE, DBSchema.TAB_PASAJEROS, // Tablas involucradas
                DBSchema.COL_PASAJEROCOCHE_PA, DBSchema.COL_ID, // Relación de las tablas
                DBSchema.COL_PASAJEROCOCHE_CO); // ID del coche en la tabla de relación

        // Preparar y ejecutar la consulta SQL
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);  // Establecer el ID del coche
        preparedStatement.execute();

        // Mostrar los pasajeros
        boolean found = false;
        while (resultSet.next()) {
            found = true;
            System.out.println("ID Pasajero: " + resultSet.getInt(DBSchema.COL_ID) +
                    ", Nombre: " + resultSet.getString(DBSchema.COL_PASAJEROS_NOM));
        }

        if (!found) {
            System.out.println("No hay pasajeros asociados a este coche.");
        }

    }



}
