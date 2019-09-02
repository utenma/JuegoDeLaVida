package com.mcc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfiguradorGUI {

    public ConfiguradorGUI() {
        ConfiguracionInicial();
    }

        public void ConfiguracionInicial(){
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
            JTextField numFilasT = new  JTextField("6");
            JTextField numColumnasT = new  JTextField("6");
            JTextField numGeneracionesT = new  JTextField("10");

            JSlider numOrganismosS = new JSlider(0,50,50);
            numOrganismosS.setMajorTickSpacing(10);
            numOrganismosS.setPaintTicks(true);
            numOrganismosS.setPaintLabels(true);

            JButton next =  new JButton("Continuar");
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdministradorDeJuego.GenerarTablero(Integer.parseInt(numFilasT.getText()), Integer.parseInt(numColumnasT.getText()), Integer.parseInt(numGeneracionesT.getText()), numOrganismosS.getValue());
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

    public static void ConfigurarCoordenadasDeOrganismosIniciales(){
        for (int i = 1; i <=  AdministradorDeJuego.tablero.NumeroDeOrganismosIniciales; i++) {
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
            AdministradorDeJuego.tablero.celdas[Integer.parseInt(yField.getText())][Integer.parseInt(xField.getText())].organismo = true;
        }
    }
}
