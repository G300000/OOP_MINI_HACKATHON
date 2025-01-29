package com.mycompany.loginpage;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TransactionHistory {
    
    void TransactionHistory(){
        JFrame historyFrame = new JFrame("Transaction History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historyFrame.setSize(1920, 1080);
        historyFrame.setLayout(null);

        // Button to access Google Drive folder
        JButton driveButton = new JButton(new ImageIcon("C://Users//Julianne Guardiario//Downloads//CLICK BUTTON.jpg/")); // replace if necessary
        driveButton.setBorder(null);
        driveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        driveButton.setBounds(855, 650, 250, 50);
        driveButton.addActionListener(evt -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://docs.google.com/spreadsheets/d/1c9G1XLmWcUGV5BswKg10uA5Axn7ydQQmYeEj8Tk7DlI/edit?gid=0#gid=0")); // replace if necessary
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        historyFrame.add(driveButton);

        // Background image
        JLabel historyIllustration = new JLabel();
        historyIllustration.setBounds(0, -150, 1920, 1080);
        ImageIcon historyBackgroundLabel = new ImageIcon("/C://Users//Julianne Guardiario//Downloads//TransactionHistory.jpg/"); // replace if necessary
        Image scaledImage1 = historyBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
        historyIllustration.setIcon(new ImageIcon(scaledImage1));
        historyFrame.add(historyIllustration);

        historyFrame.setLocationRelativeTo(null);
        historyFrame.setVisible(true);
    }
}
