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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.context.StaticFeatureManagerProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;

public class Main extends JFrame {

	// Password to SQL is base64 encoded
	
	private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnRegister;
    private JButton resetPass;
    private JLabel label;   
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		setBounds(450, 190, 1000, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
		
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);
        
        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String url = "jdbc:mysql://localhost:3307/ibm_login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=False";
        		String userName = textField.getText();
        		String password = String.valueOf(passwordField.getPassword());

        		if (userName != null && password != null) {

        			try {
        				Connection connection = (Connection) DriverManager.getConnection(url, userName, password);

        				//PreparedStatement st = (PreparedStatement) connection
        				//    .prepareStatement("Select name, password from student where name=? and password=?");

        				//st.setString(1, userName);
        				//st.setString(2, password);
        				//ResultSet rs = st.executeQuery();
        				//System.out.println(rs);
        				//if (rs.next()) {
        				dispose();
        				UserHome ah = new UserHome(userName);
        				ah.setTitle("Welcome");
        				ah.setVisible(true);
        				JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
        				//} else {
        				//    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
        				//}
        			} catch (SQLException sqlException) {
        				sqlException.printStackTrace();
        			}
        		}
        	}
        });
        
        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnRegister.setBounds(545, 500, 162, 73);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	RegisterUser ah = new RegisterUser();
            	ah.setTitle("Register user");
            	ah.setVisible(true);
            }
        });
        
        
        resetPass = new JButton("Forgot password");
        resetPass.setFont(new Font("Tahoma", Font.PLAIN, 26));
        resetPass.setBounds(150, 500, 300, 73);
        resetPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	resetPassword ah = new resetPassword();
            	ah.setTitle("Forgot password");
            	ah.setVisible(true);
            }
        });
        
        contentPane.add(resetPass);
        contentPane.add(btnNewButton);
        contentPane.add(btnRegister);
        
        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
	
}
