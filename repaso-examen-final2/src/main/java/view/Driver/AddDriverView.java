package main.java.view.Driver;

import javax.swing.*;
import java.awt.*;

public class AddDriverView extends JFrame {

    //ATRIBUTOS
    private final JButton btnAdd = new JButton("Añadir");
    private final JButton btnCancelar = new JButton("Cancelar");

    private final JTextField nombre = new JTextField(10);
    private final JTextField apellido = new JTextField(10);
    private final JTextField numDriver = new JTextField(10);

    //CONTRUCTOR
    public AddDriverView(){
        this.setTitle("Añadir Conductor");

        //Establecer el tamaño y posición de la ventana
        setSize(300,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Panel de campos con GridLayout
        JPanel camposPanel = new JPanel(new GridLayout(3,2,10,10));
        camposPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        camposPanel.add(new JLabel("Nombre"));
        camposPanel.add(nombre);
        camposPanel.add(new JLabel("Apellido"));
        camposPanel.add(apellido);
        camposPanel.add(new JLabel("NumDriver"));
        camposPanel.add(numDriver);

        //Panel de botones con FlowLayout centrado
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        botonesPanel.add(btnAdd);
        botonesPanel.add(btnCancelar);

        //Añade los paneles a la ventana y la hace visible
        add(camposPanel, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    //GETTERS

    public JTextField getApellido() {
        return apellido;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getNumDriver() {
        return numDriver;
    }
}
