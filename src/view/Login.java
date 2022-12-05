package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import database.UserDatabase;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernamet;
	private JLabel lblPassword;
	private JPasswordField passwordt;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton login;
	private UserDatabase userDatabase = new UserDatabase();
	private JLabel lblNewLabel_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Login.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 449);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new LineBorder(Color.DARK_GRAY, 10, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		usernamet = new JTextField();
		usernamet.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernamet.setBounds(213, 109, 252, 20);
		contentPane.add(usernamet);
		usernamet.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(70, 105, 120, 20);
		contentPane.add(lblNewLabel);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(70, 161, 120, 25);
		contentPane.add(lblPassword);

		passwordt = new JPasswordField();
		passwordt.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordt.setBounds(213, 167, 252, 20);
		contentPane.add(passwordt);

		lblNewLabel_1 = new JLabel("Forget Password?");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new ForgetPassword().setVisible(true);
			}
		});
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(213, 208, 321, 36);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Algerian", Font.BOLD, 55));
		lblNewLabel_2.setBounds(204, 11, 261, 58);
		contentPane.add(lblNewLabel_2);

		login = new JButton("");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkFeild()) {
						if (userDatabase.checkUser(usernamet.getText(), passwordt.getText())) {
							dispose();
							new AddStudent().setVisible(true);
						} else
							JOptionPane.showMessageDialog(null, "ERROR USERNAME OR PASSWORD", "INFO",
									JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "EMPTY FIELD", "INFO", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		login.setIcon(new ImageIcon(Login.class.getResource("/pictures/login.jpg")));
		login.setBounds(183, 280, 180, 90);
		contentPane.add(login);

		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(
				new ImageIcon(Login.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		lblNewLabel_3.setBounds(0, 0, 534, 410);
		contentPane.add(lblNewLabel_3);
	}

	private boolean checkFeild() {
		if (usernamet.getText().isEmpty() || passwordt.getText().isEmpty())
			return false;
		return true;
	}
}
