package it.login;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblEnterNewPassword;
    private JPasswordField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ChangePassword(String username) {
    	setTitle("Cambio Password");
        setBounds(450, 360, 369, 187);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSearch = new JButton("Enter");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pstr = textField.getText();
                try {
                    System.out.println("update password name " + username);
                    System.out.println("update password");

                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionemagazzino",
                        "root", "root");

                    PreparedStatement st = (PreparedStatement) con
                        .prepareStatement("Update login set password=? where username=?");

                    st.setString(1, pstr);
                    st.setString(2, username);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(btnSearch, "Password has been successfully changed");

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(10, 106, 124, 31);
        contentPane.add(btnSearch);

        lblEnterNewPassword = new JLabel("Nuova Password:");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEnterNewPassword.setBounds(10, 37, 207, 67);
        contentPane.add(lblEnterNewPassword);
        
        textField = new JPasswordField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBounds(171, 55, 176, 31);
        contentPane.add(textField);
    }
}