package jdbc_study.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.ui.content.PanelDepartment;
import jdbc_study.ui.content.PanelEmployee;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnList;

	private DepartmentDao dao;
	private EmployeeDao dao2;

	private DepartmentUI frameDepartment;
	private DepartmentListUI frameDepartmentList;
	
	private EmployeeUI frameEmployee;
	private EmployeeListUI frameEmployeeList;
	
	private JPanel pDEpt;
	private JPanel pEmp;
	private JButton btnAdd2;
	private JButton btnUpdate2;
	private JButton btnDelete2;
	private JButton btnSearch2;
	private JButton btnList2;

	public ErpManagementUI() {
		dao = new DepartmentDaoImpl();
		dao2 = new EmployeeDaoImpl();
		initComponents();
	}

	private void initComponents() {
		setTitle("\uC0AC\uC6D0 \uBD80\uC11C \uAD00\uB9AC \uBA54\uB274");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 10, 10));
		//�μ�
		pDEpt = new JPanel();
		contentPane.add(pDEpt);
		pDEpt.setLayout(new GridLayout(1, 1, 10, 0));

		btnAdd = new JButton("�μ� �߰�");
		pDEpt.add(btnAdd);

		btnUpdate = new JButton("�μ� ����");
		pDEpt.add(btnUpdate);

		btnDelete = new JButton("�μ� ����");
		pDEpt.add(btnDelete);

		btnSearch = new JButton("�μ� �˻�");
		pDEpt.add(btnSearch);

		btnList = new JButton("�μ� ���");
		pDEpt.add(btnList);
		btnList.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnAdd.addActionListener(this);
		
		//���
		pEmp = new JPanel();
		contentPane.add(pEmp);
		pEmp.setLayout(new GridLayout(1, 0, 10, 0));
		
		btnAdd2 = new JButton("\uC0AC\uC6D0 \uCD94\uAC00");
		pEmp.add(btnAdd2);
		
		btnUpdate2 = new JButton("\uC0AC\uC6D0 \uC218\uC815");
		pEmp.add(btnUpdate2);
		
		btnDelete2 = new JButton("\uC0AC\uC6D0 \uC0AD\uC81C");
		pEmp.add(btnDelete2);
		
		btnSearch2 = new JButton("\uC0AC\uC6D0 \uAC80\uC0C9");
		pEmp.add(btnSearch2);
		
		btnList2 = new JButton("\uC0AC\uC6D0 \uBAA9\uB85D");
		pEmp.add(btnList2);
		
		btnList2.addActionListener(this);
		btnSearch2.addActionListener(this);
		btnDelete2.addActionListener(this);
		btnUpdate2.addActionListener(this);
		btnAdd2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		//�μ�
		if (e.getSource() == btnList) {
			actionPerformedBtnList(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnUpdate) {
			actionPerformedBtnUpdate(e);
		}
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
		//���
		if (e.getSource() == btnList2) {
			try {
				actionPerformedBtnList2(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnAdd2) {
			actionPerformedBtnAdd2(e);
		}
		if (e.getSource() == btnSearch2) {
			actionPerformedBtnSearch2(e);
		}
		if (e.getSource() == btnUpdate2) {
			actionPerformedBtnUpdate2(e);
		}
		if (e.getSource() == btnDelete2) {
			actionPerformedBtnDelete2(e);
		}
	}

	private void actionPerformedBtnDelete2(ActionEvent e) {
		String empNo = JOptionPane.showInputDialog("������ �����ȣ�� �Է��ϼ���");

		try {
			int res = dao2.deleteEmployee(new Employee(Integer.parseInt(empNo)));
			if (frameEmployee == null) {
				frameEmployee = new EmployeeUI();
				frameEmployee.setDao(dao2);
			}
			if (res == 1)
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
			refreshUI();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnUpdate2(ActionEvent e) {
		String empNo = JOptionPane.showInputDialog("������ �����ȣ�� �Է��ϼ���");
		Employee selEmp;
		try {
			selEmp = dao2.selectEmployeetByNo(new Employee(Integer.parseInt(empNo)));
			if (selEmp == null) {
				JOptionPane.showMessageDialog(null, "������ �����ȣ�� �������� �ʽ��ϴ�.");
				return;
			}
			if (frameEmployee == null) {
				frameEmployee = new EmployeeUI();
				frameEmployee.setParent(this);
				frameEmployee.setDao(dao2);
			}
			frameEmployee.setEmployee(selEmp);
			frameEmployee.setVisible(true);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnSearch2(ActionEvent e) {
		String emptNo = JOptionPane.showInputDialog("�˻��� �����ȣ�� �Է��ϼ���");
		Employee selEmp;
		try {
			selEmp = dao2.selectEmployeetByNo(new Employee(Integer.parseInt(emptNo)));
			if (selEmp == null) {
				JOptionPane.showMessageDialog(null, "�ش� ����� �������� �ʽ��ϴ�.");
				return;
			}
			PanelEmployee pemp = new PanelEmployee();
			pemp.setEmployee(selEmp);
			pemp.setTfAllEditable(false);
			JOptionPane.showMessageDialog(null, pemp, "�μ� ����", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnAdd2(ActionEvent e) {
		if (frameEmployee == null) {
			frameEmployee = new EmployeeUI();
			frameEmployee.setParent(ErpManagementUI.this);
			frameEmployee.setDao(dao2);
		}
		frameEmployee.setVisible(true);
	}

	private void actionPerformedBtnList2(ActionEvent e) throws SQLException {
		if (frameEmployeeList == null) {
			frameEmployeeList = new EmployeeListUI();
		}
		frameEmployeeList.setEmployeeList(dao2.selectEmployeeByAll());
		frameEmployeeList.reloadData();
		frameEmployeeList.setVisible(true);
	}

	protected void actionPerformedBtnDelete(ActionEvent e) { //�μ� ����
		String deptNo = JOptionPane.showInputDialog("������ �μ���ȣ�� �Է��ϼ���");

		try {
			int res = dao.deleteDepartment(new Department(Integer.parseInt(deptNo)));
			if (frameDepartment == null) {
				frameDepartment = new DepartmentUI();
				frameDepartment.setDao(dao);
			}
			if (res != -1)
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
			refreshUI();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnUpdate(ActionEvent e) { //�μ� ����
		String deptNo = JOptionPane.showInputDialog("������ �μ���ȣ�� �Է��ϼ���");
		Department selDept;
		try {
			selDept = dao.selectDepartmentByNo(new Department(Integer.parseInt(deptNo)));
			if (selDept == null) {
				JOptionPane.showMessageDialog(null, "������ �μ��� �������� �ʽ��ϴ�.");
				return;
			}
			if (frameDepartment == null) {
				frameDepartment = new DepartmentUI();
				frameDepartment.setParent(this);
				frameDepartment.setDao(dao);
			}
			frameDepartment.setDepartment(selDept);
			frameDepartment.setVisible(true);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	protected void actionPerformedBtnSearch(ActionEvent e) { //�μ� �˻�
		String deptNo = JOptionPane.showInputDialog("�˻��� �μ���ȣ�� �Է��ϼ���");
		Department selDept;
		try {
			selDept = dao.selectDepartmentByNo(new Department(Integer.parseInt(deptNo)));
			if (selDept == null) {
				JOptionPane.showMessageDialog(null, "�ش� �μ��� �������� �ʽ��ϴ�.");
				return;
			}
			PanelDepartment pdept = new PanelDepartment();
			pdept.setDepartment(selDept);
			pdept.setTfAllEditable(false);
			JOptionPane.showMessageDialog(null, pdept, "�μ� ����", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) { //�μ� �߰�
		if (frameDepartment == null) {
			frameDepartment = new DepartmentUI();
			frameDepartment.setParent(ErpManagementUI.this);
			frameDepartment.setDao(dao);
		}
		frameDepartment.setVisible(true);
	}

	protected void actionPerformedBtnList(ActionEvent e) { //�μ� ���
		if (frameDepartmentList == null) {
			frameDepartmentList = new DepartmentListUI();
		}
		frameDepartmentList.setDepartmentList(dao.selectDepartmentByAll());
		frameDepartmentList.reloadData();
		frameDepartmentList.setVisible(true);
	}

	public void refreshUI() throws SQLException  { //ȭ�� �籸��
		if (frameDepartmentList != null && frameDepartmentList.isVisible()) {
			frameDepartmentList.setDepartmentList(dao.selectDepartmentByAll());
			frameDepartmentList.reloadData();
		}
		
		if (frameEmployeeList != null && frameEmployeeList.isVisible()) {
			frameEmployeeList.setEmployeeList(dao2.selectEmployeeByAll());
			frameEmployeeList.reloadData();
		}
	}
}
