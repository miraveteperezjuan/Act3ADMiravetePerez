package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // static que quiero que pertenezca a la clase
    private static Connection connection;


    public Connection getConnection(){
        // si alguien pide una conexion,

        // si no esta -> la creo
        if (connection==null){
            // creo
            newConnection();
        }
        // si esta -> se la doy
        return connection;
    }

    public void newConnection(){
        String url = String.format("jdbc:mysql://%s:%s/%s", DBSchema.HOST, DBSchema.PORT,DBSchema.DATABASE);

        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Error en la conexion de la base de datos");
        }
        System.out.println("Conexion creada correctamente");
    }
    public void closeConnecion(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("error al cerrar la conexion");
        } finally {
            connection = null;
        }
    }

}