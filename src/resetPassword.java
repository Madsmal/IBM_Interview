import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class resetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton resetPass;
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserHome frame = new UserHome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public resetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 140, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(150, 140, 193, 52);
        contentPane.add(lblUsername);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 260, 281, 68);
        contentPane.add(passwordField);
        
        JLabel lblPassword = new JLabel("New Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(150, 260, 300, 52);
        contentPane.add(lblPassword);
        
        JLabel lblNewLabel = new JLabel("Reset password");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(400, 13, 350, 93);
        contentPane.add(lblNewLabel);
        
        resetPass = new JButton("Register");
        resetPass.setFont(new Font("Tahoma", Font.PLAIN, 26));
        resetPass.setBounds(545, 500, 162, 73);
        resetPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String url = "jdbc:mysql://localhost:3307/ibm_login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=False";
            	String userName = textField.getText();
            	String newpassword = String.valueOf(passwordField.getPassword());
            	if(userName != null && newpassword != null) {
            		try {
                		Connection connection = (Connection) DriverManager.getConnection(url, "root", "root");

                		String query = "ALTER USER '" + userName + "'@'localhost' IDENTIFIED BY '" + newpassword +"';";
                		
                		Statement st = connection.createStatement();

                		int rs = st.executeUpdate(query);
                		st.close();

                		System.out.println("Password updated!");
                		System.out.println("User has been created!");
                		JOptionPane.showMessageDialog(resetPass, "Password succesfully changed");
                		
                		dispose();
                		Main obj = new Main();
                        obj.setTitle("Student-Login");
                        obj.setVisible(true);
                		//PreparedStatement st = (PreparedStatement) connection
                		//    .prepareStatement("Select name, password from student where name=? and password=?");

                		//st.setString(1, userName);
                		//st.setString(2, password);
                		//ResultSet rs = st.executeQuery();
                		//System.out.println(rs);
                		//if (rs.next()) {
                		//} else {
                		//    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                		//}
                	} catch (SQLException sqlException) {
                		sqlException.printStackTrace();
                	}
            		
            	}
            	
            }
        });
        contentPane.add(resetPass);
        
        
	}
	
	
}
