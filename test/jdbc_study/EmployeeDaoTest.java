package jdbc_study;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EmployeeDaoTest {
	static final Logger log = LogManager.getLogger();
	static EmployeeDao dao;

	@BeforeClass 
	public static void setUpBeforeClass() throws Exception {
		log.trace("setUpBeforeClass()");
		dao = new EmployeeDaoImpl();
	}
	@Test //��ü select
	public void test4SelectEmployeeByAll() throws SQLException, FileNotFoundException, IOException {
		log.trace("testSelectEmployeeByAll()");
		List<Employee> list= dao.selectEmployeeByAll(); //utill.�� list import
		Assert.assertNotEquals(0, list.size()); //0�� �ƴϸ� �ٺҷ��Ӵ�
		
		File imgFile = null;
		for(Employee e : list ) {
			if(e.getPic() != null) { //���̾ƴϸ� img�� ����
				imgFile = getPicFile(e);
				log.trace(imgFile.getAbsolutePath()); //���� ��� ���
			}
		}
	}
	private File getPicFile(Employee e) throws FileNotFoundException, IOException { //�̹��������� ����� �Լ�
		File file = null;
		file = new File(System.getProperty("user.dir")+ "\\pics\\" + e.getEmpName() + ".jpg"); //���ϰ�ü�� ���巯��
		try(FileOutputStream fos = new FileOutputStream(file)){
			fos.write(e.getPic());
		}
		return file;
	}
	@Test  //insert
	public void test2InsertEmployee() throws SQLException {
		log.trace("testInsertEmployee()");
		Employee newEmp = new Employee(1004, "������", "���", new Employee(1003), 1500000, new Department(1), getImage("�����.jpg"));
		
		int res = dao.insertEmployee(newEmp);
		log.trace("res = " + res);
		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String fileName) {  //�̹����� ����Ʈ��
		byte[] pic = null;
		//D:\workspace\workspace_java\jdbc_study
		String imgPath = System.getProperty("user.dir") 
									+ System.getProperty("file.separator") 
									+ "images" 
									+ System.getProperty("file.separator") 
									+ fileName ; //�����Ų ��ΰ� ����
		File imgFile = new File(imgPath); //���ϰ�ü ���� //���ϻ����� �ƴ�
		
		try(InputStream is = new FileInputStream(imgFile);){
			pic = new byte[is.available()]; //�迭�� ����ũ��
			is.read(pic); //pic�� ������ �о ��
		} catch (FileNotFoundException e) {
			System.out.println("�ش������� ã���� ����");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}
	
	@Test //update
	public void test3UpdateEmployee() throws SQLException {
		Employee emp = new Employee(1004, "������", "�븮",new Employee(3426), 3500000, new Department(1));
		emp.setPic(getImage("img2.jpg"));
		int res = dao.updateEmployee(emp);
		log.trace(res);
		Assert.assertEquals(1, res);
	}
	
	@Test //delete
	public void test1DeleteEmployhee() throws SQLException {
		Employee emp = new Employee(1004);
		int res = dao.deleteEmployee(emp);
		log.trace(res);
		Assert.assertEquals(1, res);
	}
	
	@Test //selectNo
	public void test5SelectEmployeeByNo() throws SQLException, FileNotFoundException, IOException {
		Employee emp = dao.selectEmployeetByNo(new Employee(1004));
		File imgFile = null;
		if(emp.getPic() != null) { //���̾ƴϸ� img�� ����
			imgFile = getPicFile(emp);
			log.trace(imgFile.getAbsolutePath()); //���� ��� ���
			Assert.assertNotNull(emp);
		}
		
	}

}
