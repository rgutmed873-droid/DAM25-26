package main.java.controller;

import main.java.view.PrincipalView;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Clase de mainController
 * Controlador principal de la aplicación
 * Coordina los controladores de cada sección y gestiona los eventos
 * de la ventana principal
 */
public class MainController {

    //ATRIBUTOS
    private final DriverController driverControler;

    //Vista principal de la aplicación
    PrincipalView principalView;

    //CONSTRUCTOR
    public MainController(PrincipalView view){
        this.principalView = view;

        //Inicializa el controlador de buses al arrancar la aplicación
        driverControler = new DriverController(principalView.getDriverPanel());

        //Asocia el botón Refrescar con la acción correspondiente a la pestaña activa
        principalView.getBtnUpdate().addActionListener(e -> refrescarTabla());

        //Asocia el boton añadir con la acción correspondiente a la pestaña activa
        principalView.getBtnAdd().addActionListener(e -> addDriver());

        //Asocia el boton de eliminar con la acción correspondiente a la pestaña activa
        principalView.getBtnDelete().addActionListener(e -> deleteDriver());

        //Asocia el boton de modificar con la acción correspondiente a la pestaña activa
        principalView.getBtnModify().addActionListener(e -> modifyDriver());

        //Gestiona los eventos de la ventana principal
        principalView.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                //Solicita confirmación al usuario antes de cerrar
                int resultado = JOptionPane.showConfirmDialog(
                        principalView,
                        "Estas seguro de que quieres cerrar?",
                        "¿Seguro?",
                        JOptionPane.YES_NO_OPTION
                );

                //Cierra la aplicación solo si el usuario confirma
                if (resultado == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });


    }

    //METODOS
    /**
     * Recarga los datos de la tabla según la pestaña activa en ese momento.
     */
    private void refrescarTabla() {
        // Obtiene el nombre de la pestaña seleccionada actualmente
        int tabSelected = principalView.getTabs().getSelectedIndex();
        String tab = principalView.getTabs().getTitleAt(tabSelected);

        switch (tab) {
            case "Bus":
               // busController.cargarBuses();
                break;
            case "Drivers":
                driverControler.cargarConductores();
                break;
            case "Route":
                // TODO: implementar cuando exista RouteController
                break;
        }
    }

    /**
     * Ejecuta la acción de añadir según la pestaña activa en ese momento.
     */
    private void addDriver() {

        // Obtiene el nombre de la pestaña seleccionada actualmente
        int tabSelected = principalView.getTabs().getSelectedIndex();
        String tab = principalView.getTabs().getTitleAt(tabSelected);

        switch (tab) {
            case "Bus":
                //busController.añadirBus();
                break;
            case "Drivers":
                driverControler.añadirConductor();
                break;
            case "Route":
                // TODO: implementar AddRouteView y AddRouteController
                JOptionPane.showMessageDialog(principalView, "estas en: " + tab);
                break;
        }
    }

    /**
     * Ejecuta la acción de eliminar según la pestaña activa en ese momento.
     */
    private void deleteDriver() {

        // Obtiene el nombre de la pestaña seleccionada actualmente
        int tabSelected = principalView.getTabs().getSelectedIndex();
        String tab = principalView.getTabs().getTitleAt(tabSelected);

        switch (tab) {
            case "Bus":
                //busController.eliminarBus();
                break;
            case "Drivers":
                driverControler.eliminarConductor();
                break;
            case "Route":
                // TODO: implementar cuando exista RouteController
                break;
        }
    }

    /**
     * Ejecuta la acción de modificar según la pestaña activa en ese momento.
     */
    private void modifyDriver() {

        // Obtiene el nombre de la pestaña seleccionada actualmente
        int tabSelected = principalView.getTabs().getSelectedIndex();
        String tab = principalView.getTabs().getTitleAt(tabSelected);

        switch (tab) {
            case "Bus":
                //busController.modificarBus();
                break;
            case "Drivers":
                driverControler.modificarConductor();
                break;
            case "Route":
                // TODO: implementar cuando exista RouteController
                break;
        }
    }

}
