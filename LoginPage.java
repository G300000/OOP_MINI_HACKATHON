package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

    public static void main(String[] args){     
        JFrame frame = new JFrame("HOME PAGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setResizable(false);

        frame.getContentPane().setBackground(new Color(132, 206, 238));

        // PO74ACAUSE LOGO
        JLabel illustration = new JLabel();
        illustration.setBounds(0, 25, 250, 300);
        ImageIcon originalIcon = new ImageIcon("C://Users//Julianne Guardiario//Downloads//LOGIN.png"); // Replace if necesasary
        Image scaledImage = originalIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        illustration.setIcon(new ImageIcon(scaledImage));
        frame.add(illustration);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(300, 50, 250, 280);
        formPanel.setBackground(new Color(112, 205, 204));
        frame.add(formPanel);

        // Form Title
        JLabel formTitle = new JLabel("Enter Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 16));
        formTitle.setBounds(60, 20, 150, 20);
        formPanel.add(formTitle);

        // Admin Only Label
        JLabel adminNote = new JLabel("For admin only");
        adminNote.setFont(new Font("Arial", Font.PLAIN, 12));
        adminNote.setBounds(90, 40, 100, 20);
        formPanel.add(adminNote);

        // Name Label
        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setBounds(20, 70, 80, 20);
        formPanel.add(fullNameLabel);

        // Name Textfield
        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(20, 90, 200, 25);
        fullNameField.setBackground(new Color(112, 205, 204));
        formPanel.add(fullNameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 120, 80, 20);
        formPanel.add(passwordLabel);

        // Password Textfield
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 140, 200, 25);
        passwordField.setBackground(new Color(112, 205, 204));
        formPanel.add(passwordField);

        // Admin Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 180, 160, 30);
        loginButton.setBackground(new Color(0, 200, 0));
        loginButton.setForeground(Color.WHITE);
        formPanel.add(loginButton);

        // Guest Login Button
        JButton guestButton = new JButton("Continue as Guest");
        guestButton.setBounds(50, 215, 160, 30);
        guestButton.setBackground(new Color(100, 150, 255));
        guestButton.setForeground(Color.WHITE);
        formPanel.add(guestButton);

        // Incorrect Password Label
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(20, 160, 200, 20);
        resultLabel.setForeground(Color.RED);
        formPanel.add(resultLabel);
        
        // Powered by Label
        JLabel powerLabel = new JLabel("POWERED BY: PO7YMORPHS");
        powerLabel.setFont(new Font("Arial", Font.ITALIC, 8));
        powerLabel.setBounds(128, 267, 250, 12);
        powerLabel.setForeground(Color.gray);
        formPanel.add(powerLabel);
        
        //AdminMode Action Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fullNameField.getText();
                String password = new String(passwordField.getPassword());

                if ("111".equalsIgnoreCase(username) && "111".equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Welcome, Admin!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    UserMode userMode = new AdminMode(); // Call AdminMode Class
                    userMode.homePage();
                } else {
                    resultLabel.setText("Incorrect username or password");
                }
            }
        });

        //GuestMode Action Listener
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Welcome, Guest!", "Guest Access", JOptionPane.INFORMATION_MESSAGE);
                UserMode userMode = new GuestMode(); // Call GuestMode Class
                userMode.homePage();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

