package database;

public interface DBSchema {


    String DATABASE = "act3conectores";
    String HOST = "127.0.0.1";
    String PORT = "3306";

    String TAB_COCHES = "coches";
    String TAB_PASAJEROS = "pasajeros";
    String TAB_PASAJEROCOCHE = "pasajeroCoches";

    String COL_ID = "id";

    String COL_COCHES_MAT = "matricula";
    String COL_COCHES_MA= "marca";
    String COL_COCHES_MO = "modelo";
    String COL_COCHES_CO = "color";

    String COL_PASAJEROS_NOM = "nombre";
    String COL_PASAJEROS_ED = "edad";
    String COL_PASAJEROS_PE = "peso";

    String COL_PASAJEROCOCHE_PA= "IdPasajero";
    String COL_PASAJEROCOCHE_CO= "IdCoches";

}
