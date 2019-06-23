package jdbc_study.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

import javax.swing.UIManager;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelEmployee extends JPanel {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfTitle;
	private JTextField tfManager;
	private JTextField tfSalary;
	private JTextField tfDno;

	public PanelEmployee() {

		initComponents();
	}
	private void initComponents() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC0AC\uC6D0\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblEmpNo = new JLabel("\uC0AC\uC6D0 \uBC88\uD638");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("\uC0AC\uC6D0\uBA85");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpName);
		
		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		add(tfEmpName);
		
		JLabel lblTilte = new JLabel("\uC9C1\uCC45");
		lblTilte.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTilte);
		
		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		add(tfTitle);
		
		JLabel lblManager = new JLabel("\uC9C1\uC18D\uC0C1\uC0AC");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblManager);
		
		tfManager = new JTextField();
		tfManager.setColumns(10);
		add(tfManager);
		
		JLabel lblSalary = new JLabel("\uC6D4\uAE09");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSalary);
		
		tfSalary = new JTextField();
		tfSalary.setColumns(10);
		add(tfSalary);
		
		JLabel lblDno = new JLabel("\uBD80\uC11C\uBC88\uD638");
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDno);
		
		tfDno = new JTextField();
		tfDno.setColumns(10);
		add(tfDno);
	}
	
	public void setEmployee(Employee emp) {
		tfEmpNo.setText(emp.getEmpNo()+"");
		tfEmpName.setText(emp.getEmpName());
		tfTitle.setText(emp.getTitle());
		tfManager.setText(emp.getMananger().getEmpNo()+"");
		tfSalary.setText(emp.getSalary()+"");
		tfDno.setText(emp.getDno().getDeptNo()+"");
		
		
	}
	
	public Employee getEmployee() {
		int empNo = Integer.parseInt(tfEmpNo.getText().trim());
		String empName = tfEmpName.getText().trim();
		String title = tfTitle.getText().trim();
		int manager = Integer.parseInt(tfManager.getText().trim());
		int salary = Integer.parseInt(tfSalary.getText().trim());
		int dno = Integer.parseInt(tfDno.getText().trim());
		return new Employee(empNo, empName, title, new Employee(manager), salary, new Department(dno));
	}
	
	public void clearTextField() {
		tfEmpNo.setText("");
		tfEmpName.setText("");
		tfTitle.setText("");
		tfManager.setText("");
		tfSalary.setText("");
		tfDno.setText("");
	}
	
	public JTextField getTfEmpNo() {
		return tfEmpNo;
	}
	
	public void setTfAllEditable(boolean isEditable) {
		tfEmpNo.setEditable(isEditable);
		tfEmpName.setEditable(isEditable);
		tfTitle.setEditable(isEditable);
		tfManager.setEditable(isEditable);
		tfSalary.setEditable(isEditable);
		tfDno.setEditable(isEditable);
	}

}







