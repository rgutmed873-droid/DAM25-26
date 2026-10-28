package main.java.controller;

import main.java.controller.DAO.DriverDAO;
import main.java.controller.DB.ConexionDB;
import main.java.model.Driver;
import main.java.view.Driver.AddDriverView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AddDriverController {
    // ==================== ATRIBUTOS ====================

    private final AddDriverView view;

    private final DriverController conductorController;
    private ConexionDB ConnectionBD;
    // ==================== CONSTRUCTOR ====================

    /**
     * Constructor de AddConductorController.
     * Asocia la vista y configura los listeners de los botones del formulario.
     * @param view Vista del formulario de añadir conductor
     */
    public AddDriverController(AddDriverView view, DriverController conductorController) {
        this.view = view;
        this.conductorController = conductorController;

        // Asocia el botón Cancelar para cerrar la ventana sin guardar
        view.getBtnCancelar().addActionListener(e -> view.dispose());

        // Asocia el botón Añadir con el proceso de validación y guardado
        view.getBtnAdd().addActionListener(e -> confirmarGuardado());
    }

    // ==================== MÉTODOS ====================

    /**
     * Valida los campos del formulario, solicita confirmación al usuario
     * e inserta el nuevo conductor en la base de datos.
     */
    private void confirmarGuardado() {

        // Recoge y limpia los datos introducidos en el formulario
        String sNum = view.getNumDriver().getText().trim();
        String nombre = view.getNombre().getText().trim();
        String apellido = view.getApellido().getText().trim();

        // Valida que ningún campo esté vacío
        if (sNum.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Deben estar todos los campos completos.",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Valida que el número de driver sea un entero positivo
        int numDriver;
        try {
            numDriver = Integer.parseInt(sNum);
            if (numDriver <= 0) {
                JOptionPane.showMessageDialog(view, "Debe ser un número de driver positivo.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            // El texto introducido no es un número válido
            JOptionPane.showMessageDialog(view, "Número debe ser en formato numérico válido.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Solicita confirmación al usuario antes de guardar
        int opcion = JOptionPane.showConfirmDialog(view,
                "¿Estás seguro de que quieres guardar el driver?",
                "Confirmar guardado", JOptionPane.YES_NO_OPTION);

        // Si el usuario cancela, cierra la ventana sin guardar
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        // Construye el objeto Conductor con los datos ya validados
        Driver driver = new Driver(nombre, apellido,numDriver);

        // Intenta insertar el driver en la base de datos
        boolean addSuccess = false;
        try (Connection con = ConnectionBD.getConexion()) {
            addSuccess = DriverDAO.inserDriver(con, driver);
        } catch (SQLException e) {
            // Imprime información de error si ocurre una excepción SQL
            e.printStackTrace();
        }

        // Muestra el resultado de la operación al usuario
        if (addSuccess) {
            JOptionPane.showMessageDialog(view, "Conductor guardado correctamente",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            conductorController.cargarConductores();
        } else {
            JOptionPane.showMessageDialog(view, "Error al guardar el driver.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cierra la ventana tras completar la operación
        view.dispose();
    }
}
