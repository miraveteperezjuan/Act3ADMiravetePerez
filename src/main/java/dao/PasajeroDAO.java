package dao;

import database.DBConnection;
import database.DBSchema;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO() {
        connection = new DBConnection().getConnection();
    }

    public void addPasajero(Pasajero pasajero) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                DBSchema.TAB_PASAJEROS, DBSchema.COL_PASAJEROS_NOM, DBSchema.COL_PASAJEROS_ED, DBSchema.COL_PASAJEROS_PE);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setInt(3, pasajero.getPeso());
        preparedStatement.execute();
    }

    public void deletePasajero(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBSchema.TAB_PASAJEROS, DBSchema.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void buscarPasajeroPorId(int id) throws SQLException {

        String query = String.format("SELECT * FROM %s WHERE %s = ?", DBSchema.TAB_PASAJEROS, DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){

            String nombre = resultSet.getString(DBSchema.COL_PASAJEROS_NOM);
            int edad = resultSet.getInt(DBSchema.COL_PASAJEROS_ED);
            int peso = resultSet.getInt(DBSchema.COL_PASAJEROS_PE);
            System.out.printf("El pasajero se llama %s, tiene %s años, y pesa %s kilos %n", nombre, edad, peso);
        }
    }

    public void obtenerTodosLosPasajeros() throws SQLException {

        String query = String.format("SELECT * FROM %s", DBSchema.TAB_PASAJEROS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        List<Pasajero> pasajeros = new ArrayList<>();

        while (resultSet.next()){

            String nombre = resultSet.getString(DBSchema.COL_PASAJEROS_NOM);
            int edad = resultSet.getInt(DBSchema.COL_PASAJEROS_ED);
            int peso = resultSet.getInt(DBSchema.COL_PASAJEROS_PE);

            Pasajero pasajero = mapearPasajero(nombre, edad, peso);
            pasajeros.add(pasajero);
        }

        for (Pasajero pasajero : pasajeros) {
            System.out.printf("El pasajero se llama %s, tiene %s años, y pesa %s kilos %n",
                    pasajero.getNombre(), pasajero.getEdad(), pasajero.getPeso());
        }

    }

    private Pasajero mapearPasajero(String nombre, int edad, int peso) {return new Pasajero(nombre, edad, peso);}

}
