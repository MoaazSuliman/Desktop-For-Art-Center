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

import database.AttendanceDatabase;
import database.StudentDatabase;
import model.Attendance;
import model.Method;
import model.Student;

public class AttendanceView extends JFrame {

	private JPanel contentPane;
	private JTextField idt;
	private JTable table;
	private JTable table2;
	private JTextField conditiont;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	private StudentDatabase studentDatabase = new StudentDatabase();
	private AttendanceDatabase attendanceDatabase = new AttendanceDatabase();
	private ArrayList<Attendance> atts = new ArrayList<>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private String header1[] = { "ID", "الاسم", "الايميل", " عدد الساعات" };
	private String body1[][];
	private int row = -1;
	private String header2[] = { "الاسم", "الايميل", "التاريخ", "عدد الساعات" };
	private String body2[][];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceView frame = new AttendanceView();
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
	public AttendanceView() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceView.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.49 AM.jpeg")));
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1350, 720);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1334, 50);
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("اداره الحضور");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(577, 0, 248, 44);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("مركز بصمة فن");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Arabic Typesetting", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel_2.setBounds(35, 0, 273, 44);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_6 = new JLabel(new Method().getDate());
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_6.setBounds(876, 9, 422, 44);
		panel.add(lblNewLabel_6);

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
				new Payment().setVisible(true);
			}
		});
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(10, 178, 121, 49);
		panel_1.add(panel_2_1);

		JLabel lblNewLabel_4_1 = new JLabel("المصاريف");
		lblNewLabel_4_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setBounds(25, 11, 86, 27);
		panel_2_1.add(lblNewLabel_4_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 101, 480, 569);
		getContentPane().add(scrollPane);
		try {
			setTable1();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
		}
		scrollPane.setViewportView(table);

		idt = new JTextField();
		idt.setFont(new Font("Tahoma", Font.BOLD, 14));
		idt.setBounds(176, 173, 107, 20);
		getContentPane().add(idt);
		idt.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(176, 141, 86, 21);
		getContentPane().add(lblNewLabel_1_1_1);

		JButton attend = new JButton("حضر");
		attend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkId() && row != -1) {
						attendanceDatabase.addHoursForStudent(students.get(row).getId());
						JOptionPane.showMessageDialog(null, "تمت المهمه بنجاح", "DONE",
								JOptionPane.INFORMATION_MESSAGE);
						setTable1();
						setTable2();
					} else
						JOptionPane.showMessageDialog(null, "قم ب اختيار الطالب اولا", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		attend.setForeground(Color.LIGHT_GRAY);
		attend.setFont(new Font("Tahoma", Font.BOLD, 14));
		attend.setBackground(Color.DARK_GRAY);
		attend.setBounds(176, 215, 107, 34);
		getContentPane().add(attend);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(840, 173, 480, 497);
		getContentPane().add(scrollPane_1);

		table2 = new JTable();
		table2.setRowHeight(25);
		table2.setBackground(Color.DARK_GRAY);
		table2.setForeground(Color.LIGHT_GRAY);
		table2.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_1.setViewportView(table2);

		JLabel lblNewLabel_1 = new JLabel("تفاصيل حضور الطالب");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1.setBounds(890, 101, 382, 50);
		getContentPane().add(lblNewLabel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(815, 49, 10, 632);
		getContentPane().add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("الاسم");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(358, 61, 77, 24);
		getContentPane().add(lblNewLabel_3);

		conditiont = new JTextField();
		conditiont.setFont(new Font("Tahoma", Font.BOLD, 14));
		conditiont.setColumns(10);
		conditiont.setBounds(436, 61, 182, 21);
		getContentPane().add(conditiont);

		JButton search = new JButton("بحث");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkCondition()) {
						body1 = new String[0][0];
						table = new JTable(body1, header1);

						setTable1AfterSearch();
						scrollPane.setViewportView(table);
					} else
						JOptionPane.showMessageDialog(null, "ENTER FULLNAME TO SEARCH", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		search.setIcon(new ImageIcon(AttendanceView.class.getResource("/pictures/search.png")));
		search.setForeground(Color.LIGHT_GRAY);
		search.setFont(new Font("Tahoma", Font.BOLD, 14));
		search.setBackground(Color.DARK_GRAY);
		search.setBounds(628, 61, 130, 21);
		getContentPane().add(search);

		JButton refresh = new JButton("اعاده تحميل");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AttendanceView().setVisible(true);
			}
		});
		refresh.setForeground(Color.LIGHT_GRAY);
		refresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		refresh.setBackground(Color.DARK_GRAY);
		refresh.setBounds(1217, 49, 117, 46);
		getContentPane().add(refresh);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(
				AttendanceView.class.getResource("/pictures/WhatsApp Image 2022-09-14 at 8.30.48 AM.jpeg")));
		lblNewLabel_5.setBounds(144, 50, 1190, 631);
		getContentPane().add(lblNewLabel_5);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	private void setTable1() throws SQLException {
		students = studentDatabase.getAllStudentsThatAreAvialable();
		setDefaultTable1();
	}

	private void setTable1AfterSearch() throws SQLException {
		students = studentDatabase.getAllStudentsThereAreAvilableWithCondition(conditiont.getText());
		setDefaultTable1();
	}

	private void setDefaultTable1() throws SQLException {
		int len = students.size();
		body1 = new String[len][4];
		for (int i = 0; i < len; i++) {
			body1[i][0] = String.valueOf(students.get(i).getId());
			body1[i][1] = students.get(i).getFullName();
			body1[i][2] = students.get(i).getEmail();
			body1[i][3] = String.valueOf(attendanceDatabase.getTotalHoursForStudent(students.get(i).getId()));

		}
		table = new JTable(body1, header1);
		table.setRowHeight(25);
		table.setForeground(Color.LIGHT_GRAY);
		table.setBackground(Color.DARK_GRAY);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.LIGHT_GRAY);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				setId();
				try {
					setTable2();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
	}

	private void setId() {
		idt.setText(String.valueOf(students.get(row).getId()));
	}

	private void setTable2() {
		try {
			atts = attendanceDatabase.getAllAttendanceForStudent(students.get(row).getId());
			int len = atts.size();
			body2 = new String[len][4];
			for (int i = 0; i < len; i++) {
				body2[i][0] = students.get(row).getFullName();
				body2[i][1] = students.get(row).getEmail();
				body2[i][2] = atts.get(i).getDate();
				body2[i][3] = String.valueOf(atts.get(i).getHours());

			}
			table2 = new JTable(body2, header2);
			table2.setBackground(Color.DARK_GRAY);
			table2.setForeground(Color.LIGHT_GRAY);
			table2.getTableHeader().setBackground(Color.DARK_GRAY);
			table2.getTableHeader().setForeground(Color.LIGHT_GRAY);
			scrollPane_1.setViewportView(table2);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private boolean checkId() {
		if (idt.getText().isEmpty())
			return false;
		return true;
	}

	private boolean checkCondition() {
		if (conditiont.getText().isEmpty())
			return false;
		return true;
	}
}
