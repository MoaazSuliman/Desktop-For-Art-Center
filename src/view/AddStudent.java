package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.AttendanceDatabase;
import database.PayDatabase;
import database.StudentDatabase;
import model.Method;
import model.Student;
import java.awt.Component;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("ادراه الطلاب");
	private final JLabel lblNewLabel_1 = new JLabel("ID");
	private final JTextField idt = new JTextField();
	private final JLabel lblNewLabel_1_1 = new JLabel("الاسم ");
	private final JTextField fullNamet = new JTextField();
	private JTable table;
	private JTextField conditiont;
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblNewLabel_4 = new JLabel("المصاريف");

	private final JLabel lblNewLabel_1_3 = new JLabel("المستوي");
	private final JTextField levelt = new JTextField();
	private final JLabel lblNewLabel_1_4 = new JLabel("الجنس");
	private final JComboBox comboBox = new JComboBox();
	private final JLabel lblNewLabel_1_5 = new JLabel("العنوان");
	private final JTextField addresst = new JTextField();
	private final JLabel lblNewLabel_1_1_1 = new JLabel("الهاتف");
	private final JTextField phonet = new JTextField();
	private final JTextField emailt = new JTextField();
	private final JLabel lblNewLabel_1_2 = new JLabel("الايميل");
	private final JLabel lblNewLabel_1_3_1 = new JLabel("تاريخ الميلاد");
	private final JTextField birthDatet = new JTextField();
	private JScrollPane scrollPane;
	private final JButton back = new JButton("اعاده تحميل");
	private JComboBox avilable;
	private String stringAvilable[] = { "متاح", "غير متاح" };
	private JLabel totalHours = new JLabel("اجمالي عدد الساعات");
	private JLabel totalMoney = new JLabel("اجمالي المبلغ المدفوع");

	private Student student = new Student();
	private StudentDatabase studentDatabase = new StudentDatabase();
	private String[] choose = { "ذكر", "انثي" };
	private String header[] = { "Id", "الاسم", "الهاتف", "الايميل", "المستوي", "تاريخ", "عيد الميلاد", "الجنس",
			"العنوان", "موجود" };
	private String[][] body;
	private ArrayList<Student> students = new ArrayList<>();
	private final JLabel lblNewLabel_2 = new JLabel("مركز بصمة فن");
	private JLabel numberOfStudents = new JLabel();
	private final JLabel lblNewLabel_5 = new JLabel("New label");

	private AttendanceDatabase attendanceDatbase = new AttendanceDatabase();
	private PayDatabase payDatabase = new PayDatabase();
	private final JLabel lblNewLabel_6 = new JLabel(new Method().getDate());

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
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
	public AddStudent() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AddStudent.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1350, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1334, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(589, 2, 250, 44);

		panel.add(lblNewLabel);
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Arabic Typesetting", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel_2.setBounds(37, 2, 273, 44);

		panel.add(lblNewLabel_2);
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_6.setBounds(849, 2, 422, 44);

		panel.add(lblNewLabel_6);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 49, 146, 632);
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Payment().setVisible(true);
			}
		});
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(10, 80, 121, 49);

		panel_1.add(panel_2);
		panel_2.setLayout(null);
		lblNewLabel_4.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(15, 11, 101, 27);

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
		panel_2_1.setBounds(10, 179, 121, 49);
		panel_1.add(panel_2_1);

		JLabel lblNewLabel_4_1 = new JLabel("الحضور");
		lblNewLabel_4_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setBounds(25, 11, 86, 27);
		panel_2_1.add(lblNewLabel_4_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(156, 137, 95, 21);

		contentPane.add(lblNewLabel_1);
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setColumns(10);
		idt.setBounds(261, 137, 182, 20);

		contentPane.add(idt);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(156, 169, 95, 21);

		contentPane.add(lblNewLabel_1_1);
		fullNamet.setFont(new Font("Tahoma", Font.BOLD, 14));
		fullNamet.setColumns(10);
		fullNamet.setBounds(261, 169, 182, 20);

		contentPane.add(fullNamet);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 86, 854, 584);
		contentPane.add(scrollPane);
		try {
			setTable();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		scrollPane.setViewportView(table);

		JButton add = new JButton("اضافه");
		add.setForeground(Color.BLACK);
		add.setBackground(new Color(51, 255, 102));
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (check()) {
						intizalize();
						studentDatabase.insertStudent(student);
						JOptionPane.showMessageDialog(null, "تم اضافه الطالب بنجاح", "DONE",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new AddStudent().setVisible(true);

					} else
						JOptionPane.showMessageDialog(null, "الحقل فارغ", "TAKE CARE", JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					System.out.println(student.toString());
					JOptionPane.showMessageDialog(null, ex.getMessage(), "TAKE CARE", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add.setBounds(271, 492, 89, 34);
		contentPane.add(add);

		JButton search = new JButton("بحث");
		search.setIcon(new ImageIcon(AddStudent.class.getResource("/pictures/search.png")));
		search.setForeground(Color.LIGHT_GRAY);
		search.setBackground(Color.DARK_GRAY);
		search.setFont(new Font("Tahoma", Font.BOLD, 14));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (checkCondition()) {
						body = new String[0][0];
						table = new JTable(body, header);

						setTable2();
						scrollPane.setViewportView(table);
					} else
						JOptionPane.showMessageDialog(null, "ادخل الاسم للبحث", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		search.setBounds(1067, 61, 130, 21);
		contentPane.add(search);

		JLabel lblNewLabel_3 = new JLabel("الاسم");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(781, 61, 90, 24);
		contentPane.add(lblNewLabel_3);

		conditiont = new JTextField();
		conditiont.setFont(new Font("Tahoma", Font.BOLD, 13));
		conditiont.setColumns(10);
		conditiont.setBounds(875, 61, 182, 21);
		contentPane.add(conditiont);

		JButton update = new JButton("تعديل");
		update.setForeground(Color.BLACK);
		update.setBackground(new Color(0, 51, 255));
		update.setFont(new Font("Tahoma", Font.BOLD, 18));
		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (check() && checkId()) {
						intizalize();
						student.setId(Integer.parseInt(idt.getText()));
						studentDatabase.updateStudent(student);
						JOptionPane.showMessageDialog(null, "تم التعديل بنجاح", "DONE",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new AddStudent().setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "الحقل فارغ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		update.setBounds(338, 542, 89, 34);
		contentPane.add(update);

		JButton delete = new JButton("حذف");
		delete.setForeground(Color.BLACK);
		delete.setBackground(Color.RED);
		delete.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkId()) {
						studentDatabase.deleteStudent(Integer.parseInt(idt.getText()));
						JOptionPane.showMessageDialog(null, "تم حذف الطالب", "DONE", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new AddStudent().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "ادخل ال id او اختار الطالب ", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		delete.setBounds(178, 542, 89, 34);
		contentPane.add(delete);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(156, 277, 95, 21);

		contentPane.add(lblNewLabel_1_3);
		levelt.setFont(new Font("Tahoma", Font.BOLD, 14));
		levelt.setColumns(10);
		levelt.setBounds(261, 277, 182, 20);

		contentPane.add(levelt);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(156, 343, 95, 21);

		contentPane.add(lblNewLabel_1_4);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(choose));
		comboBox.setBounds(261, 342, 182, 22);

		contentPane.add(comboBox);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(156, 386, 95, 21);

		contentPane.add(lblNewLabel_1_5);
		addresst.setFont(new Font("Tahoma", Font.BOLD, 14));
		addresst.setColumns(10);
		addresst.setBounds(261, 384, 182, 50);

		contentPane.add(addresst);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(156, 201, 95, 21);

		contentPane.add(lblNewLabel_1_1_1);
		phonet.setFont(new Font("Tahoma", Font.BOLD, 14));
		phonet.setColumns(10);
		phonet.setBounds(261, 201, 182, 20);

		contentPane.add(phonet);
		emailt.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailt.setColumns(10);
		emailt.setBounds(261, 241, 182, 25);

		contentPane.add(emailt);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(156, 246, 95, 21);

		contentPane.add(lblNewLabel_1_2);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(156, 311, 95, 21);

		contentPane.add(lblNewLabel_1_3_1);
		birthDatet.setFont(new Font("Tahoma", Font.BOLD, 14));
		birthDatet.setColumns(10);
		birthDatet.setBounds(261, 311, 182, 20);

		contentPane.add(birthDatet);
		back.setForeground(Color.LIGHT_GRAY);
		back.setBackground(Color.DARK_GRAY);
		back.setFont(new Font("Tahoma", Font.BOLD, 14));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddStudent().setVisible(true);
			}
		});
		back.setBounds(1207, 61, 117, 21);

		contentPane.add(back);

		avilable = new JComboBox();
		avilable.setModel(new DefaultComboBoxModel(stringAvilable));
		avilable.setFont(new Font("Tahoma", Font.BOLD, 14));
		avilable.setBounds(261, 459, 182, 25);
		contentPane.add(avilable);

		JLabel lblNewLabel_1_4_1 = new JLabel("هل متاح؟!");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4_1.setBounds(156, 460, 95, 21);
		contentPane.add(lblNewLabel_1_4_1);
		numberOfStudents.setFont(new Font("Tahoma", Font.BOLD, 14));
		numberOfStudents.setBounds(254, 74, 208, 21);

		contentPane.add(numberOfStudents);

		totalHours.setForeground(Color.RED);
		totalHours.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		totalHours.setBounds(178, 587, 282, 36);

		contentPane.add(totalHours);
		totalMoney.setForeground(Color.RED);
		totalMoney.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		totalMoney.setBounds(178, 634, 282, 36);

		contentPane.add(totalMoney);

		lblNewLabel_5.setIcon(
				new ImageIcon(AddStudent.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.48 AM.jpeg")));
		lblNewLabel_5.setBounds(149, 49, 1195, 632);

		contentPane.add(lblNewLabel_5);

	}

	private boolean check() {
		if (fullNamet.getText().isEmpty() || emailt.getText().isEmpty() || phonet.getText().isEmpty()
				|| levelt.getText().isEmpty() || birthDatet.getText().isEmpty() || addresst.getText().isEmpty())
			return false;
		return true;
	}

	private void intizalize() throws Exception {
		student.setFullName(fullNamet.getText());
		student.setPhone(phonet.getText());
		student.setEmail(emailt.getText());
		student.setLevel(levelt.getText());
		student.setDate(new Method().getDate());
		student.setDateOfBirth(birthDatet.getText());
		int index = comboBox.getSelectedIndex();
		student.setGender(choose[index]);
		student.setAddress(addresst.getText());
		index = avilable.getSelectedIndex();
		student.setAvilable(stringAvilable[index]);

	}

	private void setTable() throws SQLException {
		students = studentDatabase.getAllStudents();
		setDefaultTable();
	}

	private void setTable2() throws SQLException {
		students = studentDatabase.getAllStudents(conditiont.getText());
		setDefaultTable();
	}

	private boolean checkId() {
		if (idt.getText().isEmpty())
			return false;
		return true;
	}

	private void setGender(int index) {
		if (students.get(index).getGender().equals("ذكر"))
			comboBox.setSelectedIndex(0);
		else
			comboBox.setSelectedIndex(1);
	}

	private void setAvilable(int index) {
		if (students.get(index).getAvilable().equals("متاح"))
			avilable.setSelectedIndex(0);
		else
			avilable.setSelectedIndex(1);

	}

	private boolean checkCondition() {
		if (conditiont.getText().isEmpty())
			return false;
		return true;
	}

	private void setDefaultTable() {
		int len = students.size();
		body = new String[len][10];
		for (int i = 0; i < len; i++) {
			body[i][0] = String.valueOf(students.get(i).getId());
			body[i][1] = students.get(i).getFullName();
			body[i][2] = students.get(i).getPhone();
			body[i][3] = students.get(i).getEmail();
			body[i][4] = students.get(i).getLevel();
			body[i][5] = students.get(i).getDate();
			body[i][6] = students.get(i).getDateOfBirth();
			body[i][7] = students.get(i).getGender();
			body[i][8] = students.get(i).getAddress();
			body[i][9] = students.get(i).getAvilable();
		}
		table = new JTable(body, header);
		table.setRowHeight(25);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.LIGHT_GRAY);
		table.getTableHeader().setBackground(Color.darkGray);
		table.getTableHeader().setForeground(Color.lightGray);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				idt.setText(String.valueOf(students.get(index).getId()));
				fullNamet.setText(students.get(index).getFullName());
				phonet.setText(students.get(index).getPhone());
				emailt.setText(students.get(index).getEmail());
				levelt.setText(students.get(index).getLevel());
				birthDatet.setText(students.get(index).getDateOfBirth());
				setGender(index);
				addresst.setText(students.get(index).getAddress());
				setAvilable(index);
				try {
					totalHours.setText("اجمالي  عدد الساعات "
							+ attendanceDatbase.getTotalHoursForStudent(students.get(index).getId()));
					totalMoney.setText("اجمالي المبلغ المدفوع  "
							+ payDatabase.getTotalMoneyForStudent(students.get(index).getId()));

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		numberOfStudents.setText("عدد االطلاب " + students.size());
	}
}
