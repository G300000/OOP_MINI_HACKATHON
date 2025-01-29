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
        guestFrame.setSize(1000, 500);
        guestFrame.setLocationRelativeTo(null);
        guestFrame.setVisible(true);

        ImageIcon icon1 = new ImageIcon("C://Users//Julianne Guardiario//Downloads//bg_total_amount.png"); // replace if necessary
        Image img1 = icon1.getImage();
        Image resizedImg1 = img1.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImg1);

        JLabel backgroundLabel1 = new JLabel(resizedIcon1);
        backgroundLabel1.setBounds(0, 0, 1000, 500);
        
        // Amount Label
        JLabel pesoSign = new JLabel("₱");
        pesoSign.setFont(new Font("Arial", Font.PLAIN, 30));
        pesoSign.setBounds(500, 90, 50, 50); 
        guestFrame.add(pesoSign);

        JLabel amountLabel = new JLabel("" + balance); 
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        amountLabel.setBounds(530, 90, 100, 50);
        guestFrame.add(amountLabel);

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
        
        guestFrame.add(backgroundLabel1);
        
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
                valueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                valueLabel.setBounds(230 + (i * 350), 600, 100, 30); 
                valueLabel.setForeground(Color.BLACK); 
                displayFrame.add(valueLabel);
            }
            displayFrame.add(backgroundLabel);
            displayFrame.setVisible(true);
        }
        
        // Transaction History Button Function
        else if (sourceButton.getText().contains("TRANSACTION HISTORY")) {
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.TransactionHistory();
        }
        
        //Proof Link Button Function
        else if (sourceButton.getText().contains("<html><center>LINK/QR CODE<br>OF<br>PROOFS</center></html>")) {
            ProofLink proofLink = new ProofLink();
            proofLink.ProofLink();
        }
        
        // Donation Button Function
        else if (sourceButton.getText().contains("<html><center>DONATION<br>LINK/QR<br>CODE</center></html>")) {
            JFrame donationFrame = new JFrame("DONATION POOL");
            donationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            donationFrame.setSize(1920, 1080);
            donationFrame.setLayout(null);

            // Button to access Google Drive folder
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

            // Background Image
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
