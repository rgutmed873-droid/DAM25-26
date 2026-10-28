package main.java.app;

import main.java.controller.MainController;
import main.java.view.PrincipalView;

public class Principal {
    static void main(String[] args) {

        //Creo la vista principal de la aplicacion
        PrincipalView ventanaPrincipal = new PrincipalView();

        //Creo al controlador principal que le paso la visa
        new MainController(ventanaPrincipal);

        //Hago visible a la ventana principal
        ventanaPrincipal.setVisible(true);
    }
}
