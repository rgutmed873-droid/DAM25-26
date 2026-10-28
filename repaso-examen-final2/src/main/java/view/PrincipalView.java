package main.java.view;

import main.java.view.Driver.DriverPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Clase principal de la view
 * Vista principal de la aplicación
 * Contiene el panel de pestañas con las secciones de la aplicacion
 * Y también la barra de herramientas con los botones
 */
public class PrincipalView extends JFrame {

    //ATRIBUTOS

    //Panel de pestañas principal que agrupa
    private JTabbedPane tabs = new JTabbedPane();

    //Paneles de cada sección
    private DriverPanel driverPanel = new DriverPanel();

    //Etiqueta de estado en la parte superior de la ventana
    private JLabel etiquetaEstado = new JLabel("Estado aplicación..");

    //Botones de la barra de herramientas
    JButton btnAdd = new JButton("Añadir");
    JButton btnDelete = new JButton("Borrar");
    JButton btnUpdate = new JButton("Actualizar");
    JButton btnModify = new JButton("Modificar");

    //Botones de la zona norte que sería la de arriba de la ventana
    JPanel panelNorte = new JPanel();
    JPanel botoneriaPanel = new JPanel();

    //CONSTRUCTOR

    public PrincipalView(){
        super("Aucorsa - Ventana principal");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500,500);

        setLayout(new BorderLayout());

        //Añadir cada sección que tenga como una pestaña del panel principal
        //En este caso voy añadir solo el Driver
        tabs.add("Drivers", driverPanel);

        //Coloco el panel de pestañas en el centro de la ventana
        add(tabs, BorderLayout.CENTER);

        //Configurar el panel norte con BorderLayout para separar botones y etiqueta
        panelNorte.setLayout(new BorderLayout());

        //Añado los botones de acción al panel de botones
        botoneriaPanel.setLayout(new FlowLayout());
        botoneriaPanel.add(btnAdd);
        botoneriaPanel.add(btnDelete);
        botoneriaPanel.add(btnUpdate);
        botoneriaPanel.add(btnModify);

        //Coloco los botones y etiqueta de estado abajo en el norte
        panelNorte.add(botoneriaPanel, BorderLayout.NORTH);
        panelNorte.add(etiquetaEstado, BorderLayout.SOUTH);

        //Añadir al panel norte a la ventana
        add(panelNorte, BorderLayout.NORTH);
    }

    //GETTERS AND SETTERS

    public JPanel getBotoneriaPanel() {
        return botoneriaPanel;
    }

    public void setBotoneriaPanel(JPanel botoneriaPanel) {
        this.botoneriaPanel = botoneriaPanel;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(JButton btnModify) {
        this.btnModify = btnModify;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public DriverPanel getDriverPanel() {
        return driverPanel;
    }

    public void setDriverPanel(DriverPanel driverPanel) {
        this.driverPanel = driverPanel;
    }

    public JLabel getEtiquetaEstado() {
        return etiquetaEstado;
    }

    public void setEtiquetaEstado(JLabel etiquetaEstado) {
        this.etiquetaEstado = etiquetaEstado;
    }

    public JPanel getPanelNorte() {
        return panelNorte;
    }

    public void setPanelNorte(JPanel panelNorte) {
        this.panelNorte = panelNorte;
    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setTabs(JTabbedPane tabs) {
        this.tabs = tabs;
    }
}
