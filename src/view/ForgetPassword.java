package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

public class ForgetPassword extends JFrame {

	private JPanel contentPane;
	private UserDatabase userDatabase = new UserDatabase();
	private JTextField usernamet;
	private JTextField questiont;
	private JTextField answert;
	private JPasswordField newPasswordt;
	private JPasswordField confirmNewPasswordt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassword frame = new ForgetPassword();
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
	public ForgetPassword() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ForgetPassword.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		setTitle("Forget Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 462);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.DARK_GRAY, 10));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 153, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (check()) {
						if (ifAnswerIsTrueOrNot()) {
							if (comparePasswords()) {
								userDatabase.updatePassword(usernamet.getText(), newPasswordt.getText());
								dispose();
								JOptionPane.showMessageDialog(null, "تم تغيير الباسورد بنجاح", "DONE",
										JOptionPane.INFORMATION_MESSAGE);
								new Login().setVisible(true);
							} else
								JOptionPane.showMessageDialog(null, "PASSWORDS ARE NOT SAME", "INFO",
										JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "WRONG ANSWER", "INFO",
									JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "EMPTY FIELD", "INFO", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(225, 311, 142, 37);
		contentPane.add(btnNewButton);

		JLabel enter = new JLabel("Enter UserName");
		enter.setFont(new Font("Tahoma", Font.BOLD, 12));
		enter.setForeground(Color.RED);
		enter.setBounds(448, 57, 108, 14);
		enter.setVisible(false);
		contentPane.add(enter);

		JButton view = new JButton("View Que");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (beforeGetQuestion()) {
						questiont.setText(userDatabase.getQuestion(usernamet.getText()));
					} else {
						enter.setVisible(true);
					}

				} catch (Exception ex) {
				}
			}

		});
		view.setFont(new Font("Tahoma", Font.BOLD, 14));
		view.setBounds(448, 98, 108, 22);
		contentPane.add(view);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(36, 57, 156, 20);
		contentPane.add(lblUsername);

		usernamet = new JTextField();
		usernamet.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernamet.setColumns(10);
		usernamet.setBounds(202, 57, 236, 20);
		contentPane.add(usernamet);

		JLabel lblNewLabel = new JLabel("Question");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(36, 98, 156, 20);
		contentPane.add(lblNewLabel);

		questiont = new JTextField();
		questiont.setFont(new Font("Tahoma", Font.BOLD, 14));
		questiont.setColumns(10);
		questiont.setBounds(202, 98, 236, 20);
		contentPane.add(questiont);

		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnswer.setBounds(36, 147, 156, 20);
		contentPane.add(lblAnswer);

		answert = new JTextField();
		answert.setFont(new Font("Tahoma", Font.BOLD, 14));
		answert.setColumns(10);
		answert.setBounds(202, 147, 236, 20);
		contentPane.add(answert);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassword.setBounds(36, 214, 156, 20);
		contentPane.add(lblNewPassword);

		newPasswordt = new JPasswordField();
		newPasswordt.setFont(new Font("Tahoma", Font.BOLD, 14));
		newPasswordt.setBounds(202, 214, 236, 20);
		contentPane.add(newPasswordt);

		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfirmNewPassword.setBounds(36, 257, 156, 20);
		contentPane.add(lblConfirmNewPassword);

		confirmNewPasswordt = new JPasswordField();
		confirmNewPasswordt.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirmNewPasswordt.setBounds(202, 257, 236, 20);
		contentPane.add(confirmNewPasswordt);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(255, 153, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(225, 375, 142, 37);
		contentPane.add(btnNewButton_1);
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(
				ForgetPassword.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		lblNewLabel_2.setBounds(0, 0, 566, 423);
		contentPane.add(lblNewLabel_2);

	}

	private boolean check() {
		if (answert.getText().isEmpty() || newPasswordt.getText().isEmpty() || confirmNewPasswordt.getText().isEmpty())
			return false;
		return true;
	}

	private boolean ifAnswerIsTrueOrNot() throws SQLException {
		if (answert.getText().equals(userDatabase.getAnswer(usernamet.getText())))
			return true;
		return false;
	}

	private boolean comparePasswords() {
		if (newPasswordt.getText().equals(confirmNewPasswordt.getText()))
			return true;
		return false;
	}

	private boolean beforeGetQuestion() {
		if (usernamet.getText().isEmpty())
			return false;
		return true;
	}
}
