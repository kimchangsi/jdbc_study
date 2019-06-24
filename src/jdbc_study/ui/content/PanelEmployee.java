package jdbc_study.ui.content;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Base64;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class PanelEmployee extends JPanel implements ActionListener {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfTitle;
	private JTextField tfSalary;
	private JComboBox<Department> cmbDno;
	private JComboBox<Employee> cmbManager;
	private JButton btnPic;
	private ImageLabel lblPic;
	private JFileChooser chooser;
	private byte[] pic;
	private JPanel panel;
	private JPanel panel_1;
	private 	String imgPath = System.getProperty("user.dir") 
			+ System.getProperty("file.separator") 
			+ "images" 
			+ System.getProperty("file.separator") 
			+ "noImage.jpg" ; 
	private String s;

	public ImageLabel getLblPic() {
		return lblPic;
	}

	

	public PanelEmployee() {
		
		String path = System.getProperty("user.dir")+"\\images";
		chooser = new JFileChooser(path);
		initComponents();
		
		
	}
	private void initComponents() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC0AC\uC6D0\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		/*
		 * tfManager = new JTextField(); tfManager.setColumns(10); add(tfManager);
		 */
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblEmpNo = new JLabel("\uC0AC\uC6D0 \uBC88\uD638");
		panel.add(lblEmpNo);
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfEmpNo = new JTextField();
		panel.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("\uC0AC\uC6D0\uBA85");
		panel.add(lblEmpName);
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfEmpName = new JTextField();
		panel.add(tfEmpName);
		tfEmpName.setColumns(10);
		
		JLabel lblTilte = new JLabel("\uC9C1\uCC45");
		panel.add(lblTilte);
		lblTilte.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfTitle = new JTextField();
		panel.add(tfTitle);
		tfTitle.setColumns(10);
		
		JLabel lblManager = new JLabel("\uC9C1\uC18D\uC0C1\uC0AC");
		panel.add(lblManager);
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbManager = new JComboBox<Employee>();
		panel.add(cmbManager);
		
		JLabel lblSalary = new JLabel("\uC6D4\uAE09");
		panel.add(lblSalary);
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfSalary = new JTextField();
		panel.add(tfSalary);
		tfSalary.setColumns(10);
		
		JLabel lblDno = new JLabel("\uBD80\uC11C\uBC88\uD638");
		panel.add(lblDno);
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbDno = new JComboBox<Department>();
		panel.add(cmbDno);
		
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblPic = new ImageLabel();
		panel_1.add(lblPic);
		lblPic.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		
		
		btnPic = new JButton("\uC0AC\uC9C4");
		panel_1.add(btnPic, BorderLayout.SOUTH);
		btnPic.addActionListener(this);
		
	}
	
	public void setEmployee(Employee emp) {
		tfEmpNo.setText(emp.getEmpNo()+"");
		tfEmpName.setText(emp.getEmpName());
		tfTitle.setText(emp.getTitle());
		/* tfManager.setText(emp.getMananger().getEmpNo()+""); */
		cmbManager.setSelectedItem(emp.getMananger());
		tfSalary.setText(emp.getSalary()+"");
		cmbDno.setSelectedItem(emp.getDno());
		
		  System.out.println(emp.getPic());
		/* System.out.println(byteArrayToBinaryString(emp.getPic())); */
		 
		
	}
	
	public Employee getEmployee() {
		int empNo = Integer.parseInt(tfEmpNo.getText().trim());
		String empName = tfEmpName.getText().trim();
		String title = tfTitle.getText().trim();
		int manager = ((Employee)cmbManager.getSelectedItem()).getEmpNo();
		int salary = Integer.parseInt(tfSalary.getText().trim());
		int dno = ((Department)cmbDno.getSelectedItem()).getDeptNo();
		return new Employee(empNo, empName, title, new Employee(manager), salary, new Department(dno),pic);
	}
	
	public void clearTextField() {
		tfEmpNo.setText("");
		tfEmpName.setText("");
		tfTitle.setText("");
		tfSalary.setText("");
		cmbDno.setSelectedIndex(-1);
		cmbManager.setSelectedIndex(-1);
	}
	
	public void setNoImage() {
		lblPic.setImg(imgPath);
	}
	
	public JTextField getTfEmpNo() {
		return tfEmpNo;
	}
	
	public void setTfAllEditable(boolean isEditable) {
		tfEmpNo.setEditable(isEditable);
		tfEmpName.setEditable(isEditable);
		tfTitle.setEditable(isEditable);
		tfSalary.setEditable(isEditable);
		cmbDno.setEnabled(isEditable); // combobox는 setEnabled를 사용함
		cmbManager.setEnabled(isEditable);  // combobox는 setEnabled를 사용함
		
	}
	
	public void setCmbDno(List<Department> item) {
		System.out.println(item);
		 cmbDno.removeAllItems();
		for(Department d : item) {
			cmbDno.addItem(d);
		}
	}
	
	public void setCmbManager(List<Employee> item) {
		System.out.println(item);
		cmbManager.removeAllItems();
		for(Employee e : item) {
			cmbManager.addItem(e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPic) {
			actionPerformedBtnPic(e);
		}
	}
	protected void actionPerformedBtnPic(ActionEvent e) { //사진추가
	
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String selectedFilePath = chooser.getSelectedFile().getPath();
		lblPic.setImg(selectedFilePath);
		pic = getImage(selectedFilePath);
		repaint();
		revalidate();
		
		System.out.println();
	}
	
	private byte[] getImage(String selectedFilePath) {  //사진 바이트로 받아오기
		byte[] selPic = null;
		
		File imgFile = new File(selectedFilePath); 
		
		try(InputStream is = new FileInputStream(imgFile);){
			selPic = new byte[is.available()]; 
			is.read(selPic);
		} catch (FileNotFoundException e) {
			System.out.println("해당파일을 찾을수 없음");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return selPic;
	}
	
	private String byteArrayToBinaryString(byte[] b) {
		
		return s;
    }



	
	
}







