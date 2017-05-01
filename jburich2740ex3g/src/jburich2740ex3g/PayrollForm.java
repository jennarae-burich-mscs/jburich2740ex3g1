package jburich2740ex3g;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JTextField hoursTextField;
	private JList employeeList;
	private JLabel totalHoursLable;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JTextField payRateTextField;
	private JTextField empNameTextField;
	private JTextField empIDTextField;
	private JButton addHoursButtons;
	private JButton clearHoursButton;
	private JButton updateButton;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
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
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("jburich ex3g");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 37, 371, 138);
		contentPane.add(scrollPane);
		
		
//		JList employeeList = new JList();
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "John Doe", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Jane Doe", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Roger Smith", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Sally Johnson", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Mike Cornwell", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(10, 11, 134, 27);
		contentPane.add(lblSelectEmployee);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(22, 204, 122, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setBounds(22, 240, 107, 14);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25 -100):");
		lblPayRate.setBounds(22, 277, 122, 14);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter hours (0.1 - 20):");
		lblEnterHours.setBounds(22, 310, 134, 14);
		contentPane.add(lblEnterHours);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(22, 348, 79, 14);
		contentPane.add(lblTotalHours);
		
		JLabel lblGrossPay = new JLabel("Gross pay:");
		lblGrossPay.setBounds(22, 386, 79, 14);
		contentPane.add(lblGrossPay);
		
		JButton clearFormButton = new JButton("Clear Form");
		clearFormButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_clearFormButton_mouseClicked(e);
			}
		});
		clearFormButton.setBounds(282, 413, 99, 33);
		contentPane.add(clearFormButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_clearHoursButton_mouseClicked(e);
			}
		});
		clearHoursButton.setBounds(314, 306, 89, 23);
		contentPane.add(clearHoursButton);
		
		addHoursButtons = new JButton("+");
		addHoursButtons.setEnabled(false);
		addHoursButtons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_addHoursButtons_mouseClicked(e);
			}
		});
		addHoursButtons.setBounds(249, 306, 55, 23);
		contentPane.add(addHoursButtons);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_hoursTextField_focusGained(e);
			}
		});
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setText("0.00");
		hoursTextField.setBounds(188, 307, 51, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		totalHoursLable = new JLabel("0.00");
		totalHoursLable.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLable.setBounds(153, 348, 86, 14);
		contentPane.add(totalHoursLable);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(153, 386, 86, 14);
		contentPane.add(grossPayLabel);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(149, 413, 123, 33);
		contentPane.add(updateButton);
		
		payRateTextField = new JTextField();
		payRateTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateTextField.setText("7.25");
		payRateTextField.setBounds(188, 274, 51, 20);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empNameTextField.setBounds(132, 237, 107, 20);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		empIDTextField = new JTextField();
		empIDTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empIDTextField.setText("000");
		empIDTextField.setBounds(188, 201, 51, 20);
		contentPane.add(empIDTextField);
		empIDTextField.setColumns(10);
	}

	protected void do_employeeList_mouseClicked(MouseEvent e) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.addHours(Double.parseDouble(this.hoursTextField.getText()));
		this.empIDTextField.setText(Integer.toString(payroll.getId()));
		this.empNameTextField.setText(payroll.getName());
		this.payRateTextField.setText(Double.toString(payroll.getPayRate()));
		DecimalFormat hoursFmt = new DecimalFormat("##0.00");
		this.hoursTextField.requestFocus();
		this.addHoursButtons.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
	}
	
	protected void do_addHoursButtons_mouseClicked(MouseEvent e) {
		Payroll pay = (Payroll) employeeList.getSelectedValue();
		double hours = Double.parseDouble(this.hoursTextField.getText());
		
       if (pay.addHours(hours)) {
        DecimalFormat hoursfmt = new DecimalFormat ("##0.00");
        this.totalHoursLable.setText(hoursfmt.format(pay.getHours()));
        DecimalFormat grosspayfmt = new DecimalFormat ("$#,###,###,###,###.00");
        this.grossPayLabel.setText(grosspayfmt.format(pay.calcGrossPay()));
        hoursTextField.setText("0.00");
       }
       else {
    	   JOptionPane.showMessageDialog(null, "Invalid Hours: \nMust be in range 0.1 - 20.");
       }
        this.hoursTextField.requestFocus();
	}
	
	protected void do_clearHoursButton_mouseClicked(MouseEvent e) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.setHours(0.0);
		this.totalHoursLable.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_clearFormButton_mouseClicked(MouseEvent e) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.setHours(0.0);
		this.empIDTextField.setText("0");
		this.empNameTextField.setText("");
		this.payRateTextField.setText("");
		this.totalHoursLable.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.grossPayLabel.setText("0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		this.addHoursButtons.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
	}
	
	protected void do_hoursTextField_focusGained(FocusEvent e) {
		hoursTextField.selectAll();
	}
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(empIDTextField.getText());
		double rate = Double.parseDouble(payRateTextField.getText());
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if (!payroll.setId(id)) 
		{
			JOptionPane.showMessageDialog(null, "Invalid Employee ID: \nMust be > 100");
			empIDTextField.setText("101");
			empIDTextField.requestFocus();
		}
		else if (!payroll.setPayRate(rate)) 
		{
			JOptionPane.showMessageDialog(null, "Invalid pay rate: \nMust be 7.25 - 100");
			DecimalFormat rateFmt = new DecimalFormat("##0.00");
			this.payRateTextField.setText(rateFmt.format(payroll.getPayRate()));
			this.payRateTextField.requestFocus();
		}
		else if (!payroll.setName(empNameTextField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Invalid name: \nMust enter a name.");
            this.empNameTextField.setText(payroll.getName());
            this.empNameTextField.requestFocus();
		}
		employeeList.repaint();
	}
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null);
		payrollObjMapper.writeAllPayroll(employeeListModel);
	}
}
