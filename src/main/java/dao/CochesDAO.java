package dao;

import database.DBConnection;
import database.DBSchema;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CochesDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CochesDAO() {
        connection = new DBConnection().getConnection();
    }

    public void addCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                DBSchema.TAB_COCHES, DBSchema.COL_COCHES_MAT, DBSchema.COL_COCHES_MA, DBSchema.COL_COCHES_MO, DBSchema.COL_COCHES_CO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.execute();
    }

    public void deleteCoche(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBSchema.TAB_COCHES, DBSchema.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void buscarCochePorId(int id) throws SQLException {

        String query = String.format("SELECT * FROM %s WHERE %s = ?", DBSchema.TAB_COCHES, DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String matricula = resultSet.getString(DBSchema.COL_COCHES_MAT);
            String marca = resultSet.getString(DBSchema.COL_COCHES_MA);
            String modelo = resultSet.getString(DBSchema.COL_COCHES_MO);
            String color = resultSet.getString(DBSchema.COL_COCHES_CO);
            System.out.printf("El coche posee la matrícula %s, es de la marca %s, es del modelo %s y tiene un color %s%n", matricula, marca, modelo, color);
        }
    }

    public void obtenerTodosLosCoches() throws SQLException {

        String query = String.format("SELECT * FROM %s", DBSchema.TAB_COCHES);
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Coche> coches = new ArrayList<>();

        while (resultSet.next()) {
            String matricula = resultSet.getString(DBSchema.COL_COCHES_MAT);
            String marca = resultSet.getString(DBSchema.COL_COCHES_MA);
            String modelo = resultSet.getString(DBSchema.COL_COCHES_MO);
            String color = resultSet.getString(DBSchema.COL_COCHES_CO);

            Coche coche = mapearCoche(matricula, marca, modelo, color);
            coches.add(coche);
        }

        for (Coche coche : coches) {
            System.out.printf("El coche posee la matrícula %s, es de la marca %s, es del modelo %s y tiene un color %s%n",
                    coche.getMatricula(), coche.getMarca(), coche.getModelo(), coche.getColor());
        }
    }





    private Coche mapearCoche(String matricula, String marca, String modelo, String color) {
        return new Coche(matricula,marca,modelo,color);
    }

}


