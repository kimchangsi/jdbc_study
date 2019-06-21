package jdbc_study;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mysql.jdbc.log.LogUtils;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DeptEmpTransctionDao;
import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.daoimpl.DeptEmpTransctionDaoImpl;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //test를 순서대로 실행하기위해

public class DeptEmpTransactionDaoTest {
	//밑에 메소드들이 static 이라서 static은 static만 접근가능
	static final Logger log = LogManager.getLogger(); 
	static DeptEmpTransctionDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		log.trace("start DeptEmpTransactionDaoTest");
		dao = new DeptEmpTransctionDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		log.trace("Stop DeptEmpTransactionDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test //department 실패
	public void test1trInsertEmpAndDept() {
		log.trace("Department fail");
		Department dept = new Department(1,"수정"	, 8);
		Employee emp = new Employee(1004, "서현진", "사원", new Employee(1003), 150000, dept);
		
		int res = dao.trInsertEmpAndDept(emp, dept);
		log.trace(String.format("res %d", res));
		Assert.assertNotEquals(2, res);
	}
	
	@Test //department 성공 employee 실패
	public void test2trInsertEmpAndDept() {
		log.trace("Employee fail");
		Department dept = new Department(6,"마케팅"	, 8);
		Employee emp = new Employee(1003, "조민희", "과장", new Employee(4377), 150000, dept);
		
		int res = dao.trInsertEmpAndDept(emp, dept);
		log.trace(String.format("res %d", res));
		Assert.assertNotEquals(2, res);
	}
	
	@Test //department 성공 employee 실패
	public void test3trInsertEmpAndDept() throws SQLException {
		log.trace("successl");
		Department dept = new Department(6,"마케팅"	, 8);
		Employee emp = new Employee(1234, "손흥민", "과장", new Employee(4377), 200000, dept);
		
		int res = dao.trInsertEmpAndDept(emp, dept);
		log.trace(String.format("res %d", res));
		Assert.assertEquals(2, res);
		
		EmployeeDao empDao = new EmployeeDaoImpl();
		empDao.deleteEmployee(emp);
		
		DepartmentDao deptDao = new DepartmentDaoImpl();
		deptDao.deleteDepartment(dept);
	}

	 
	
	

}
