package main.java.view.Driver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DriverPanel extends JPanel {

    //ATRIBUTOS

    //Tabla visual donde se mostrarán los conductores
    JTable tablaVista = new JTable();

    //Modelo de datos que alimenta la tabla
    DefaultTableModel modeloTabla = new DefaultTableModel();

    //CONSTRUCTOR

    public DriverPanel() {

        setLayout(new BorderLayout());

        //Inicializar el modelo con las columnas de Conductor, sin filas iniciales
        modeloTabla = new DefaultTableModel(
                new String[]{"NumDriver", "Name", "Surname"}, 0){

            //Hace no editable ninguna celda de la tabla
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        //Asocia el modelo a la tabla
        tablaVista = new JTable(modeloTabla);

        //Configurar el comportamiento de selección de la tabla
        tablaVista.setFillsViewportHeight(true);
        tablaVista.setRowSelectionAllowed(true);
        tablaVista.setColumnSelectionAllowed(false);
        tablaVista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Añade la tabla dentro de un JScrollPane para permitir desplazamientos
        add(new JScrollPane(tablaVista), BorderLayout.CENTER);

    }

    //GETTERS


    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JTable getTablaVista() {
        return tablaVista;
    }

    public void setTablaVista(JTable tablaVista) {
        this.tablaVista = tablaVista;
    }
}
