package com.mycompany.loginpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GuestMaterials extends Materials{
    
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
            Logger.getLogger(GuestMode.class.getName()).log(Level.SEVERE, null, ex);
            for (int i = 0; i < 4; i++) {
                counterValues[i] = 0;
            }
        }
        
        JFrame displayFrame = new JFrame("Recorded Values");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setSize(1920, 1080);
        displayFrame.setLayout(null); 

        // Background Image
        ImageIcon icon = new ImageIcon("C://Users//Julianne Guardiario//Downloads//availablemats.png"); // replace if necessary
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(1600, 800, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        JLabel backgroundLabel = new JLabel(resizedIcon);
        backgroundLabel.setBounds(-160, -150, 1920, 1080);

        // Display Values
        for (int i = 0; i < 4; i++) {
            JLabel valueLabel = new JLabel("Amount: " + counterValues[i]); 
            valueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            valueLabel.setBounds(230 + (i * 350), 600, 100, 30); 
            valueLabel.setForeground(Color.BLACK); 
            displayFrame.add(valueLabel);
        }
        displayFrame.add(backgroundLabel);
        displayFrame.setVisible(true);
    }
}
