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

public class ProofLink {
    
    void ProofLink(){
        JFrame proofFrame = new JFrame("LINK OF PROOFS");
        proofFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        proofFrame.setSize(1920, 1080);
        proofFrame.setLayout(null);

        // Button to access Google Drive folder
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

        // Background Image
        JLabel proofIllustration = new JLabel();
        proofIllustration.setBounds(0, -150, 1920, 1080);
        ImageIcon proofBackgroundLabel = new ImageIcon("C://Users//Julianne Guardiario//Downloads//GUEST_PROOF.png"); // replace if necessary
        Image scaledImage3 = proofBackgroundLabel.getImage().getScaledInstance(1600,800, Image.SCALE_SMOOTH);
        proofIllustration.setIcon(new ImageIcon(scaledImage3));
        proofFrame.add(proofIllustration);
            
        proofFrame.setLocationRelativeTo(null);
        proofFrame.setVisible(true);
    }
}
