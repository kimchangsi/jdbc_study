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
	@Test //전체 select
	public void test4SelectEmployeeByAll() throws SQLException, FileNotFoundException, IOException {
		log.trace("testSelectEmployeeByAll()");
		List<Employee> list= dao.selectEmployeeByAll(); //utill.의 list import
		Assert.assertNotEquals(0, list.size()); //0이 아니면 다불러왓다
		
		File imgFile = null;
		for(Employee e : list ) {
			if(e.getPic() != null) { //널이아니면 img가 있음
				imgFile = getPicFile(e);
				log.trace(imgFile.getAbsolutePath()); //실제 경로 출력
			}
		}
	}
	private File getPicFile(Employee e) throws FileNotFoundException, IOException { //이미지파일을 만드는 함수
		File file = null;
		file = new File(System.getProperty("user.dir")+ "\\pics\\" + e.getEmpName() + ".jpg"); //파일객체가 만드러짐
		try(FileOutputStream fos = new FileOutputStream(file)){
			fos.write(e.getPic());
		}
		return file;
	}
	@Test  //insert
	public void test2InsertEmployee() throws SQLException {
		log.trace("testInsertEmployee()");
		Employee newEmp = new Employee(1004, "서현진", "사원", new Employee(1003), 1500000, new Department(1), getImage("손흥민.jpg"));
		
		int res = dao.insertEmployee(newEmp);
		log.trace("res = " + res);
		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String fileName) {  //이미지를 바이트로
		byte[] pic = null;
		//D:\workspace\workspace_java\jdbc_study
		String imgPath = System.getProperty("user.dir") 
									+ System.getProperty("file.separator") 
									+ "images" 
									+ System.getProperty("file.separator") 
									+ fileName ; //실행시킨 경로가 나옴
		File imgFile = new File(imgPath); //파일객체 생성 //파일생성은 아님
		
		try(InputStream is = new FileInputStream(imgFile);){
			pic = new byte[is.available()]; //배열의 가용크기
			is.read(pic); //pic의 내용을 읽어서 들어감
		} catch (FileNotFoundException e) {
			System.out.println("해당파일을 찾을수 없음");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}
	
	@Test //update
	public void test3UpdateEmployee() throws SQLException {
		Employee emp = new Employee(1004, "아이유", "대리",new Employee(3426), 3500000, new Department(1));
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
		if(emp.getPic() != null) { //널이아니면 img가 있음
			imgFile = getPicFile(emp);
			log.trace(imgFile.getAbsolutePath()); //실제 경로 출력
			Assert.assertNotNull(emp);
		}
		
	}

}
