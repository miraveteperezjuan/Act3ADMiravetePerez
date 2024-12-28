package controller;

import dao.CochesDAO;
import dao.PasajeroDAO;
import model.Coche;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Concesionario {

    private CochesDAO cochesDAO;
    private PasajeroDAO pasajeroDAO;

    public Concesionario(){
        cochesDAO = new CochesDAO();
        pasajeroDAO = new PasajeroDAO();
    }

    public void opciones() {
        System.out.println("\n--- Concesionario ---");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por ID");
        System.out.println("3. Consulta coche por ID");
        System.out.println("4. Modificar coche por ID");
        System.out.println("5. Listado de coches");
        System.out.println("6. Gestión de pasajeros");
        System.out.println("7. Salir del programa");
        System.out.print("Elige una opción: ");
    }

    public void menu() {
        int option;
        Scanner scanner = new Scanner(System.in);

        do {
            opciones();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarCoches();
                    break;
                case 2:
                    borrarCoches();
                    break;
                case 3:
                    consultarCoches();
                    break;
                case 4:
                    modificarCoches();
                    break;
                case 5:
                    listarCoches();
                    break;
                case 6:
                    menuPasajeros();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción invalida. Vuelva a intentarlo");
            }
        } while (option != 6);
    }

    private void opcionesPasajero() {
        System.out.println("\n--- Gestión de Pasajeros y coches ---");
        System.out.println("1. Añadir nuevo pasajero");
        System.out.println("2. Borrar pasajero por ID");
        System.out.println("3. Consultar pasajero por ID");
        System.out.println("4. Listar todos los pasajeros");
        System.out.println("5. Añadir pasajero a coche");
        System.out.println("6. Eliminar pasajero de un coche");
        System.out.println("7. Listar todos los pasajeros de un coche");
        System.out.println("8. Salir del programa");
        System.out.print("Elige una opción: ");
    }

    private void menuPasajeros() {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            opcionesPasajero();
            option = scanner.nextInt();
            scanner.nextLine();
        switch (option) {
            case 1:
                agregarPasajero();
                break;
            case 2:
                borrarPasajero();
                break;
            case 3:
                consultaPasajeroId();
                break;
            case 4:
                todosPasajeros();
                break;
            case 5:
                agregarPasajeroCoche();
                break;
            case 6:
                eliminarPasajeroCoche();
                break;
            case 7:
                listarPasajeroCoche();
                break;
            case 8:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción invalida. Vuelva a intentarlo");
        }
        } while (option != 6);
    }

    private void listarPasajeroCoche() {
    }

    private void eliminarPasajeroCoche() {
    }

    private void agregarPasajeroCoche() {
    }

    private void todosPasajeros() {
    }

    private void consultaPasajeroId() {
    }

    private void borrarPasajero() {
    }

    private void agregarPasajero() {
    }

    public void agregarCoches(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce matricula");
        String matricula = scanner.next();
        System.out.println("Introduce marca");
        String marca = scanner.next();
        System.out.println("Introduce modelo");
        String modelo = scanner.next();
        System.out.println("Introduce color");
        String color = scanner.next();

        try {
            cochesDAO.addCoche(new Coche(matricula,marca,modelo,color));
        } catch (SQLException e) {
            System.out.println("No se ha podido insertar en la base de datos");
        }

    }

    public void borrarCoches(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del coche a borrar");
        int id = scanner.nextInt();
        try {
            cochesDAO.deleteCoche(id);
        } catch (SQLException e) {
            System.out.println("Error al borrar el coche");
        }
    }

    private void consultarCoches() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del coche a buscar:");
        int id = scanner.nextInt();
        try {
            cochesDAO.buscarCochePorId(id);
        } catch (SQLException e) {
            System.out.println("Error al buscar el coche en la base de datos: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, introduce un número válido." + e.getMessage());
        }
    }

    private void listarCoches () {
            try {
             cochesDAO.obtenerTodosLosCoches();
            } catch (SQLException e) {
                System.out.println("No se ha podido listar en la base de datos");
            }
        }

    private void modificarCoches () {


    }

}