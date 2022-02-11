import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField1;
    private JLabel lblEnterNewPassword;
    private JLabel lblEnterNewPassword1;

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
    public ChangePassword(String name) {
        setBounds(450, 360, 1300, 512);
        // Set window bounds
        setResizable(false);
        // cant move window 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 34));
        textField.setBounds(450, 300, 609, 67);
        contentPane.add(textField);
        textField.setColumns(10);
        
        textField1 = new JTextField();
        textField1.setFont(new Font("Tahoma", Font.PLAIN, 34));
        textField1.setBounds(450, 150, 609, 67);
        contentPane.add(textField1);
        textField1.setColumns(10);
        
        JButton btnSearch = new JButton("Enter");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String curPW = textField1.getText();
                String newPW = textField.getText();
                try {
                    System.out.println("update password name " + name);
                    System.out.println("update password");

                    String url = "jdbc:mysql://localhost:3307/ibm_login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=False";

                    Connection con = (Connection) DriverManager.getConnection(url, name, curPW);

                    String query = "ALTER USER '" + name + "'@'localhost' IDENTIFIED BY '" + newPW + "'";
                    
                    Statement st = con.createStatement();

                    int rs = st.executeUpdate(query);
                    
                    System.out.println("Password is changed successfully!");
                    
                    st.close();
                    //PreparedStatement st = (PreparedStatement) con
                    //    .prepareStatement("Update student set password=? where name=?");

                    //st.setString(1, pstr);
                    //st.setString(2, name);
                    //st.executeUpdate();
                    JOptionPane.showMessageDialog(btnSearch, "Password has been successfully changed");

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 29));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(438, 400, 170, 59);
        contentPane.add(btnSearch);

        lblEnterNewPassword = new JLabel("Enter New Password :");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblEnterNewPassword.setBounds(45, 300, 326, 67);
        contentPane.add(lblEnterNewPassword);
        
        lblEnterNewPassword1 = new JLabel("Enter Current Password :");
        lblEnterNewPassword1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblEnterNewPassword1.setBounds(45, 150, 400, 67);
        contentPane.add(lblEnterNewPassword1);
        
        
    }
}