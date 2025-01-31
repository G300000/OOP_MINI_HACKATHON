package com.mycompany.loginpage;

import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AdminMaterials extends Materials{
    void materials(){
        int[] counterValues = new int[4];
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("materials.txt"));
            for (int i = 0; i < 4; i++) {
                String str = reader.readLine();
                counterValues[i] = Integer.parseInt(str);
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
            for (int i = 0; i < 4; i++) {
                counterValues[i] = 0;
            }
        }
        
        JFrame frame = new JFrame("Available Material Donations");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(null); 

        ImageIcon icon = new ImageIcon("C://Users//Julianne Guardiario//Downloads//availablemats.png/"); // replace if necessary
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(1600, 800, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel backgroundLabel = new JLabel(resizedIcon);
        backgroundLabel.setBounds(-160, -150, 1920, 1080);

        // Spinners
        JSpinner[] spinners = new JSpinner[4];
        for (int i = 0; i < 4; i++) {
            spinners[i] = new JSpinner(new SpinnerNumberModel(counterValues[i], 0, 100, 1));
            spinners[i].setFont(new Font("Arial", Font.BOLD, 20));
            spinners[i].setBounds(230 + (i * 350), 600, 100, 30);
            frame.add(spinners[i]);
        }

        // Record Button to Record Changes in File
        JButton recordButton = new JButton("Record Values");
        recordButton.setFont(new Font("Arial", Font.BOLD, 20));
        recordButton.setBounds(675, 700, 200, 50); 
        recordButton.addActionListener(k -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("materials.txt"));
                for (int i = 0; i < spinners.length; i++) {
                    counterValues[i] = (int) spinners[i].getValue();
                    writer.write(counterValues[i] + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        frame.add(recordButton);
        frame.add(backgroundLabel);
        frame.setVisible(true);
    }
}
