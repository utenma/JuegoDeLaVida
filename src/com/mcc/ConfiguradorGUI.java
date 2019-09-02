package com.mcc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfiguradorGUI {

    public ConfiguradorGUI() {
        EjecutarConfiguradorGeneral();
    }

    private void EjecutarConfiguradorGeneral(){
        System.out.println("Maestría en Ciencias de la Computación");
        System.out.println("Tecnologías de Programación");
        System.out.println("Juego de la Vida de John H. Conway\n");

        JFrame frame = new JFrame();
        frame.setTitle("Juego de La Vida Configuracion Inicial");
        frame.setBounds(10,20,300,300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        frame.add(panel);

        JLabel numFilasL = new JLabel("Numero de Filas :");
        JLabel numColumnasL = new JLabel("Numero de Columnas :");
        JLabel numGeneracionesL = new JLabel("Numero de Generaciones :");
        JLabel numOrganismosL = new JLabel("<html>Numero de<br>Organismos %</html>");
        JTextField numFilasT = new  JTextField("4");
        JTextField numColumnasT = new  JTextField("4");
        JTextField numGeneracionesT = new  JTextField("10");

        JSlider numOrganismosS = new JSlider(0,50,25);
        numOrganismosS.setMajorTickSpacing(10);
        numOrganismosS.setPaintTicks(true);
        numOrganismosS.setPaintLabels(true);

        JButton next =  new JButton("Continuar");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdministradorDeJuego.tablero = new Tablero(Integer.parseInt(numFilasT.getText()), Integer.parseInt(numColumnasT.getText()), Integer.parseInt(numGeneracionesT.getText()), numOrganismosS.getValue());
                EjecutarAsistenteDeCoordenadas();
                AdministradorDeJuego.IniciarJuego();
            }
        });

        panel.add(numFilasL);
        panel.add(numFilasT);
        panel.add(numColumnasL);
        panel.add(numColumnasT);
        panel.add(numGeneracionesL);
        panel.add(numGeneracionesT);
        panel.add(numOrganismosL);
        panel.add(numOrganismosS);
        panel.add(next);

        frame.setVisible(true);
    }

    private void EjecutarAsistenteDeCoordenadas() {

        for (int i = 0; i < Tablero.ObtenerNumeroDeOrganismos(); i++) {
            JTextField xField = new JTextField(4);
            JTextField yField = new JTextField(4);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("x:"));
            myPanel.add(xField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("y:"));
            myPanel.add(yField);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Organismo " + i + " : x = " + xField.getText() + " y = " + yField.getText());
            }
            AdministradorDeJuego.tablero.celdas[Integer.parseInt(yField.getText())][Integer.parseInt(xField.getText())].accion = Accion.Añadir;
        }
    }
}
