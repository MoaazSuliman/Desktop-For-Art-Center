package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PushbackInputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.PayDatabase;
import database.StudentDatabase;
import model.Method;
import model.Pay;
import model.Student;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Payment extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField moneyt;
	private JTextField fullNamet;
	private JTable table2;
	private JTextField idt;
	private JLabel totalMoneyForStudent;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private JButton push;

	private String headerForStudentTable[] = { "Id", "الاسم", "الايميل", "االهاتف", "اجمالي المبلغ " };
	private String bodyForStudentTable[][];
	private String headerForPaymentTable[] = { "اسم الطالب", "الايميل", "التاريخ", "المبلغ" };
	private String bodyForPaymentTable[][];
	private ArrayList<Student> students;
	private StudentDatabase studentDatabase = new StudentDatabase();
	private PayDatabase payDatabase = new PayDatabase();
	private int student_id;
	private int money;
	private int row = -1;
	private ArrayList<Pay> pays = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
	public Payment() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Payment.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1350, 720);
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 49, 146, 632);
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AddStudent().setVisible(true);
			}
		});
		panel_2.setLayout(null);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(10, 80, 121, 49);
		panel_1.add(panel_2);

		JLabel lblNewLabel_4 = new JLabel("الطلاب");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_4.setBounds(25, 11, 96, 27);
		panel_2.add(lblNewLabel_4);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AttendanceView().setVisible(true);
			}
		});
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(10, 178, 121, 49);
		panel_1.add(panel_2_1);

		JLabel lblNewLabel_4_1 = new JLabel("الحضور");
		lblNewLabel_4_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setBounds(25, 11, 86, 27);
		panel_2_1.add(lblNewLabel_4_1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1334, 50);
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("ادراه المصاريف");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(600, 0, 262, 44);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("مركز بصمة فن");
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Arabic Typesetting", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel_2.setBounds(35, 0, 273, 44);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_6 = new JLabel(new Method().getDate());
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_6.setBounds(872, 3, 422, 44);
		panel.add(lblNewLabel_6);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(298, 107, 527, 563);
		getContentPane().add(scrollPane);

		try {
			setTable1();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
		}
		setTable1HeaderColors();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1_1 = new JLabel("المبلغ المدفوع");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(172, 171, 116, 21);
		getContentPane().add(lblNewLabel_1_1);

		moneyt = new JTextField();
		moneyt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					push.doClick();
				}
			}
		});
		moneyt.setFont(new Font("Tahoma", Font.BOLD, 14));
		moneyt.setColumns(10);
		moneyt.setBounds(156, 203, 132, 20);
		getContentPane().add(moneyt);

		push = new JButton("دفع");
		push.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkMoney()) {
						if (row != -1) {
							money = Integer.parseInt(moneyt.getText());
							payDatabase.payForStudent(student_id, money);
							JOptionPane.showMessageDialog(null, "تم اضافه المبلغ بنجاح", "DONE",
									JOptionPane.WARNING_MESSAGE);
							moneyt.setText("");
							setTotalMoneyForStudent();
							setTable2();
							setTable1();
						} else
							JOptionPane.showMessageDialog(null, "قم باختيار الطالب", "ERROR",
									JOptionPane.INFORMATION_MESSAGE);

					} else
						JOptionPane.showMessageDialog(null, "لم يتم اضافه اي مبلغ", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		push.setForeground(Color.LIGHT_GRAY);
		push.setFont(new Font("Tahoma", Font.BOLD, 14));
		push.setBackground(Color.DARK_GRAY);
		push.setBounds(156, 246, 132, 34);
		getContentPane().add(push);

		JLabel lblNewLabel_3 = new JLabel("الاسم");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(337, 72, 90, 24);
		getContentPane().add(lblNewLabel_3);

		fullNamet = new JTextField();
		fullNamet.setFont(new Font("Tahoma", Font.BOLD, 14));
		fullNamet.setColumns(10);
		fullNamet.setBounds(431, 72, 182, 21);
		getContentPane().add(fullNamet);

		JButton search = new JButton("بحث");
		search.setIcon(new ImageIcon(Payment.class.getResource("/pictures/search.png")));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkFullName()) {
						setTable1AfterSearch();
						scrollPane.setViewportView(table);
					} else
						JOptionPane.showMessageDialog(null, "قم بادخال الاسم للبحث");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		search.setForeground(Color.LIGHT_GRAY);
		search.setFont(new Font("Tahoma", Font.BOLD, 14));
		search.setBackground(Color.DARK_GRAY);
		search.setBounds(623, 72, 130, 21);
		getContentPane().add(search);

		JButton back = new JButton("اعاده تحميل");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Payment().setVisible(true);
			}
		});
		back.setForeground(Color.LIGHT_GRAY);
		back.setFont(new Font("Tahoma", Font.BOLD, 14));
		back.setBackground(Color.DARK_GRAY);
		back.setBounds(1207, 49, 117, 46);
		getContentPane().add(back);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(849, 180, 475, 402);
		getContentPane().add(scrollPane_1);

		table2 = new JTable();
		table2.setRowHeight(25);
		table2.setForeground(Color.LIGHT_GRAY);
		table2.setBackground(Color.DARK_GRAY);
		table2.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_1.setViewportView(table2);

		JLabel lblNewLabel_1 = new JLabel("تفاصيل مصاريف الطالب");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1.setBounds(908, 107, 426, 53);
		getContentPane().add(lblNewLabel_1);

		totalMoneyForStudent = new JLabel("المبلغ الكلي للطالب");
		totalMoneyForStudent.setForeground(Color.BLACK);
		totalMoneyForStudent.setFont(new Font("Arabic Typesetting", Font.BOLD | Font.ITALIC, 40));
		totalMoneyForStudent.setBounds(908, 593, 383, 64);
		getContentPane().add(totalMoneyForStudent);

		idt = new JTextField();
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setBounds(156, 140, 132, 20);
		getContentPane().add(idt);
		idt.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(172, 107, 116, 21);
		getContentPane().add(lblNewLabel_1_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(829, 49, 10, 632);
		getContentPane().add(panel_3);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(
				new ImageIcon(Payment.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.48 AM.jpeg")));
		lblNewLabel_5.setBounds(144, 49, 1190, 632);
		getContentPane().add(lblNewLabel_5);
	}

	private void setTable1() throws SQLException {
		students = studentDatabase.getAllStudentsThatAreAvialable();
		setDefaultTable1();
	}

	private boolean checkMoney() {
		if (moneyt.getText().isEmpty())
			return false;
		return true;
	}

	private void setTotalMoneyForStudent() throws SQLException {
		totalMoneyForStudent.setText("المبلغ الكلي للطالب " + payDatabase.getTotalMoneyForStudent(student_id));
	}

	private void setTable2() throws SQLException {
		pays = payDatabase.getAllInformationForStudentPayment(student_id);
		int len = pays.size();
		bodyForPaymentTable = new String[len][4];
		for (int i = 0; i < len; i++) {
			bodyForPaymentTable[i][0] = students.get(row).getFullName();
			bodyForPaymentTable[i][1] = students.get(row).getEmail();
			bodyForPaymentTable[i][2] = pays.get(i).getDate();
			bodyForPaymentTable[i][3] = String.valueOf(pays.get(i).getMoney());

		}
		table2 = new JTable(bodyForPaymentTable, headerForPaymentTable);
		table2.setForeground(Color.LIGHT_GRAY);
		table2.setBackground(Color.DARK_GRAY);
		table2.getTableHeader().setBackground(Color.DARK_GRAY);
		table2.getTableHeader().setForeground(Color.lightGray);
		setTable2HeaderColors();
		table2.setFont(new Font("Tahoma", Font.BOLD, 10));
		scrollPane_1.setViewportView(table2);
	}

	private boolean checkFullName() {
		if (fullNamet.getText().isEmpty())
			return false;
		return true;
	}

	private void setTable1AfterSearch() throws SQLException {
		students = studentDatabase.getAllStudentsThereAreAvilableWithCondition(fullNamet.getText());

		setDefaultTable1();
	}

	private void setDefaultTable1() throws SQLException {
		int len = students.size();
		new Method().resizeForMoney(students);
		bodyForStudentTable = new String[len][5];
		for (int i = 0; i < len; i++) {
			bodyForStudentTable[i][0] = String.valueOf(students.get(i).getId());
			bodyForStudentTable[i][1] = students.get(i).getFullName();
			bodyForStudentTable[i][2] = students.get(i).getEmail();
			bodyForStudentTable[i][3] = students.get(i).getPhone();
			bodyForStudentTable[i][4] = String.valueOf(payDatabase.getTotalMoneyForStudent(students.get(i).getId()));
		}
		table = new JTable(bodyForStudentTable, headerForStudentTable);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.LIGHT_GRAY);
		setTable1HeaderColors();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				student_id = students.get(row).getId();
				idt.setText(String.valueOf(students.get(row).getId()));

				try {
					setTable2();
					setTotalMoneyForStudent();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR from moaaz", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
	}

	private void setTable1HeaderColors() {
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.lightGray);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

	}

	private void setTable2HeaderColors() {
		table2.getTableHeader().setBackground(Color.DARK_GRAY);
		table2.getTableHeader().setForeground(Color.lightGray);
	}
}
