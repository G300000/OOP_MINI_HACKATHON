package com.mycompany.loginpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AdminMode extends UserMode implements ActionListener{
    
    public static int[] counterValues = new int[4];
    
    private double balance;
    JLabel amountLabel;
    
    @Override
    void homePage() {
        try (BufferedReader reader = new BufferedReader(new FileReader("balance.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                balance = Double.parseDouble(line);
            } else {
                balance = 0.0; // Default value
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(GuestMode.class.getName()).log(Level.SEVERE, "Error reading balance.txt", ex);
            balance = 0.0; // Handle gracefully
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

        JFrame adminFrame = new JFrame("Total Amount Main Page: Admin Mode");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(null); // Absolute layout
        adminFrame.setSize(900, 500);
        adminFrame.getContentPane().setBackground(new Color(132, 206, 238));
        adminFrame.setVisible(true);

        // Create JPanel for "Total Amount Gained" section
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null); // Enable absolute positioning
        totalPanel.setBounds(100, 70, 700, 100); // Position and size
        totalPanel.setBackground(new Color(112, 205, 204));

        // Add content to the JPanel
        JLabel title = new JLabel("TOTAL AMOUNT GAINED:");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(10, 10, 300, 20); // Position inside the panel
        totalPanel.add(title);

        JLabel pesoSign = new JLabel("₱"); // Peso sign
        pesoSign.setFont(new Font("Arial", Font.PLAIN, 18));
        pesoSign.setBounds(10, 40, 20, 20); // Position inside the panel
        totalPanel.add(pesoSign);

        amountLabel = new JLabel("" + balance); // Example amount
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountLabel.setBounds(30, 40, 100, 20);
        totalPanel.add(amountLabel);

        // Add the panel to the frame
        adminFrame.add(totalPanel);

        // Add Buttons at the bottom
        JButton addButton = new JButton("Add/Subtract Amount");
        addButton.setBounds(20, 400, 180, 50);
        adminFrame.add(addButton);
        addButton.setBackground(new Color(112, 205, 204));
        adminFrame.setLocationRelativeTo(null);

        JButton materialButton = new JButton("<html><center>CHANGE<br>MATERIAL<br>QUANTITY</center></html>");
        materialButton.setBounds(225, 400, 180, 50);
        materialButton.setBackground(new Color(112, 205, 204));
        adminFrame.add(materialButton);
        

        JButton transactButton = new JButton("<html><center>TRANSACTION<br>HISTORY<br>EDITOR</center></html>");
        transactButton.setBounds(430, 400, 180, 50);
        transactButton.setBackground(new Color(112, 205, 204));
        adminFrame.add(transactButton);

        JButton proofButton = new JButton("<html><center>PROOF<br>LINK<br>EDITOR</center></html>");
        proofButton.setBounds(635, 400, 180, 50);
        proofButton.setBackground(new Color(112, 205, 204));
        adminFrame.add(proofButton);
        
        addButton.addActionListener(this); // Registering this instance as ActionListener
        materialButton.addActionListener(this);
        transactButton.addActionListener(this);
        proofButton.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks here
        JButton sourceButton = (JButton) e.getSource();
        
        if (sourceButton.getText().equals("Add/Subtract Amount")) {
            JFrame frame = new JFrame("Admin Mode");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 300);

            JTabbedPane tabbedPane = new JTabbedPane();

            JPanel adminPanel = new JPanel();
            adminPanel.setLayout(new GridLayout(4, 1, 10, 10));

            JLabel balanceLabel = new JLabel("Current Balance: " + balance);
            balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            adminPanel.add(balanceLabel);

            JLabel lblenterAmount = new JLabel("Enter Amount:");
            JTextField tfenterAmount = new JTextField();
            adminPanel.add(lblenterAmount);
            adminPanel.add(tfenterAmount);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));

            JButton addButton = new JButton("Add");
            JButton subtractButton = new JButton("Subtract");

            buttonPanel.add(addButton);
            buttonPanel.add(subtractButton);
            adminPanel.add(buttonPanel);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double amount = Double.parseDouble(tfenterAmount.getText());
                        if (amount < 0) {
                            JOptionPane.showMessageDialog(frame, "Amount cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        balance += amount;
                        balanceLabel.setText("Current Balance: " + balance);
                        amountLabel.setText("" + balance);
                        JOptionPane.showMessageDialog(frame, "Added " + amount + " to balance.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("balance.txt"));
                            writer.write("" + balance + "\n");
                            writer.close();

                        } catch (IOException ex) {
                            Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            subtractButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double amount = Double.parseDouble(tfenterAmount.getText());
                        if (amount < 0) {
                            JOptionPane.showMessageDialog(frame, "Amount cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (amount > balance) {
                            JOptionPane.showMessageDialog(frame, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        balance -= amount;
                        balanceLabel.setText("Current Balance: " + balance);
                        amountLabel.setText("" + balance );
                        JOptionPane.showMessageDialog(frame, "Subtracted " + amount + " from balance.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("balance.txt"));
                            writer.write("" + balance + "\n");
                            writer.close();

                        } catch (IOException ex) {
                            Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            tabbedPane.addTab("Admin Mode", adminPanel);

            frame.add(tabbedPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        else if (sourceButton.getText().contains("<html><center>CHANGE<br>MATERIAL<br>QUANTITY</center></html>")) {
            JFrame frame = new JFrame("Available Material Donations");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(1920, 1080);
            frame.setLayout(null); 

            ImageIcon icon = new ImageIcon("C://Users//Julianne Guardiario//Downloads//availablemats.png/"); // replace with new path
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
                spinners[i].setBounds(200 + (i * 350), 600, 100, 30);
                frame.add(spinners[i]);
            }

            //Records spinner values
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
        else if (sourceButton.getText().contains("<html><center>TRANSACTION<br>HISTORY<br>EDITOR</center></html>")) {
            JFrame historyFrame = new JFrame("Transaction History");
            historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            historyFrame.setSize(1920, 1080);
            historyFrame.setLayout(null);

            // Button to access Google Drive folder
            JButton driveButton = new JButton(new ImageIcon("C://Users//Julianne Guardiario//Downloads//CLICK BUTTON.jpg/")); // Update path
            driveButton.setBorder(null);
            driveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            driveButton.setBounds(855, 650, 250, 50);
            driveButton.addActionListener(evt -> {
                try {
                    Desktop.getDesktop().browse(URI.create("https://docs.google.com/spreadsheets/d/1c9G1XLmWcUGV5BswKg10uA5Axn7ydQQmYeEj8Tk7DlI/edit?gid=0#gid=0"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
             });
            historyFrame.add(driveButton);

            // Background image
            JLabel historyIllustration = new JLabel();
            historyIllustration.setBounds(0, -150, 1920, 1080);
            ImageIcon historyBackgroundLabel = new ImageIcon("/C://Users//Julianne Guardiario//Downloads//TransactionHistory.jpg/"); // Update path
            Image scaledImage1 = historyBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
            historyIllustration.setIcon(new ImageIcon(scaledImage1));
            historyFrame.add(historyIllustration);

            // Show the transaction history frame
            historyFrame.setLocationRelativeTo(null);
            historyFrame.setVisible(true);
        }
        else if (sourceButton.getText().contains("<html><center>PROOF<br>LINK<br>EDITOR</center></html>")) {
            JFrame proofFrame = new JFrame("LINK OF PROOFS");
            proofFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            proofFrame.setSize(1920, 1080);
            proofFrame.setLayout(null);

            JButton driveButton = new JButton(new ImageIcon("C://Users//Julianne Guardiario//Downloads//CLICK BUTTON.jpg/"));
            driveButton.setBorder(null);
            driveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            driveButton.setBounds(875, 700, 250, 50);
            driveButton.addActionListener(evt -> {
                try {
                    Desktop.getDesktop().browse(URI.create("https://drive.google.com/drive/folders/1_Nk9lLK6o4rcc5m4cnKNHMcUzu0lBrnW?usp=drive_link"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
             });
            
            proofFrame.add(driveButton);

            JLabel proofIllustration = new JLabel();
            proofIllustration.setBounds(0, -150, 1920, 1080);
            ImageIcon proofBackgroundLabel = new ImageIcon("C://Users//Julianne Guardiario//Downloads//GUEST_PROOF.png/"); 
            Image scaledImage3 = proofBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
            proofIllustration.setIcon(new ImageIcon(scaledImage3));
            proofFrame.add(proofIllustration);
            
            proofFrame.setLocationRelativeTo(null);
            proofFrame.setVisible(true);
        }
    }
}

