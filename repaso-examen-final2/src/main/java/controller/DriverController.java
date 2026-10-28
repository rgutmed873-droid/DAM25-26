package main.java.controller;

import main.java.controller.DAO.DriverDAO;
import main.java.controller.DB.ConexionDB;
import main.java.model.Driver;
import main.java.view.Driver.AddDriverView;
import main.java.view.Driver.DriverPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *Clase de driver controller
 * Controlador de los conductores
 * Gestiona la carga de datos desde la base de datos y las acciones
 * disponible sobre los conductores en la vista
 */
public class DriverController {
    //ATRIBUTOS
    private final DriverPanel driverPanel;


    //Lista local que almacena los conductores cargados desde la base de datos
    private ArrayList<Driver> drivers = new ArrayList<>();
    private ConexionDB ConnectionBD;

    //CONSTRUCTOR

    /**
     * Constructor del driverController
     * Asocia el panel y carga los datos de la base de datos
     *
     */
    public DriverController(DriverPanel panel) {
        this.driverPanel = panel;

        //Carga los conductores al inicializar el controlador
        //Para así tener los datos de los conductores en la tabla
        cargarConductores();
    }

    //METODOS

    /**
     * Carga los conductores de la base de datos y los muestra en la tabla
     */
    public void cargarConductores() {
        try {
            List<Driver> lista = DriverDAO.consultDrivers(con);
            drivers = new ArrayList<>(lista);

            // Limpia la tabla antes de recargar los datos
            driverPanel.getModeloTabla().setRowCount(0);

            // Añade cada conductor como una nueva fila en la tabla
            for (Driver c : drivers) {
                driverPanel.getModeloTabla().addRow(new Object[]{
                        c.getNumeroConductor(),
                        c.getNombre(),
                        c.getApellidos()
                });
            }
        } catch (SQLException e) {
            // Imprime información de error si ocurre una excepción SQL
            e.printStackTrace();
        }
    }

    /**
     * Abre la ventana para añadir un nuevo Driver
     */
    public void añadirConductor() {
        // Crea la vista del formulario de añadir conductor
        AddDriverView view = new AddDriverView();

        // Crea el controlador del formulario y le pasa la vista
        new AddDriverController(view, this);
    }

    public void eliminarConductor() {
        // Obtiene la fila seleccionada en la tabla
        int filaSeleccionada = driverPanel.getTablaVista().getSelectedRow();

        // Comprueba que haya una fila seleccionada
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(driverPanel, "Debes seleccionar un conductor para eliminar.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtiene el número del conductor seleccionado
        int numConductor = (int) driverPanel.getModeloTabla().getValueAt(filaSeleccionada, 0);

        // Solicita confirmación al usuario antes de eliminar
        int opcion = JOptionPane.showConfirmDialog(driverPanel,
                "¿Estás seguro de que quieres eliminar el conductor " + numConductor + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        // Si el usuario cancela, no hace nada
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        // Intenta eliminar el conductor de la base de datos
        boolean deleteSuccess = false;

        try (Connection con = ConnectionBD.getConexion()) {
            deleteSuccess = DriverDAO.deleteDriver(con, numConductor);
        } catch (SQLException e) {
            // Imprime información de error si ocurre una excepción SQL
            e.printStackTrace();
        }

        // Muestra el resultado de la operación al usuario
        if (deleteSuccess) {
            JOptionPane.showMessageDialog(driverPanel, "Conductor eliminado correctamente.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);

            // Recarga la tabla para reflejar los cambios
            cargarConductores();
        } else {
            JOptionPane.showMessageDialog(driverPanel, "Error al eliminar el conductor.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Abre la ventana para modificar un conductor seleccionado en la tabla
     */
    public void modificarConductor() {
        // Obtiene la fila seleccionada en la tabla
        int filaSeleccionada = driverPanel.getTablaVista().getSelectedRow();

        // Comprueba que haya una fila seleccionada
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(driverPanel, "Debes seleccionar un conductor para modificar.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtiene los datos del conductor seleccionado de la tabla
        int numConductor = (int) driverPanel.getModeloTabla().getValueAt(filaSeleccionada, 0);
        String nombre = (String) driverPanel.getModeloTabla().getValueAt(filaSeleccionada, 1);
        String apellido = (String) driverPanel.getModeloTabla().getValueAt(filaSeleccionada, 2);

        // Crea la vista del formulario pre-rellenada con los datos actuales
        ModifyConductorView modifyPanel = new ModifyConductorView(numConductor, nombre, apellido);

        // Crea el controlador del formulario y le pasa la vista
        new ModifyConductorController(modifyPanel, this);

    }
}
