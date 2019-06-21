package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import jdbc_study.dto.Department;


public class DeptListUI extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private List<Department> deptList; 

	public DeptListUI() { 
		initComponents(); 
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public JTable getTable() {
		return table;
	}

	private void initComponents() {
		setTitle("부서목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "부서목록",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(); 
		scrollPane.setViewportView(table);

	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));
	}

	private Object[][] getRows() { 
		Object[][] rows = new Object[deptList.size()][];
		for (int i = 0; i < deptList.size(); i++) {
			rows[i] = deptList.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] { "부서번호", "부서명", "위치(층)" };
	}

}
