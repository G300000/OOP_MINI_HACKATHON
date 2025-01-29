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
        // Buffered Reader for already existing data
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

        // Frame
        JFrame adminFrame = new JFrame("Total Amount Main Page: Admin Mode");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(null);
        adminFrame.setSize(900, 500);
        adminFrame.getContentPane().setBackground(new Color(132, 206, 238));
        adminFrame.setVisible(true);

        // Total Panel
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null); 
        totalPanel.setBounds(100, 70, 700, 100);
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
        amountLabel = new JLabel("" + balance); 
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountLabel.setBounds(30, 40, 100, 20);
        totalPanel.add(amountLabel);

        adminFrame.add(totalPanel);

        // Add and Subtract Button
        JButton addButton = new JButton("Add/Subtract Amount");
        addButton.setBounds(20, 400, 180, 50);
        adminFrame.add(addButton);
        addButton.setBackground(new Color(112, 205, 204));
        adminFrame.setLocationRelativeTo(null);

        // Change Materials Button
        JButton materialButton = new JButton("<html><center>CHANGE<br>MATERIAL<br>QUANTITY</center></html>");
        materialButton.setBounds(225, 400, 180, 50);
        materialButton.setBackground(new Color(112, 205, 204));
        adminFrame.add(materialButton);
        
        // Transaction History Button
        JButton transactButton = new JButton("<html><center>TRANSACTION<br>HISTORY<br>EDITOR</center></html>");
        transactButton.setBounds(430, 400, 180, 50);
        transactButton.setBackground(new Color(112, 205, 204));
        adminFrame.add(transactButton);

        // Proof Link Button
        JButton proofButton = new JButton("<html><center>PROOF<br>LINK<br>EDITOR</center></html>");
        proofButton.setBounds(635, 400, 180, 50);
        proofButton.setBackground(new Color(112, 205, 204));
        adminFrame.add(proofButton);
        
        // Addition of Action Listeners
        addButton.addActionListener(this); 
        materialButton.addActionListener(this);
        transactButton.addActionListener(this);
        proofButton.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Recognize which button was selected
        JButton sourceButton = (JButton) e.getSource();
        
        // Add/Subtract Button Function
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

            // Addition Button
            JButton addButton = new JButton("Add");
            
            // Subtractiob Button
            JButton subtractButton = new JButton("Subtract");

            buttonPanel.add(addButton);
            buttonPanel.add(subtractButton);
            adminPanel.add(buttonPanel);

            // Addition Implementation
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
                        
                        // Save Changes in File
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

            // Subtraction Implementation
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
                        
                        // Save Changes in File
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
        
        // Change Materials Button Function
        else if (sourceButton.getText().contains("<html><center>CHANGE<br>MATERIAL<br>QUANTITY</center></html>")) {
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
                spinners[i].setBounds(200 + (i * 350), 600, 100, 30);
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
        
        // Transaction History Button Function
        else if (sourceButton.getText().contains("<html><center>TRANSACTION<br>HISTORY<br>EDITOR</center></html>")) {
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.TransactionHistory();
        }
        
        // Proof Link Button Function
        else if (sourceButton.getText().contains("<html><center>PROOF<br>LINK<br>EDITOR</center></html>")) {
            ProofLink proofLink = new ProofLink();
            proofLink.ProofLink();
        }
    }
}

