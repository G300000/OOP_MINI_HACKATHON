package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPage {

    public static void main(String[] args){
        try {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("balance.txt", true));
            writer.write("0");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create the frame
        JFrame frame = new JFrame("HOME PAGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setResizable(false);

        // Background color
        frame.getContentPane().setBackground(new Color(132, 206, 238));

        JLabel illustration = new JLabel();
        illustration.setBounds(0, 25, 250, 300);
        ImageIcon originalIcon = new ImageIcon("C://Users//Julianne Guardiario//Downloads//LOGIN.png"); // Replace with actual image path
        Image scaledImage = originalIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        illustration.setIcon(new ImageIcon(scaledImage));
        frame.add(illustration);
        

        // Panel for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(300, 50, 250, 280);
        formPanel.setBackground(new Color(112, 205, 204));
        frame.add(formPanel);

        // Form title
        JLabel formTitle = new JLabel("Enter Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 16));
        formTitle.setBounds(60, 20, 150, 20);
        formPanel.add(formTitle);

        JLabel adminNote = new JLabel("For admin only");
        adminNote.setFont(new Font("Arial", Font.PLAIN, 12));
        adminNote.setBounds(90, 40, 100, 20);
        formPanel.add(adminNote);

        // Full Name label and field
        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setBounds(20, 70, 80, 20);
        formPanel.add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(20, 90, 200, 25);
        fullNameField.setBackground(new Color(112, 205, 204));
        formPanel.add(fullNameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 120, 80, 20);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 140, 200, 25);
        passwordField.setBackground(new Color(112, 205, 204));
        formPanel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 180, 160, 30);
        loginButton.setBackground(new Color(0, 200, 0));
        loginButton.setForeground(Color.WHITE);
        formPanel.add(loginButton);

        // Guest Button
        JButton guestButton = new JButton("Continue as Guest");
        guestButton.setBounds(50, 215, 160, 30);
        guestButton.setBackground(new Color(100, 150, 255));
        guestButton.setForeground(Color.WHITE);
        formPanel.add(guestButton);

        // Result Label
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(20, 160, 200, 20);
        resultLabel.setForeground(Color.RED);
        formPanel.add(resultLabel);
        
        JLabel powerLabel = new JLabel("POWERED BY: PO7YMORPHS");
        powerLabel.setFont(new Font("Arial", Font.ITALIC, 8));
        powerLabel.setBounds(128, 267, 250, 12);
        powerLabel.setForeground(Color.gray);
        formPanel.add(powerLabel);
        
        // Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fullNameField.getText();
                String password = new String(passwordField.getPassword());

                if ("111".equalsIgnoreCase(username) && "111".equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Welcome, Admin!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    UserMode userMode = new AdminMode();
                    userMode.homePage();
                } else {
                    resultLabel.setText("Incorrect username or password");
                }
            }
        });

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Welcome, Guest!", "Guest Access", JOptionPane.INFORMATION_MESSAGE);
                UserMode userMode = new GuestMode();
                userMode.homePage();
            }
        });

        // Show the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

