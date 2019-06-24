package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.ui.content.PanelEmployee;

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnClear;
	private PanelEmployee pContent;

	private EmployeeDao dao;
	
	private ErpManagementUI erpManagementUI;
	private List<Department> lists;
	private List<Employee> listsEmp;
	
	public EmployeeUI() {
		initComponents();
		
	}
	
	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}
	
	public void setList(List<Department> lists) {
		this.lists = lists;
		pContent.setCmbDno(lists);
	}
	

	public void setListsEmp(List<Employee> listsEmp) {
		this.listsEmp = listsEmp;
		pContent.setCmbManager(listsEmp);
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\uC0AC\uC6D0\uAD00\uB9AC");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pContent = new PanelEmployee();
		contentPane.add(pContent, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnAdd) {
			if (btnAdd.getText().equals("추가")) {
				actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Employee newEmp = pContent.getEmployee();
		int res;
		try {
			res = dao.updateEmployee(newEmp);
			if (res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s 사원 정보가 수정되었습니다.", newEmp.getEmpName()));
				pContent.clearTextField();
				btnAdd.setText("추가");
			}
			this.dispose();
			erpManagementUI.refreshUI();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee newEmp = pContent.getEmployee();
		System.out.println(newEmp);
		int res;
		try {
			res = dao.insertEmployee(newEmp);
			if (res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s 사원이 추가되었습니다.", newEmp.getEmpName()));
				pContent.clearTextField();
			}
			erpManagementUI.refreshUI();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTextField();
	}
	
	public void setEmployee(Employee emp) {
		pContent.setEmployee(emp);
		pContent.getTfEmpNo().setEditable(false);
		btnAdd.setText("수정");
	}

	public void setParent(ErpManagementUI erpManagementUI) {
		this.erpManagementUI = erpManagementUI;
	}
}
