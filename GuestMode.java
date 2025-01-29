package com.mycompany.loginpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GuestMode extends UserMode implements ActionListener{
    
    private double balance;
    public static int[] counterValues = new int[4];

    @Override
    void homePage() {
        // Buffered Reader to Access Data stored in File
        try (BufferedReader reader = new BufferedReader(new FileReader("balance.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                balance = Double.parseDouble(line);
            } else {
                balance = 0.0; 
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(GuestMode.class.getName()).log(Level.SEVERE, "Error reading balance.txt", ex);
            balance = 0.0; 
        }
        
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
        
        JFrame guestFrame = new JFrame("Total Amount Main Page: Guest Mode");
        guestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guestFrame.setLayout(null); 
        guestFrame.getContentPane().setBackground(new Color(132, 206, 238));
        guestFrame.setSize(1000, 500);
        guestFrame.setLocationRelativeTo(null);
        guestFrame.setVisible(true);

        // Total Panel
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null); 
        totalPanel.setBounds(150, 70, 700, 100); 
        totalPanel.setBackground(new Color(112, 205, 204));

        // Title Label
        JLabel title = new JLabel("TOTAL AMOUNT GAINED:");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(10, 10, 300, 20); 
        totalPanel.add(title);

        JLabel pesoSign = new JLabel("₱"); 
        pesoSign.setFont(new Font("Arial", Font.PLAIN, 18));
        pesoSign.setBounds(10, 40, 20, 20); 
        totalPanel.add(pesoSign);

        // Amount Label
        JLabel amountLabel = new JLabel();
        amountLabel.setText("" + balance);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountLabel.setBounds(30, 40, 100, 20); 
        totalPanel.add(amountLabel);

        guestFrame.add(totalPanel);

        // Materials Button
        JButton materialsButton = new JButton("AVAILABLE MATERIALS");
        materialsButton.setBounds(70, 400, 200, 50); 
        materialsButton.setBackground(new Color(112, 205, 204));
        guestFrame.add(materialsButton);

        // Transaction History Button
        JButton historyButton = new JButton("TRANSACTION HISTORY");
        historyButton.setBounds(280, 400, 200, 50); 
        historyButton.setBackground(new Color(112, 205, 204));
        guestFrame.add(historyButton);

        // Proof Link Button
        JButton linkproofButton = new JButton("<html><center>LINK/QR CODE<br>OF<br>PROOFS</center></html>"); // Para gumitna
        linkproofButton.setBounds(490, 400, 200, 50); 
        linkproofButton.setBackground(new Color(112, 205, 204));
        guestFrame.add(linkproofButton);

        // Donation Button
        JButton donationButton = new JButton("<html><center>DONATION<br>LINK/QR<br>CODE</center></html>"); // Para gumitna
        donationButton.setBounds(710, 400, 200, 50); 
        donationButton.setBackground(new Color(112, 205, 204));
        guestFrame.add(donationButton);
        
        // Addition of Action Listener
        materialsButton.addActionListener(this);
        historyButton.addActionListener(this);
        linkproofButton.addActionListener(this);
        donationButton.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Recognize which button was selected
        JButton sourceButton = (JButton) e.getSource();
        
        // Materials Button Function
        if (sourceButton.getText().equals("AVAILABLE MATERIALS")) {
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
                valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
                valueLabel.setBounds(200 + (i * 350), 600, 150, 30); 
                valueLabel.setForeground(Color.BLACK); 
                displayFrame.add(valueLabel);
            }
            displayFrame.add(backgroundLabel);
            displayFrame.setVisible(true);
        }
        
        // Transaction History Button Function
        else if (sourceButton.getText().contains("TRANSACTION HISTORY")) {
            JFrame historyFrame = new JFrame("Transaction History");
            historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            historyFrame.setSize(1920, 1080);
            historyFrame.setLayout(null);

            // Button to access Google Drive folder
            JButton driveButton = new JButton(new ImageIcon("C://Users//Julianne Guardiario//Downloads//CLICK BUTTON.jpg")); // replace if necessary
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

            // Background Image
            JLabel historyIllustration = new JLabel();
            historyIllustration.setBounds(0, -150, 1920, 1080);
            ImageIcon historyBackgroundLabel = new ImageIcon("/C://Users//Julianne Guardiario//Downloads//TransactionHistory.jpg"); // replace if necessary
            Image scaledImage1 = historyBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
            historyIllustration.setIcon(new ImageIcon(scaledImage1));
            historyFrame.add(historyIllustration);

            historyFrame.setLocationRelativeTo(null);
            historyFrame.setVisible(true);
        }
        
        //Proof Link Button Function
        else if (sourceButton.getText().contains("<html><center>LINK/QR CODE<br>OF<br>PROOFS</center></html>")) {
            JFrame proofFrame = new JFrame("LINK OF PROOFS");
            proofFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            proofFrame.setSize(1920, 1080);
            proofFrame.setLayout(null);

            
            JButton driveButton = new JButton(new ImageIcon("C://Users//Julianne Guardiario//Downloads//CLICK BUTTON.jpg")); // replace if necessary
            driveButton.setBorder(null);
            driveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            driveButton.setBounds(875, 700, 250, 50);
            driveButton.addActionListener(evt -> {
                try {
                    Desktop.getDesktop().browse(URI.create("https://drive.google.com/drive/folders/1AQNh4rijSL968ayQR-Nl_mZE-TQ1_5Qh?usp=drive_link")); // replace if necessary
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
             });
            
            proofFrame.add(driveButton);

            JLabel proofIllustration = new JLabel();
            proofIllustration.setBounds(0, -150, 1920, 1080);
            ImageIcon proofBackgroundLabel = new ImageIcon("C://Users//Julianne Guardiario//Downloads//GUEST_PROOF.png"); // replace if necessary
            Image scaledImage3 = proofBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
            proofIllustration.setIcon(new ImageIcon(scaledImage3));
            proofFrame.add(proofIllustration);
            
            proofFrame.setLocationRelativeTo(null);
            proofFrame.setVisible(true);
        }
        else if (sourceButton.getText().contains("<html><center>DONATION<br>LINK/QR<br>CODE</center></html>")) {
            JFrame donationFrame = new JFrame("DONATION POOL");
            donationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            donationFrame.setSize(1920, 1080);
            donationFrame.setLayout(null);

            JButton driveButton = new JButton(new ImageIcon("C://Users//Julianne Guardiario//Downloads//CLICK BUTTON.jpg")); // replace if necessary
            driveButton.setBorder(null);
            driveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            driveButton.setBounds(875, 700, 250, 50);
            driveButton.addActionListener(evt -> {
                try {
                    Desktop.getDesktop().browse(URI.create("https://drive.google.com/drive/folders/1KecmlyhJGB92dDN92GAHZP58SK7HLVBF")); // replace if necessary
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
             });
            
            donationFrame.add(driveButton);

            JLabel donationIllustration = new JLabel();
            donationIllustration.setBounds(0, -150, 1920, 1080);
            ImageIcon donationBackgroundLabel = new ImageIcon("C://Users//Julianne Guardiario//Downloads//DONATION POOL.png"); // replace if necessary
            Image scaledImage2 = donationBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
            donationIllustration.setIcon(new ImageIcon(scaledImage2));
            donationFrame.add(donationIllustration);

            donationFrame.setLocationRelativeTo(null);
            donationFrame.setVisible(true);
        }
    }
}
