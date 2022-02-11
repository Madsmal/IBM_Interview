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

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.context.StaticFeatureManagerProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnRegister;

	public static void main(String[] args) {

		final FeatureManager featureManager = FeatureManagerBuilder
				.begin()
				.featureEnum(MyFeatures.class)
				.build();
		StaticFeatureManagerProvider.setFeatureManager(featureManager);
		System.out.println(MyFeatures.A.isActive());

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

	public enum MyFeatures implements Feature {
		@EnabledByDefault A,
		B;

		public boolean isActive() {
			return FeatureContext.getFeatureManager()
					.isActive(this);
		}
	}

	public RegisterUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
		lblNewLabel.setBounds(423, 13, 273, 93);
		contentPane.add(lblNewLabel);

		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnRegister.setBounds(545, 400, 162, 73);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:mysql://localhost:3307/ibm_login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=False";
				String userName = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if (userName != null && password != null) {
					try {
						Connection connection = (Connection) DriverManager.getConnection(url, "root", "root");

						String query = "CREATE USER '" + userName + "'@'localhost' IDENTIFIED BY '" + password +"';";

						Statement st = connection.createStatement();

						int rs = st.executeUpdate(query);
						st.close();


						String grantAccess = "GRANT ALL privileges ON *.* TO '" + userName + "'@'localhost';";
						Statement st1 = connection.createStatement();

						int rs1 = st1.executeUpdate(grantAccess);

						st1.close();

						System.out.println("User has been created!");
						System.out.println("User has been created!");
						JOptionPane.showMessageDialog(btnRegister, "User succesfully created");

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
				else {
					System.out.println("Username or password cannot be null");
				}
			}
		});
		contentPane.add(btnRegister);
	}


}
